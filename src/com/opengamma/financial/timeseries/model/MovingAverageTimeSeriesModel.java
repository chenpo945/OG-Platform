/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries.model;

import com.opengamma.math.statistics.distribution.ProbabilityDistribution;
import com.opengamma.util.timeseries.DoubleTimeSeries;
import com.opengamma.util.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.util.timeseries.fast.longint.FastArrayLongDoubleTimeSeries;

/**
 * 
 * @author emcleod
 */
public class MovingAverageTimeSeriesModel {
  private final ProbabilityDistribution<Double> _random;

  public MovingAverageTimeSeriesModel(final ProbabilityDistribution<Double> random) {
    if (random == null)
      throw new IllegalArgumentException("Probability distribution was null");
    _random = random;
  }

  public DoubleTimeSeries<Long> getSeries(final double[] theta, final int q, final long[] dates) {
    if (theta == null)
      throw new IllegalArgumentException("Coefficient array was null");
    if (q < 1)
      throw new IllegalArgumentException("Order must be greater than zero");
    if (theta.length < q)
      throw new IllegalArgumentException("Coefficient array must contain at least " + q + " elements");
    if (dates == null)
      throw new IllegalArgumentException("Dates array was null");
    if (dates.length == 0)
      throw new IllegalArgumentException("Dates array was empty");
    final int n = dates.length;
    final double[] z = new double[n];
    for (int i = 0; i < n; i++) {
      z[i] = _random.nextRandom();
    }
    final double[] data = new double[n];
    data[0] = theta[0];
    double sum;
    for (int i = 1; i < n; i++) {
      sum = theta[0] + z[i];
      for (int j = 1; j < (i < q ? i : q + 1); j++) {
        sum += z[i - j] * theta[j];
      }
      data[i] = sum;
    }
    return new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.TIME_EPOCH_MILLIS, dates, data);
  }
}
