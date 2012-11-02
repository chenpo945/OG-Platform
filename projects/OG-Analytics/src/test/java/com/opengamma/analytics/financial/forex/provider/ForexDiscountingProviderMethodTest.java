/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.forex.provider;

import static org.testng.AssertJUnit.assertEquals;

import javax.time.calendar.ZonedDateTime;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.forex.definition.ForexDefinition;
import com.opengamma.analytics.financial.forex.derivative.Forex;
import com.opengamma.analytics.financial.forex.method.MulticurveProviderDiscountForexDataSets;
import com.opengamma.analytics.financial.instrument.payment.PaymentFixedDefinition;
import com.opengamma.analytics.financial.interestrate.market.description.MultipleCurrencyCurveSensitivityMarket;
import com.opengamma.analytics.financial.interestrate.payments.derivative.PaymentFixed;
import com.opengamma.analytics.financial.interestrate.payments.provider.PaymentFixedDiscountingProviderMethod;
import com.opengamma.analytics.financial.provider.calculator.PresentValueCurveSensitivityDiscountingProviderCalculator;
import com.opengamma.analytics.financial.provider.calculator.PresentValueDiscountingProviderCalculator;
import com.opengamma.analytics.financial.provider.description.MulticurveProviderInterface;
import com.opengamma.analytics.financial.util.AssertSensivityObjects;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.time.DateUtils;

/**
 * Test related to the method for Forex transaction by discounting on each payment.
 */
public class ForexDiscountingProviderMethodTest {

  private static final Currency CUR_1 = Currency.EUR;
  private static final Currency CUR_2 = Currency.USD;
  private static final ZonedDateTime PAYMENT_DATE = DateUtils.getUTCDate(2011, 5, 24);
  private static final double NOMINAL_1 = 100000000;
  private static final double FX_RATE = 1.4177;
  private static final ForexDefinition FX_DEFINITION = new ForexDefinition(CUR_1, CUR_2, PAYMENT_DATE, NOMINAL_1, FX_RATE);
  private static final MulticurveProviderInterface PROVIDER = MulticurveProviderDiscountForexDataSets.createMulticurvesForex();
  private static final ZonedDateTime REFERENCE_DATE = DateUtils.getUTCDate(2011, 5, 20);

  public static final String NOT_USED = "Not used";
  public static final String[] NOT_USED_2 = {NOT_USED, NOT_USED};

  private static final Forex FX = FX_DEFINITION.toDerivative(REFERENCE_DATE, NOT_USED_2);
  private static final PaymentFixedDefinition PAY_DEFINITION_1 = new PaymentFixedDefinition(CUR_1, PAYMENT_DATE, NOMINAL_1);
  private static final PaymentFixed PAY_1 = PAY_DEFINITION_1.toDerivative(REFERENCE_DATE, NOT_USED_2);
  private static final PaymentFixedDefinition PAY_DEFINITION_2 = new PaymentFixedDefinition(CUR_2, PAYMENT_DATE, -NOMINAL_1 * FX_RATE);
  private static final PaymentFixed PAY_2 = PAY_DEFINITION_2.toDerivative(REFERENCE_DATE, NOT_USED_2);

  private static final ForexDiscountingProviderMethod METHOD_FX = ForexDiscountingProviderMethod.getInstance();
  private static final PaymentFixedDiscountingProviderMethod METHOD_PAY = PaymentFixedDiscountingProviderMethod.getInstance();
  private static final PresentValueDiscountingProviderCalculator PVDC = PresentValueDiscountingProviderCalculator.getInstance();
  private static final PresentValueCurveSensitivityDiscountingProviderCalculator PVSCDC = PresentValueCurveSensitivityDiscountingProviderCalculator.getInstance();

  //  private static final CurrencyExposureForexCalculator CEC_FX = CurrencyExposureForexCalculator.getInstance();
  //  private static final PresentValueCurveSensitivityMCSCalculator PVCSC_FX = PresentValueCurveSensitivityMCSCalculator.getInstance();
  //  private static final TodayPaymentCalculator TPC = TodayPaymentCalculator.getInstance();
  //  private static final ConstantSpreadHorizonThetaCalculator THETAC = ConstantSpreadHorizonThetaCalculator.getInstance();
  //  private static final ConstantSpreadYieldCurveBundleRolldownFunction CURVE_ROLLDOWN = ConstantSpreadYieldCurveBundleRolldownFunction.getInstance();

  private static final double TOLERANCE_PV = 1.0E-2; // one cent out of 100m
  private static final double TOLERANCE_PV_DELTA = 1.0E+2; //Testing note: Sensitivity is for a movement of 1. 1E+2 = 1 cent for a 1 bp move.

