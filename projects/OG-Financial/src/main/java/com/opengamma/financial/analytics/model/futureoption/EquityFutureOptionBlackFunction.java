/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.futureoption;

import com.opengamma.financial.analytics.model.CalculationPropertyNamesAndValues;

/**
 *
 */
public abstract class EquityFutureOptionBlackFunction extends EquityFutureOptionFunction {

  /**
   * @param valueRequirementName The value requirement name
   */
  public EquityFutureOptionBlackFunction(final String... valueRequirementName) {
    super(valueRequirementName);
  }

  @Override
  protected String getCalculationMethod() {
    return CalculationPropertyNamesAndValues.BLACK_METHOD;
  }

  @Override
  protected String getModelType() {
    return CalculationPropertyNamesAndValues.ANALYTIC;
  }
}
