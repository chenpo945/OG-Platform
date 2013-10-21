/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.credit.isda.cdx;

import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_SPREAD_CURVE_SHIFT;
import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_SPREAD_CURVE_SHIFT_TYPE;
import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE;
import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_CONFIG;
import static com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues.PROPERTY_YIELD_CURVE_CALCULATION_METHOD;

import java.util.Collections;
import java.util.Set;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.credit.creditdefaultswap.definition.vanilla.CreditDefaultSwapDefinition;
import com.opengamma.analytics.financial.credit.isdastandardmodel.CDSAnalytic;
import com.opengamma.analytics.financial.credit.isdastandardmodel.ISDACompliantCreditCurve;
import com.opengamma.analytics.financial.credit.isdastandardmodel.ISDACompliantYieldCurve;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.OpenGammaCompilationContext;
import com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues;
import com.opengamma.financial.analytics.model.credit.CreditSecurityToIdentifierVisitor;
import com.opengamma.financial.analytics.model.credit.isda.cds.StandardVanillaRR01CDSFunction;
import com.opengamma.financial.security.FinancialSecurity;

/**
 * 
 */
public class ISDACDXAsSingleNameRR01Function extends ISDACDXAsSingleNameFunction {

  public ISDACDXAsSingleNameRR01Function() {
    super(ValueRequirementNames.RR01);
  }

  @Override
  protected Set<ComputedValue> getComputedValue(final CreditDefaultSwapDefinition definition,
                                                final ISDACompliantYieldCurve yieldCurve,
                                                final ZonedDateTime[] times,
                                                final double[] marketSpreads,
                                                final ZonedDateTime valuationDate,
                                                final ComputationTarget target,
                                                final ValueProperties properties,
                                                final FunctionInputs inputs,
                                                ISDACompliantCreditCurve hazardCurve,
                                                CDSAnalytic analytic) {
    final double rr01 = StandardVanillaRR01CDSFunction.getRR01(definition, yieldCurve, properties, hazardCurve, analytic);
    final ValueSpecification spec = new ValueSpecification(ValueRequirementNames.RR01, target.toSpecification(), properties);
    return Collections.singleton(new ComputedValue(spec, rr01));
  }

  @Override
  public Set<ValueRequirement> getRequirements(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue) {
    final Set<ValueRequirement> requirements = super.getRequirements(context, target, desiredValue);
    if (requirements == null) {
      return null;
    }
    final ValueProperties constraints = desiredValue.getConstraints();
    final Set<String> recoveryRateBumps = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_RECOVERY_RATE_CURVE_BUMP);
    if (recoveryRateBumps == null || recoveryRateBumps.size() != 1) {
      return null;
    }
    final Set<String> recoveryRateBumpTypes = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_RECOVERY_RATE_BUMP_TYPE);
    if (recoveryRateBumpTypes == null || recoveryRateBumpTypes.size() != 1) {
      return null;
    }
    final Set<String> cdsPriceTypes = constraints.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_CDS_PRICE_TYPE);
    if (cdsPriceTypes == null || cdsPriceTypes.size() != 1) {
      return null;
    }

    final FinancialSecurity security = (FinancialSecurity) target.getSecurity();
    final String spreadCurveName = "CDS_INDEX_" + security.accept(new CreditSecurityToIdentifierVisitor(
        OpenGammaCompilationContext.getSecuritySource(context))).getUniqueId().getValue();
    //TODO shouldn't need all of the yield curve properties
    final String yieldCurveName = desiredValue.getConstraint(PROPERTY_YIELD_CURVE);
    final String yieldCurveCalculationConfig = desiredValue.getConstraint(PROPERTY_YIELD_CURVE_CALCULATION_CONFIG);
    final String yieldCurveCalculationMethod = desiredValue.getConstraint(PROPERTY_YIELD_CURVE_CALCULATION_METHOD);
    final Set<String> creditSpreadCurveShifts = constraints.getValues(PROPERTY_SPREAD_CURVE_SHIFT);
    final ValueProperties.Builder hazardRateCurveProperties = ValueProperties.builder()
        .with(ValuePropertyNames.CURVE, spreadCurveName)
        .with(ValuePropertyNames.CURVE_CALCULATION_METHOD, "ISDA")
        .with(PROPERTY_YIELD_CURVE_CALCULATION_CONFIG, yieldCurveCalculationConfig)
        .with(PROPERTY_YIELD_CURVE_CALCULATION_METHOD, yieldCurveCalculationMethod)
        .with(PROPERTY_YIELD_CURVE, yieldCurveName);
    if (creditSpreadCurveShifts != null) {
      final Set<String> creditSpreadCurveShiftTypes = constraints.getValues(PROPERTY_SPREAD_CURVE_SHIFT_TYPE);
      hazardRateCurveProperties.with(PROPERTY_SPREAD_CURVE_SHIFT, creditSpreadCurveShifts).with(PROPERTY_SPREAD_CURVE_SHIFT_TYPE, creditSpreadCurveShiftTypes);
    }
    final ValueRequirement hazardRateCurveRequirement = new ValueRequirement(ValueRequirementNames.HAZARD_RATE_CURVE, target.toSpecification(), hazardRateCurveProperties.get());
    requirements.add(hazardRateCurveRequirement);
    return requirements;
  }

  @Override
  protected ValueProperties.Builder getCommonResultProperties() {
    return createValueProperties()
        .withAny(CreditInstrumentPropertyNamesAndValues.PROPERTY_RECOVERY_RATE_CURVE_BUMP)
        .withAny(CreditInstrumentPropertyNamesAndValues.PROPERTY_RECOVERY_RATE_BUMP_TYPE)
        .withAny(CreditInstrumentPropertyNamesAndValues.PROPERTY_CDS_PRICE_TYPE);
  }

  @Override
  protected boolean labelResultWithCurrency() {
    return true;
  }
}
