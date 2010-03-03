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
public class RankTestIIDHypothesis<T extends DoubleTimeSeries<?>> extends IIDHypothesis<T> {
  private final double _criticalValue;

  public RankTestIIDHypothesis(final double level) {
    if (level <= 0 || level > 1)
      throw new IllegalArgumentException("Level must be betweeen 0 and 1");
    _criticalValue = new NormalDistribution(0, 1).getInverseCDF(1 - level / 2.);
  }

  @Override
  public boolean testIID(final T x) {
    final Double[] data = x.valuesArray();
    int t = 0;
    final int n = x.size();
    double val;
    for (int i = 0; i < n - 1; i++) {
      val = data[i];
      for (int j = i + 1; j < n; j++) {
        if (data[j] > val) {
          t++;
        }
      }
    }
    final double mean = n * (n - 1) / 4.;
    final double std = Math.sqrt(n * (n - 1) * (2 * n + 5.) / 72.);
    return Math.abs(t - mean) / std < _criticalValue;
  }

}
