/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries.filter;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cern.colt.Arrays;

import com.opengamma.util.timeseries.DoubleTimeSeries;
import com.opengamma.util.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.util.timeseries.fast.longint.FastArrayLongDoubleTimeSeries;
import com.opengamma.util.timeseries.fast.longint.FastLongDoubleTimeSeries;

/**
 * 
 * @author emcleod
 */
public class ZeroValueDoubleTimeSeriesFilter<T extends DoubleTimeSeries<?>> extends TimeSeriesFilter<T> {
  private static final Logger s_Log = LoggerFactory.getLogger(ZeroValueDoubleTimeSeriesFilter.class);
  private double _zero;

  public ZeroValueDoubleTimeSeriesFilter() {
    _zero = 1e-15;
  }

  public ZeroValueDoubleTimeSeriesFilter(final double zero) {
    if (zero < 0)
      throw new IllegalArgumentException("Must have a positive value of zero");
    _zero = zero;
  }

  public void setZero(final double zero) {
    if (zero < 0)
      throw new IllegalArgumentException("Must have a positive value of zero");
    _zero = zero;
  }

  @Override
  public FilteredTimeSeries<DoubleTimeSeries<Long>> evaluate(final T ts) {
    if (ts == null)
      throw new IllegalArgumentException("Time series was null");
    if (ts.isEmpty()) {
      s_Log.info("Time series was empty");
      return new FilteredTimeSeries<DoubleTimeSeries<Long>>(FastArrayLongDoubleTimeSeries.EMPTY_SERIES, null);
    }
    final FastLongDoubleTimeSeries x = ts.toFastLongDoubleTimeSeries();
    final int n = x.size();
    final long[] filteredDates = new long[n];
    final double[] filteredData = new double[n];
    final long[] rejectedDates = new long[n];
    final double[] rejectedData = new double[n];
    final Iterator<Entry<Long, Double>> iter = x.iterator();
    Entry<Long, Double> entry;
    int i = 0, j = 0;
    while (iter.hasNext()) {
      entry = iter.next();
      if (Math.abs(entry.getValue()) < _zero) {
        rejectedDates[j] = entry.getKey();
        rejectedData[j++] = entry.getValue();
      } else {
        filteredDates[i] = entry.getKey();
        filteredData[i++] = entry.getValue();
      }
    }
    final DateTimeNumericEncoding encoding = x.getEncoding();
    return new FilteredTimeSeries<DoubleTimeSeries<Long>>(new FastArrayLongDoubleTimeSeries(encoding, Arrays.trimToCapacity(filteredDates, i), Arrays.trimToCapacity(filteredData,
        i)), new FastArrayLongDoubleTimeSeries(encoding, Arrays.trimToCapacity(rejectedDates, j), Arrays.trimToCapacity(rejectedData, j)));
  }
}
