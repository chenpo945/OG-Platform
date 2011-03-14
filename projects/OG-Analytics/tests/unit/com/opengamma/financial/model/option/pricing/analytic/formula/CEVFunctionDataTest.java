/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.analytic.formula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * 
 */
public class CEVFunctionDataTest {
  private static final double F = 100;
  private static final double DF = 0.95;
  private static final double SIGMA = 0.23;
  private static final double BETA = 0.96;
  private static final CEVFunctionData DATA = new CEVFunctionData(F, DF, SIGMA, BETA);

  @Test(expected = IllegalArgumentException.class)
  public void testLowBeta() {
    new CEVFunctionData(F, 0, SIGMA, -BETA);
  }

  @Test
  public void test() {
    assertEquals(DATA.getDiscountFactor(), DF, 0);
    assertEquals(DATA.getForward(), F, 0);
    assertEquals(DATA.getBlackVolatility(), SIGMA, 0);
    assertEquals(DATA.getBeta(), BETA, 0);
    CEVFunctionData other = new CEVFunctionData(F, DF, SIGMA, BETA);
    assertEquals(DATA, other);
    assertEquals(DATA.hashCode(), other.hashCode());
    other = new CEVFunctionData(F + 1, DF, SIGMA, BETA);
    assertFalse(other.equals(DATA));
    other = new CEVFunctionData(F, DF * 0.5, SIGMA, BETA);
    assertFalse(other.equals(DATA));
    other = new CEVFunctionData(F, DF, SIGMA + 0.1, BETA);
    assertFalse(other.equals(DATA));
    other = new CEVFunctionData(F, DF, SIGMA, BETA * 0.5);
    assertFalse(other.equals(DATA));
  }

}
