/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.credit.isda.calibration;

import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_SPREAD_CURVE_SHIFT;
import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_SPREAD_CURVE_SHIFT_TYPE;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.threeten.bp.ZonedDateTime;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.credit.BuySellProtection;
import com.opengamma.analytics.financial.credit.ISDAYieldCurveAndSpreadsProvider;
import com.opengamma.analytics.financial.credit.StubType;
import com.opengamma.analytics.financial.credit.calibratehazardratecurve.ISDAHazardRateCurveCalculator;
import com.opengamma.analytics.financial.credit.creditdefaultswap.definition.legacy.LegacyVanillaCreditDefaultSwapDefinition;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.CDSAnalytic;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.CDSAnalyticFactory;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.FastCreditCurveBuilder;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.ISDACompliantCreditCurve;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.ISDACompliantYieldCurve;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.QuotedSpread;
import com.opengamma.analytics.financial.credit.hazardratecurve.HazardRateCurve;
import com.opengamma.analytics.financial.credit.isdayieldcurve.ISDADateCurve;
import com.opengamma.analytics.math.curve.NodalObjectsCurve;
import com.opengamma.core.holiday.HolidaySource;
import com.opengamma.core.holiday.impl.WeekendHolidaySource;
import com.opengamma.core.organization.OrganizationSource;
import com.opengamma.core.region.RegionSource;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetSpecification;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.target.ComputationTargetType;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.OpenGammaCompilationContext;
import com.opengamma.financial.OpenGammaExecutionContext;
import com.opengamma.financial.analytics.conversion.CreditDefaultIndexSwapSecurityToProxyConverter;
import com.opengamma.financial.analytics.model.YieldCurveFunctionUtils;
import com.opengamma.financial.analytics.model.cds.ISDAFunctionConstants;
import com.opengamma.financial.analytics.model.credit.CreditFunctionUtils;
import com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues;
import com.opengamma.financial.analytics.model.credit.CreditSecurityToIdentifierVisitor;
import com.opengamma.financial.analytics.model.credit.IMMDateGenerator;
import com.opengamma.financial.analytics.model.credit.isdanew.CDSAnalyticVisitor;
import com.opengamma.financial.convention.HolidaySourceCalendarAdapter;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventionFactory;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityTypes;
import com.opengamma.financial.security.FinancialSecurityUtils;
import com.opengamma.financial.security.cds.CreditDefaultSwapIndexSecurity;
import com.opengamma.util.OpenGammaClock;
import com.opengamma.util.ParallelArrayBinarySort;
import com.opengamma.util.async.AsynchronousExecution;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class ISDACDXAsSingleNameHazardRateCurveFunction extends ISDAHazardRateCurveFunction {
  private static final BusinessDayConvention FOLLOWING = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
  private static final FastCreditCurveBuilder CREDIT_CURVE_BUILDER = new FastCreditCurveBuilder();

  @Override
  public Set<ComputedValue> execute(final FunctionExecutionContext executionContext, final FunctionInputs inputs, final ComputationTarget target,
      final Set<ValueRequirement> desiredValues) throws AsynchronousExecution {
    final RegionSource regionSource = OpenGammaExecutionContext.getRegionSource(executionContext);
    final OrganizationSource organizationSource = OpenGammaExecutionContext.getOrganizationSource(executionContext);
    final HolidaySource holidaySource = OpenGammaExecutionContext.getHolidaySource(executionContext);
    final CreditDefaultIndexSwapSecurityToProxyConverter converter = new CreditDefaultIndexSwapSecurityToProxyConverter(holidaySource, regionSource, organizationSource);
    final ZonedDateTime valuationTime = ZonedDateTime.now(executionContext.getValuationClock());
    final CreditDefaultSwapIndexSecurity security = (CreditDefaultSwapIndexSecurity) target.getSecurity();
    final Calendar calendar = new HolidaySourceCalendarAdapter(holidaySource, FinancialSecurityUtils.getCurrency(security));
    LegacyVanillaCreditDefaultSwapDefinition cds = (LegacyVanillaCreditDefaultSwapDefinition) security.accept(converter);
    cds = cds.withEffectiveDate(FOLLOWING.adjustDate(calendar, valuationTime.withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(1)));
    final Object yieldCurveObject = inputs.getValue(ValueRequirementNames.YIELD_CURVE);
    if (yieldCurveObject == null) {
      throw new OpenGammaRuntimeException("Could not get yield curve");
    }
    final Object spreadCurveObject = inputs.getValue(ValueRequirementNames.CREDIT_SPREAD_CURVE);
    if (spreadCurveObject == null) {
      throw new OpenGammaRuntimeException("Could not get credit spread curve");
    }
    final ISDACompliantYieldCurve yieldCurve = (ISDACompliantYieldCurve) yieldCurveObject;
    final NodalObjectsCurve<?, ?> spreadCurve = (NodalObjectsCurve<?, ?>) spreadCurveObject;
    final Tenor[] tenors = CreditFunctionUtils.getTenors(spreadCurve.getXData());
    final Double[] marketSpreadObjects = CreditFunctionUtils.getSpreads(spreadCurve.getYData());
    ParallelArrayBinarySort.parallelBinarySort(tenors, marketSpreadObjects);
    final int n = tenors.length;

    // assume new style IMM maturities
    final CDSAnalyticFactory analyticFactory = new CDSAnalyticFactory(cds.getRecoveryRate(), cds.getCouponFrequency().getPeriod())
        .with(cds.getBusinessDayAdjustmentConvention())
        .with(calendar).with(cds.getStubType())
        .withAccualDCC(cds.getDayCountFractionConvention());

    final CDSAnalytic pricingCDS = analyticFactory.makeCDS(valuationTime.toLocalDate(), cds.getEffectiveDate().toLocalDate(), cds.getMaturityDate().toLocalDate());
    double spread = 0;
    final ZonedDateTime[] times = new ZonedDateTime[n];
    final CDSAnalytic[] creditAnalytics = new CDSAnalytic[n];
    final double[] marketSpreads = new double[n];
    for (int i = 0; i < n; i++) {
      ZonedDateTime nextIMM = IMMDateGenerator.getNextIMMDate(valuationTime, tenors[i]).withHour(0).withMinute(0).withSecond(0).withNano(0);
      creditAnalytics[i] = analyticFactory.makeCDS(valuationTime.toLocalDate(), cds.getEffectiveDate().toLocalDate(), nextIMM.toLocalDate());
      marketSpreads[i] = marketSpreadObjects[i] * 1e-4;
      if (!nextIMM.isAfter(cds.getMaturityDate().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
        spread = marketSpreads[i];
      }
    }
    final ValueProperties properties = Iterables.getOnlyElement(desiredValues).getConstraints().copy()
        .with(ValuePropertyNames.FUNCTION, getUniqueId())
        .get();
    final ISDACompliantCreditCurve curve;

    if (IMMDateGenerator.isIMMDate(cds.getMaturityDate())) {
      curve = CREDIT_CURVE_BUILDER.calibrateCreditCurve(pricingCDS, spread, yieldCurve);
    } else {
      curve = CREDIT_CURVE_BUILDER.calibrateCreditCurve(creditAnalytics, marketSpreads, yieldCurve);
    }
    final ValueSpecification spec = new ValueSpecification(ValueRequirementNames.HAZARD_RATE_CURVE, target.toSpecification(), properties);
    return Collections.singleton(new ComputedValue(spec, curve));
  }

  @Override
  public ComputationTargetType getTargetType() {
    return FinancialSecurityTypes.CREDIT_DEFAULT_SWAP_INDEX_SECURITY;
  }

  @Override
  public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target) {
    final ValueProperties properties = ValueProperties.all();
    return Collections.singleton(new ValueSpecification(ValueRequirementNames.HAZARD_RATE_CURVE, target.toSpecification(), properties));
  }

  @Override
  public Set<ValueRequirement> getRequirements(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue) {
    final ValueProperties constraints = desiredValue.getConstraints();
    final Set<String> yieldCurveNames = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE);
    if (yieldCurveNames == null || yieldCurveNames.size() != 1) {
      return null;
    }
    final Set<String> yieldCurveCalculationConfigNames = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_CONFIG);
    if (yieldCurveCalculationConfigNames == null || yieldCurveCalculationConfigNames.size() != 1) {
      return null;
    }
    final Set<String> yieldCurveCalculationMethodNames = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_METHOD);
    if (yieldCurveCalculationMethodNames == null || yieldCurveCalculationMethodNames.size() != 1) {
      return null;
    }
    final FinancialSecurity security = (FinancialSecurity) target.getSecurity();
    final ComputationTargetSpecification currencyTarget = ComputationTargetSpecification.of(FinancialSecurityUtils.getCurrency(target.getSecurity()));
    final String yieldCurveName = Iterables.getOnlyElement(yieldCurveNames);
    final String yieldCurveCalculationConfigName = Iterables.getOnlyElement(yieldCurveCalculationConfigNames);
    final String yieldCurveCalculationMethodName = Iterables.getOnlyElement(yieldCurveCalculationMethodNames);
    final CreditSecurityToIdentifierVisitor identifierVisitor = new CreditSecurityToIdentifierVisitor(OpenGammaCompilationContext.getSecuritySource(context));
    final String spreadCurveName = "CDS_INDEX_" + security.accept(identifierVisitor).getUniqueId().getValue();
    final ValueRequirement yieldCurveRequirement = YieldCurveFunctionUtils.getCurveRequirement(currencyTarget, yieldCurveName, yieldCurveCalculationConfigName,
        yieldCurveCalculationMethodName);

    final ValueProperties.Builder spreadCurveProperties = ValueProperties.builder()
        .with(ValuePropertyNames.CURVE, spreadCurveName);
    final Set<String> spreadCurveShift = constraints.getValues(PROPERTY_SPREAD_CURVE_SHIFT);
    if (spreadCurveShift != null && !spreadCurveShift.isEmpty()) {
      final Set<String> creditSpreadCurveShiftTypes = constraints.getValues(PROPERTY_SPREAD_CURVE_SHIFT_TYPE);
      if (creditSpreadCurveShiftTypes == null || creditSpreadCurveShiftTypes.size() != 1) {
        return null;
      }
      spreadCurveProperties.with(PROPERTY_SPREAD_CURVE_SHIFT, spreadCurveShift).with(PROPERTY_SPREAD_CURVE_SHIFT_TYPE, creditSpreadCurveShiftTypes);
    }
    final ValueRequirement creditSpreadCurveRequirement = new ValueRequirement(ValueRequirementNames.CREDIT_SPREAD_CURVE, ComputationTargetSpecification.NULL, spreadCurveProperties.get());
    return Sets.newHashSet(yieldCurveRequirement, creditSpreadCurveRequirement);
  }

  @Override
  public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target, final Map<ValueSpecification, ValueRequirement> inputs) {
    final ValueProperties.Builder propertiesBuilder = createValueProperties()
        .with(ValuePropertyNames.CURVE_CALCULATION_METHOD, ISDAFunctionConstants.ISDA_METHOD_NAME);
    for (final Map.Entry<ValueSpecification, ValueRequirement> entry : inputs.entrySet()) {
      final ValueSpecification spec = entry.getKey();
      final ValueProperties.Builder inputPropertiesBuilder = spec.getProperties().copy();
      inputPropertiesBuilder.withoutAny(ValuePropertyNames.FUNCTION);
      if (spec.getValueName().equals(ValueRequirementNames.YIELD_CURVE)) {
        propertiesBuilder.with(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE, inputPropertiesBuilder.get().getValues(ValuePropertyNames.CURVE));
        inputPropertiesBuilder.withoutAny(ValuePropertyNames.CURVE);
        propertiesBuilder.with(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_CONFIG, inputPropertiesBuilder.get().getValues(ValuePropertyNames.CURVE_CALCULATION_CONFIG));
        inputPropertiesBuilder.withoutAny(ValuePropertyNames.CURVE_CALCULATION_CONFIG);
        propertiesBuilder.with(CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_METHOD, inputPropertiesBuilder.get().getValues(ValuePropertyNames.CURVE_CALCULATION_METHOD));
        inputPropertiesBuilder.withoutAny(ValuePropertyNames.CURVE_CALCULATION_METHOD);
      } else if (spec.getValueName().equals(ValueRequirementNames.CREDIT_SPREAD_CURVE)) {
        propertiesBuilder.with(ValuePropertyNames.CURVE, inputPropertiesBuilder.get().getValues(ValuePropertyNames.CURVE));
        inputPropertiesBuilder.withoutAny(ValuePropertyNames.CURVE);
      }
      final ValueProperties inputProperties = inputPropertiesBuilder.get();
      if (!inputProperties.isEmpty()) {
        for (final String propertyName : inputProperties.getProperties()) {
          propertiesBuilder.with(propertyName, inputProperties.getValues(propertyName));
        }
      }
    }
    final ValueProperties properties = propertiesBuilder.get();
    final ComputationTargetSpecification targetSpec = target.toSpecification();
    return Collections.singleton(new ValueSpecification(ValueRequirementNames.HAZARD_RATE_CURVE, targetSpec, properties));
  }

}
