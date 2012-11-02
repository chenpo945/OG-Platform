/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate;

import org.apache.commons.lang.Validate;

import com.opengamma.analytics.financial.calculator.PresentValueCurveSensitivityMCSCalculator;
import com.opengamma.analytics.financial.calculator.PresentValueMCACalculator;
import com.opengamma.analytics.financial.forex.derivative.ForexSwap;
import com.opengamma.analytics.financial.forex.method.ForexSwapDiscountingMethod;
import com.opengamma.analytics.financial.forex.method.MultipleCurrencyInterestRateCurveSensitivity;
import com.opengamma.analytics.financial.interestrate.bond.definition.BillTransaction;
import com.opengamma.analytics.financial.interestrate.bond.method.BillTransactionDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.cash.derivative.Cash;
import com.opengamma.analytics.financial.interestrate.cash.derivative.DepositCounterpart;
import com.opengamma.analytics.financial.interestrate.cash.derivative.DepositZero;
import com.opengamma.analytics.financial.interestrate.cash.method.CashDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.cash.method.DepositZeroDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.fra.derivative.ForwardRateAgreement;
import com.opengamma.analytics.financial.interestrate.fra.method.ForwardRateAgreementDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.future.derivative.InterestRateFuture;
import com.opengamma.analytics.financial.interestrate.future.method.InterestRateFutureDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.swap.derivative.Swap;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Compute the sensitivity of the spread to the curve; the spread is the number to be added to the market standard quote of the instrument for which the present value of the instrument is zero.
 * The notion of "spread" will depend of each instrument.
 */
public final class ParSpreadMarketQuoteCurveSensitivityCalculator extends AbstractInstrumentDerivativeVisitor<YieldCurveBundle, InterestRateCurveSensitivity> {

  /**
   * The unique instance of the calculator.
   */
  private static final ParSpreadMarketQuoteCurveSensitivityCalculator INSTANCE = new ParSpreadMarketQuoteCurveSensitivityCalculator();

  /**
   * Gets the calculator instance.
   * @return The calculator.
   */
  public static ParSpreadMarketQuoteCurveSensitivityCalculator getInstance() {
    return INSTANCE;
  }

  /**
   * Constructor.
   */
  private ParSpreadMarketQuoteCurveSensitivityCalculator() {
  }

  /**
   * The methods and calculators.
   */
  private static final PresentValueMCACalculator PVMCC = PresentValueMCACalculator.getInstance();
  private static final PresentValueCurveSensitivityMCSCalculator PVCSMCC = PresentValueCurveSensitivityMCSCalculator.getInstance();
  private static final PresentValueBasisPointCalculator PVBPC = PresentValueBasisPointCalculator.getInstance();
  private static final PresentValueBasisPointCurveSensitivityCalculator PVBPCSC = PresentValueBasisPointCurveSensitivityCalculator.getInstance();
  private static final CashDiscountingMethod METHOD_DEPOSIT = CashDiscountingMethod.getInstance();
  private static final DepositZeroDiscountingMethod METHOD_DEPOSIT_ZERO = DepositZeroDiscountingMethod.getInstance();
  private static final BillTransactionDiscountingMethod METHOD_BILL_TRANSACTION = BillTransactionDiscountingMethod.getInstance();
  private static final ForwardRateAgreementDiscountingMethod METHOD_FRA = ForwardRateAgreementDiscountingMethod.getInstance();
  private static final InterestRateFutureDiscountingMethod METHOD_IR_FUTURES = InterestRateFutureDiscountingMethod.getInstance();
  private static final ForexSwapDiscountingMethod METHOD_FX_SWAP = ForexSwapDiscountingMethod.getInstance();

  @Override
  public InterestRateCurveSensitivity visit(final InstrumentDerivative derivative, final YieldCurveBundle curves) {
    Validate.notNull(curves);
    Validate.notNull(derivative);
    return derivative.accept(this, curves);
  }

  //     -----     Deposit     -----

  @Override
  public InterestRateCurveSensitivity visitCash(final Cash deposit, final YieldCurveBundle curves) {
    return METHOD_DEPOSIT.parSpreadCurveSensitivity(deposit, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitDepositZero(final DepositZero deposit, final YieldCurveBundle curves) {
    return METHOD_DEPOSIT_ZERO.parSpreadCurveSensitivity(deposit, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitDepositCounterpart(final DepositCounterpart deposit, final YieldCurveBundle curves) {
    return METHOD_DEPOSIT.parSpreadCurveSensitivity(deposit, curves);
  }

  //     -----     Bill & bonds     -----

  @Override
  public InterestRateCurveSensitivity visitBillTransaction(final BillTransaction bill, final YieldCurveBundle curves) {
    return METHOD_BILL_TRANSACTION.parSpreadCurveSensitivity(bill, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitSwap(final Swap<?, ?> swap, final YieldCurveBundle curves) {
    ArgumentChecker.notNull(curves, "Curves");
    ArgumentChecker.notNull(swap, "Swap");
    final Currency ccy1 = swap.getFirstLeg().getCurrency();
    final MultipleCurrencyInterestRateCurveSensitivity pvcsmc = PVCSMCC.visit(swap, curves);
    final InterestRateCurveSensitivity pvcs = pvcsmc.converted(ccy1, curves.getFxRates()).getSensitivity(ccy1);
    final InterestRateCurveSensitivity pvbpcs = PVBPCSC.visit(swap.getFirstLeg(), curves);
    final double pvbp = PVBPC.visit(swap.getFirstLeg(), curves);
    final double pv = curves.getFxRates().convert(PVMCC.visit(swap, curves), ccy1).getAmount();
    // Implementation note: Total pv in currency 1.
    return pvcs.multipliedBy(-1.0 / pvbp).plus(pvbpcs.multipliedBy(pv / (pvbp * pvbp)));
  }

  @Override
  public InterestRateCurveSensitivity visitFixedCouponSwap(final SwapFixedCoupon<?> swap, final YieldCurveBundle curves) {
    return visitSwap(swap, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitForwardRateAgreement(final ForwardRateAgreement fra, final YieldCurveBundle curves) {
    Validate.notNull(curves);
    Validate.notNull(fra);
    return METHOD_FRA.parSpreadCurveSensitivity(fra, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitInterestRateFuture(final InterestRateFuture future, final YieldCurveBundle curves) {
    return METHOD_IR_FUTURES.priceCurveSensitivity(future, curves);
  }

  @Override
  public InterestRateCurveSensitivity visitForexSwap(final ForexSwap fx, final YieldCurveBundle curves) {
    return METHOD_FX_SWAP.parSpreadCurveSensitivity(fx, curves);
  }

}