  @Test
  /**
   * Tests the present value computation.
   */
  public void presentValue() {
    final MultipleCurrencyAmount pv = METHOD_FX.presentValue(FX, PROVIDER);
    final MultipleCurrencyAmount ca1 = METHOD_PAY.presentValue(PAY_1, PROVIDER);
    final MultipleCurrencyAmount ca2 = METHOD_PAY.presentValue(PAY_2, PROVIDER);
    assertEquals("ForexDiscountingMethod: presentValue", ca1.plus(ca2), pv);
  }

  @Test
  /**
   * Test the present value through the method and through the calculator.
   */
  public void presentValueMethodVsCalculator() {
    final MultipleCurrencyAmount pvMethod = METHOD_FX.presentValue(FX, PROVIDER);
    final MultipleCurrencyAmount pvCalculator = PVDC.visit(FX, PROVIDER);
    assertEquals("ForexDiscountingMethod: presentValue: Method vs Calculator", pvMethod, pvCalculator);
  }

  @Test
  /**
   * Test the present value sensitivity to interest rate.
   */
  public void presentValueCurveSensitivity() {
    final MultipleCurrencyCurveSensitivityMarket pvcs = METHOD_FX.presentValueCurveSensitivity(FX, PROVIDER);
    final MultipleCurrencyCurveSensitivityMarket pvs1 = PVSCDC.visit(PAY_1, PROVIDER);
    final MultipleCurrencyCurveSensitivityMarket pvs2 = PVSCDC.visit(PAY_2, PROVIDER);
    AssertSensivityObjects.assertEquals("ForexDiscountingMethod: presentValueCurveSensitivity", pvs1.plus(pvs2).cleaned(), pvcs.cleaned(), TOLERANCE_PV_DELTA);
  }

  @Test
  /**
   * Test the present value curve sensitivity through the method and through the calculator.
   */
  public void presentValueCurveSensitivityMethodVsCalculator() {
    final MultipleCurrencyCurveSensitivityMarket pvcsMethod = METHOD_FX.presentValueCurveSensitivity(FX, PROVIDER);
    final MultipleCurrencyCurveSensitivityMarket pvcsCalculator = PVSCDC.visit(FX, PROVIDER);
    AssertSensivityObjects.assertEquals("", pvcsMethod, pvcsCalculator, TOLERANCE_PV_DELTA);
  }

  @Test
  /**
   * Test the present value of EUR/USD is the same as an USD/EUR.
   */
  public void presentValueReverse() {
    final ForexDefinition fxReverseDefinition = new ForexDefinition(CUR_2, CUR_1, PAYMENT_DATE, -NOMINAL_1 * FX_RATE, 1.0 / FX_RATE);
    final Forex fxReverse = fxReverseDefinition.toDerivative(REFERENCE_DATE, NOT_USED_2);
    final MultipleCurrencyAmount pv = METHOD_FX.presentValue(FX, PROVIDER);
    final MultipleCurrencyAmount pvReverse = METHOD_FX.presentValue(fxReverse, PROVIDER);
    assertEquals("Forex present value: Reverse description", pv.getAmount(CUR_1), pvReverse.getAmount(CUR_1), TOLERANCE_PV);
    assertEquals("Forex present value: Reverse description", pv.getAmount(CUR_2), pvReverse.getAmount(CUR_2), TOLERANCE_PV);
  }

  @Test
  /**
   * Tests the currency exposure computation.
   */
  public void currencyExposure() {
    final MultipleCurrencyAmount exposureMethod = METHOD_FX.currencyExposure(FX, PROVIDER);
    final MultipleCurrencyAmount pv = METHOD_FX.presentValue(FX, PROVIDER);
    assertEquals("Currency exposure", pv, exposureMethod);
    //    final MultipleCurrencyAmount exposureCalculator = CEC_FX.visit(FX, PROVIDER); // TODO
    //    assertEquals("Currency exposure: Method vs Calculator", exposureMethod, exposureCalculator);
  }

