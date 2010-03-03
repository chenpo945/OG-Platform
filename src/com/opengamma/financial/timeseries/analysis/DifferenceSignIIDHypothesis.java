/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries.analysis;

import com.opengamma.math.statistics.distribution.NormalDistribution;
import com.opengamma.util.timeseries.DoubleTimeSeries;

/**
 * 
 * @author emcleod
 */
public class DifferenceSignIIDHypothesis<T extends DoubleTimeSeries<?>> extends IIDHypothesis<T> {
  private final double _criticalValue;

  public DifferenceSignIIDHypothesis(final double level) {
    if (level <= 0 || level > 1)
      throw new IllegalArgumentException("Level must be between 0 and 1");
    _criticalValue = new NormalDistribution(0, 1).getInverseCDF(1 - level / 2.);
  }

  @Override
  public boolean testIID(final T x) {
    final Double[] data = x.valuesArray();
    final int n = data.length;
    int t = 0;
    for (int i = 1; i < n; i++) {
      if (data[i] > data[i - 1]) {
        t++;
      }
    }
    final double mean = (n - 1) / 2.;
    final double std = Math.sqrt((n + 1) / 12.);
    return Math.abs(t - mean) / std < _criticalValue;
  }
}
