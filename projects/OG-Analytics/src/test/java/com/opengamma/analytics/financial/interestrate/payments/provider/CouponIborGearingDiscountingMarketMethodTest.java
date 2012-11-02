/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.payments.provider;

import static org.testng.AssertJUnit.assertEquals;

import java.util.TreeSet;

import javax.time.calendar.ZonedDateTime;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.curve.sensitivity.ParameterSensitivity;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.payment.CouponIborGearingDefinition;
import com.opengamma.analytics.financial.interestrate.market.description.MultipleCurrencyCurveSensitivityMarket;
import com.opengamma.analytics.financial.interestrate.market.description.ProviderDiscountDataSets;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponIborGearing;
import com.opengamma.analytics.financial.provider.calculator.PresentValueCurveSensitivityDiscountingProviderCalculator;
import com.opengamma.analytics.financial.provider.calculator.PresentValueDiscountingProviderCalculator;
import com.opengamma.analytics.financial.provider.description.MulticurveProviderDiscount;
import com.opengamma.analytics.financial.provider.sensitivity.ParameterSensitivityDiscountInterpolatedFDProviderCalculator;
import com.opengamma.analytics.financial.provider.sensitivity.ParameterSensitivityProviderCalculator;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.analytics.financial.util.AssertSensivityObjects;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.time.DateUtils;

/**
 * Tests related to the pricing and sensitivities of Ibor coupon with gearing factor and spread in the discounting method.
 */
public class CouponIborGearingDiscountingMarketMethodTest {

  private static final MulticurveProviderDiscount PROVIDER = ProviderDiscountDataSets.createProvider3();
  private static final IborIndex[] IBOR_INDEXES = ProviderDiscountDataSets.getIndexesIbor();
  private static final IborIndex EURIBOR3M = IBOR_INDEXES[0];
  private static final Calendar CALENDAR_EUR = EURIBOR3M.getCalendar();
  private static final Currency EUR = EURIBOR3M.getCurrency();
  private static final DayCount DAY_COUNT_COUPON = DayCountFactory.INSTANCE.getDayCount("Actual/365");
  private static final ZonedDateTime ACCRUAL_START_DATE = DateUtils.getUTCDate(2011, 5, 23);
  private static final ZonedDateTime ACCRUAL_END_DATE = DateUtils.getUTCDate(2011, 8, 22);
  private static final double ACCRUAL_FACTOR = DAY_COUNT_COUPON.getDayCountFraction(ACCRUAL_START_DATE, ACCRUAL_END_DATE);
  private static final double NOTIONAL = 1000000; //1m
  private static final double FACTOR = 2.0;
  private static final double SPREAD = 0.0050;
  private static final ZonedDateTime FIXING_DATE = ScheduleCalculator.getAdjustedDate(ACCRUAL_START_DATE, -EURIBOR3M.getSpotLag(), CALENDAR_EUR);
  private static final CouponIborGearingDefinition COUPON_DEFINITION = new CouponIborGearingDefinition(EURIBOR3M.getCurrency(), ACCRUAL_END_DATE, ACCRUAL_START_DATE, ACCRUAL_END_DATE, ACCRUAL_FACTOR,
      NOTIONAL, FIXING_DATE, EURIBOR3M, SPREAD, FACTOR);
  private static final ZonedDateTime REFERENCE_DATE = DateUtils.getUTCDate(2010, 12, 27);
  private static final CouponIborGearing COUPON = COUPON_DEFINITION.toDerivative(REFERENCE_DATE, new String[] {"A", "B"});

  private static final CouponIborGearingDiscountingProviderMethod METHOD = CouponIborGearingDiscountingProviderMethod.getInstance();
  private static final PresentValueDiscountingProviderCalculator PVDC = PresentValueDiscountingProviderCalculator.getInstance();
  private static final PresentValueCurveSensitivityDiscountingProviderCalculator PVCSDC = PresentValueCurveSensitivityDiscountingProviderCalculator.getInstance();
  private static final ParameterSensitivityProviderCalculator PSC = new ParameterSensitivityProviderCalculator(PVCSDC);
  private static final double SHIFT = 1.0E-6;
  private static final ParameterSensitivityDiscountInterpolatedFDProviderCalculator PSC_DSC_FD = new ParameterSensitivityDiscountInterpolatedFDProviderCalculator(PVDC, SHIFT);

  private static final double TOLERANCE_PV = 1.0E-2;
  private static final double TOLERANCE_PV_DELTA = 1.0E+2; //Testing note: Sensitivity is for a movement of 1. 1E+2 = 1 cent for a 1 bp move.

  @Test
  public void presentValue() {
    MultipleCurrencyAmount pv = METHOD.presentValue(COUPON, PROVIDER);
    double df = PROVIDER.getDiscountFactor(COUPON.getCurrency(), COUPON.getPaymentTime());
    double forward = PROVIDER.getForwardRate(EURIBOR3M, COUPON.getFixingPeriodStartTime(), COUPON.getFixingPeriodEndTime(), COUPON.getFixingAccrualFactor());
    double pvExpected = (forward * FACTOR + SPREAD) * COUPON.getPaymentYearFraction() * COUPON.getNotional() * df;
    assertEquals("Coupon Ibor Gearing: Present value by discounting", pvExpected, pv.getAmount(EUR), 1.0E-2);
  }

  @Test
  public void presentValueMethodVsCalculator() {
    MultipleCurrencyAmount pvMethod = METHOD.presentValue(COUPON, PROVIDER);
    MultipleCurrencyAmount pvCalculator = PVDC.visit(COUPON, PROVIDER);
    assertEquals("CouponFixedDiscountingMarketMethod: present value", pvMethod.getAmount(EUR), pvCalculator.getAmount(EUR), TOLERANCE_PV);
  }

  @Test
  /**
   * Tests present value curve sensitivity when the valuation date is on trade date.
   */
  public void presentValueCurveSensitivity() {
    ParameterSensitivity pvpsDepositExact = PSC.calculateSensitivity(COUPON, new TreeSet<String>(), PROVIDER);
    ParameterSensitivity pvpsDepositFD = PSC_DSC_FD.calculateSensitivity(COUPON, PROVIDER);
    AssertSensivityObjects.assertEquals("CashDiscountingProviderMethod: presentValueCurveSensitivity ", pvpsDepositExact, pvpsDepositFD, TOLERANCE_PV_DELTA);
  }

  @Test
  public void presentValueMarketSensitivityMethodVsCalculator() {
    MultipleCurrencyCurveSensitivityMarket pvcsMethod = METHOD.presentValueCurveSensitivity(COUPON, PROVIDER);
    MultipleCurrencyCurveSensitivityMarket pvcsCalculator = PVCSDC.visit(COUPON, PROVIDER);
    AssertSensivityObjects.assertEquals("CouponFixedDiscountingMarketMethod: presentValueMarketSensitivity", pvcsMethod, pvcsCalculator, TOLERANCE_PV_DELTA);
  }

}