  //  @Test
  //  /**
  //   * Tests the forward Forex rate computation.
  //   */
  //  public void forwardRate() {
  //    final double fxToday = 1.4123;
  //    final FXMatrix fxMatrix = new FXMatrix(CUR_1, CUR_2, fxToday);
  //    final YieldCurveBundle curvesFx = new YieldCurveBundle(PROVIDER.getCurvesMap(), fxMatrix, CURVE_CURRENCY);
  //    final double fwd = METHOD_FX.forwardForexRate(FX, curvesFx);
  //    final double dfDomestic = PROVIDER.getCurve(CURVES_NAME[1]).getDiscountFactor(FX.getPaymentTime());
  //    final double dfForeign = PROVIDER.getCurve(CURVES_NAME[0]).getDiscountFactor(FX.getPaymentTime());
  //    final double fwdExpected = fxToday * dfForeign / dfDomestic;
  //    assertEquals("Forex: forward rate", fwdExpected, fwd, 1.0E-10);
  //  }
  //
  //  @Test
  //  /**
  //   * Tests the forward Forex rate through the method and through the calculator.
  //   */
  //  public void forwardRateMethodVsCalculator() {
  //    final double fxToday = 1.4123;
  //    final FXMatrix fxMatrix = new FXMatrix(CUR_1, CUR_2, fxToday);
  //    final YieldCurveBundle curvesFx = new YieldCurveBundle(PROVIDER.getCurvesMap(), fxMatrix, CURVE_CURRENCY);
  //    final double fwdMethod = METHOD_FX.forwardForexRate(FX, curvesFx);
  //    final ForwardRateForexCalculator FWDC = ForwardRateForexCalculator.getInstance();
  //    final double fwdCalculator = FWDC.visit(FX, curvesFx);
  //    assertEquals("Forex: forward rate", fwdMethod, fwdCalculator, 1.0E-10);
  //  }

  //  @Test
  //  /**
  //   * Tests the TodayPaymentCalculator for forex transactions.
  //   */
  //  public void forexTodayPaymentBeforePayment() {
  //    final Forex fx = FX_DEFINITION.toDerivative(PAYMENT_DATE.minusDays(1), CURVES_NAME);
  //    final MultipleCurrencyAmount cash = TPC.visit(fx);
  //    assertEquals("TodayPaymentCalculator: forex", 0.0, cash.getAmount(fx.getCurrency1()), TOLERANCE_PV);
  //    assertEquals("TodayPaymentCalculator: forex", 0.0, cash.getAmount(fx.getCurrency2()), TOLERANCE_PV);
  //    assertEquals("TodayPaymentCalculator: forex", 2, cash.getCurrencyAmounts().length);
  //  }
  //
  //  @Test
  //  /**
  //   * Tests the TodayPaymentCalculator for forex transactions.
  //   */
  //  public void forexTodayPaymentOnPayment() {
  //    final Forex fx = FX_DEFINITION.toDerivative(PAYMENT_DATE, CURVES_NAME);
  //    final MultipleCurrencyAmount cash = TPC.visit(fx);
  //    assertEquals("TodayPaymentCalculator: forex", FX_DEFINITION.getPaymentCurrency1().getReferenceAmount(), cash.getAmount(fx.getCurrency1()), TOLERANCE_PV);
  //    assertEquals("TodayPaymentCalculator: forex", FX_DEFINITION.getPaymentCurrency2().getReferenceAmount(), cash.getAmount(fx.getCurrency2()), TOLERANCE_PV);
  //    assertEquals("TodayPaymentCalculator: forex", 2, cash.getCurrencyAmounts().length);
  //  }
  //
  //  @Test
  //  /**
  //   * Tests the Theta (1 day change of pv) for forex transactions.
  //   */
  //  public void thetaBeforePayment() {
  //    final MultipleCurrencyAmount theta = THETAC.getTheta(FX_DEFINITION, REFERENCE_DATE, CURVES_NAME, PROVIDER, 1);
  //    final Forex swapToday = FX_DEFINITION.toDerivative(REFERENCE_DATE, CURVES_NAME);
  //    final Forex swapTomorrow = FX_DEFINITION.toDerivative(REFERENCE_DATE.plusDays(1), CURVES_NAME);
  //    final MultipleCurrencyAmount pvToday = PVC_FX.visit(swapToday, PROVIDER);
  //    final YieldCurveBundle tomorrowData = CURVE_ROLLDOWN.rollDown(PROVIDER, TimeCalculator.getTimeBetween(REFERENCE_DATE, REFERENCE_DATE.plusDays(1)));
  //    final MultipleCurrencyAmount pvTomorrow = PVC_FX.visit(swapTomorrow, tomorrowData);
  //    final MultipleCurrencyAmount thetaExpected = pvTomorrow.plus(pvToday.multipliedBy(-1.0));
  //    assertEquals("ThetaCalculator: fixed-coupon swap", thetaExpected.getAmount(CUR_1), theta.getAmount(CUR_1), TOLERANCE_PV);
  //    assertEquals("ThetaCalculator: fixed-coupon swap", thetaExpected.getAmount(CUR_2), theta.getAmount(CUR_2), TOLERANCE_PV);
  //    assertEquals("ThetaCalculator: fixed-coupon swap", 2, theta.getCurrencyAmounts().length);
  //  }

}
