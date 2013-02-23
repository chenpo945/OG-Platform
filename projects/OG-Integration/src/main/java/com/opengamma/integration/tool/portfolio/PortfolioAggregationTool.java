/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.aggregation.AggregationFunction;
import com.opengamma.financial.aggregation.AssetClassAggregationFunction;
import com.opengamma.financial.aggregation.CurrencyAggregationFunction;
import com.opengamma.financial.aggregation.DetailedAssetClassAggregationFunction;
import com.opengamma.financial.aggregation.PortfolioAggregator;
import com.opengamma.financial.aggregation.PositionAttributeAggregationFunction;
import com.opengamma.financial.aggregation.UnderlyingAggregationFunction;
import com.opengamma.integration.tool.IntegrationToolContext;
import com.opengamma.util.generate.scripts.Scriptable;

/**
 * Tool to aggregate portfolios
 */
@Scriptable
public class PortfolioAggregationTool extends AbstractTool<IntegrationToolContext> {

  private static final Logger s_logger = LoggerFactory.getLogger(PortfolioAggregationTool.class);

  private final Map<String, AggregationFunction<?>> _aggregationFunctions = new HashMap<>();
  private static final String PORTFOLIO_OPT = "p";
  private static final String AGGREGATION_OPT = "a";
  private static final String SPLIT_OPT = "s";


  /**
   * Runs the tool.
   *
   * @param args  empty arguments
   */
  public static void main(String[] args) {  // CSIGNORE
    new PortfolioAggregationTool().initAndRun(args, IntegrationToolContext.class);
  }

  @Override
  protected void doRun() {
    populateAggregationFunctionMap(getToolContext().getSecuritySource());
    PortfolioAggregator.aggregate(getCommandLine().getOptionValue(PORTFOLIO_OPT) + " by " + getCommandLine().getOptionValues(AGGREGATION_OPT)[0],
                                  getToolContext().getPortfolioMaster(),
                                  getToolContext().getPositionMaster(),
                                  getToolContext().getPositionSource(),
                                  getToolContext().getSecuritySource(),
                                  createAggregationFunctions(getCommandLine().getOptionValues(AGGREGATION_OPT)),
                                  true);
  }


  private void populateAggregationFunctionMap(SecuritySource secSource) {
    _aggregationFunctions.put("AssetClass", new AssetClassAggregationFunction());
    _aggregationFunctions.put("Currency", new CurrencyAggregationFunction());
    _aggregationFunctions.put("DetailedAssetClass", new DetailedAssetClassAggregationFunction());
    _aggregationFunctions.put("Underlying", new UnderlyingAggregationFunction(secSource, "BLOOMBERG_TICKER"));
  }
  
  private AggregationFunction<?>[] createAggregationFunctions(String[] aggregatorNames) {
    if (aggregatorNames == null) {
      s_logger.error("No aggregators specified");
      System.exit(1);
      return null; // idiot compiler...
    } else { 
      @SuppressWarnings("unchecked")
      AggregationFunction<?>[] results = new AggregationFunction<?>[aggregatorNames.length];
      for (int i = 0; i < aggregatorNames.length; i++) {
        AggregationFunction<?> aggregationFunction = _aggregationFunctions.get(aggregatorNames[i].trim());
        if (aggregationFunction != null) {
          results[i] = aggregationFunction;
        } else {
          results[i] =  new PositionAttributeAggregationFunction(aggregatorNames[i].trim());
        }
      }
      return results;
    }
  }

  protected Options createOptions(boolean contextProvided) {
    Options options = super.createOptions(contextProvided);

    @SuppressWarnings("static-access")
    Option baseViewOption = OptionBuilder.withLongOpt("portfolio")
                                         .hasArg()
                                         .isRequired()
                                         .withDescription("The portfolio name")
                                         .create(PORTFOLIO_OPT);
    options.addOption(baseViewOption);
    @SuppressWarnings("static-access")
    Option aggregationTypesOption = OptionBuilder.withLongOpt("aggregation-types")
                                                 .hasArgs()
                                                 .isRequired()
                                                 .withValueSeparator(',')
                                                 .withDescription("The (comma, no space seperated) names of the aggregation" +
                                                                  " styles to use: e.g AssetClass,Currency,DetailtedAssetClass")
                                                 .create(AGGREGATION_OPT);
    options.addOption(aggregationTypesOption);
    @SuppressWarnings("static-access")
    Option splitPortfoliosOption =  OptionBuilder.withLongOpt("split")
                                                 .withDescription(
                                                     "Split into separate portfolios grouped by the top-level aggregator" +
                                                         " instead of aggregating the existing portfoliio")
                                                 .create(SPLIT_OPT);
    options.addOption(splitPortfoliosOption);
    return options;
  }
}
