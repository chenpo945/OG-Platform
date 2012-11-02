/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.curve;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

import com.opengamma.analytics.financial.curve.generator.GeneratorYDCurve;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.provider.description.MulticurveProviderInterface;
import com.opengamma.analytics.financial.provider.description.MulticurveProviderDiscount;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Generator of MarketDiscountBundle from the parameters.
 */
public class GeneratorProviderDiscount extends Function1D<DoubleMatrix1D, MulticurveProviderInterface> {

  /**
   * The map with the currencies and the related discounting curves names.
   */
  private final LinkedHashMap<String, Currency> _discountingMap;
  /**
   * The map with the indexes and the related forward curves names.
   */
  private final LinkedHashMap<String, IndexON> _forwardONMap;
  /**
   * The map with the indexes and the related forward curves names.
   */
  private final LinkedHashMap<String, IborIndex> _forwardIborMap;
  /**
   * The map with the names and the related curves generators.
   */
  private final LinkedHashMap<String, GeneratorYDCurve> _generatorsMap;
  /**
   * The market with known data (curves, model parameters, etc.).
   */
  private final MulticurveProviderDiscount _knownData;

  /**
   * Constructor
   * @param knownData The yield curve bundle with known data (curves).
   * @param discountingMap The discounting curves names map.
   * @param forwardIborMap The forward curves names map.
   * @param forwardONMap The forward curves names map.
   * @param generatorsMap The generators map.
   */
  public GeneratorProviderDiscount(final MulticurveProviderDiscount knownData, final LinkedHashMap<String, Currency> discountingMap, final LinkedHashMap<String, IborIndex> forwardIborMap,
      final LinkedHashMap<String, IndexON> forwardONMap, final LinkedHashMap<String, GeneratorYDCurve> generatorsMap) {
    ArgumentChecker.notNull(discountingMap, "Discounting curves names map");
    ArgumentChecker.notNull(forwardIborMap, "Forward curves names map");
    ArgumentChecker.notNull(forwardONMap, "Forward curves names map");
    _knownData = knownData;
    _discountingMap = discountingMap;
    _forwardIborMap = forwardIborMap;
    _forwardONMap = forwardONMap;
    _generatorsMap = generatorsMap;
  }

  /**
   * Gets the know curves.
   * @return The known curves.
   */
  public MulticurveProviderDiscount getKnownData() {
    return _knownData;
  }

  @Override
  public MulticurveProviderDiscount evaluate(DoubleMatrix1D x) {
    MulticurveProviderDiscount bundle = _knownData.copy();
    Set<String> nameSet = _generatorsMap.keySet();
    int indexParam = 0;
    for (String name : nameSet) {
      GeneratorYDCurve gen = _generatorsMap.get(name);
      double[] paramCurve = Arrays.copyOfRange(x.getData(), indexParam, indexParam + gen.getNumberOfParameter());
      indexParam += gen.getNumberOfParameter();
      YieldAndDiscountCurve curve = gen.generateCurve(name, bundle, paramCurve);
      if (_discountingMap.containsKey(name)) {
        bundle.setCurve(_discountingMap.get(name), curve);
      }
      if (_forwardONMap.containsKey(name)) {
        bundle.setCurve(_forwardONMap.get(name), curve);
      }
      if (_forwardIborMap.containsKey(name)) {
        bundle.setCurve(_forwardIborMap.get(name), curve);
      }
      // TODO: Do we need to check that the curve is used at least once?
    }
    return bundle;
  }

}
