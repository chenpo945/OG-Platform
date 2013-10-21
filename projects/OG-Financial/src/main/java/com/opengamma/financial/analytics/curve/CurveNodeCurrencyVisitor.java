/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.financial.analytics.ircurve.strips.CashNode;
import com.opengamma.financial.analytics.ircurve.strips.ContinuouslyCompoundedRateNode;
import com.opengamma.financial.analytics.ircurve.strips.CreditSpreadNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNodeVisitor;
import com.opengamma.financial.analytics.ircurve.strips.DeliverableSwapFutureNode;
import com.opengamma.financial.analytics.ircurve.strips.DiscountFactorNode;
import com.opengamma.financial.analytics.ircurve.strips.FRANode;
import com.opengamma.financial.analytics.ircurve.strips.FXForwardNode;
import com.opengamma.financial.analytics.ircurve.strips.IMMFRANode;
import com.opengamma.financial.analytics.ircurve.strips.IMMSwapNode;
import com.opengamma.financial.analytics.ircurve.strips.RateFutureNode;
import com.opengamma.financial.analytics.ircurve.strips.SwapNode;
import com.opengamma.financial.analytics.ircurve.strips.ThreeLegBasisSwapNode;
import com.opengamma.financial.analytics.ircurve.strips.ZeroCouponInflationNode;
import com.opengamma.financial.convention.CMSLegConvention;
import com.opengamma.financial.convention.CompoundingIborLegConvention;
import com.opengamma.financial.convention.Convention;
import com.opengamma.financial.convention.ConventionSource;
import com.opengamma.financial.convention.ConventionVisitor;
import com.opengamma.financial.convention.DeliverablePriceQuotedSwapFutureConvention;
import com.opengamma.financial.convention.DepositConvention;
import com.opengamma.financial.convention.EquityConvention;
import com.opengamma.financial.convention.FXForwardAndSwapConvention;
import com.opengamma.financial.convention.FXSpotConvention;
import com.opengamma.financial.convention.FederalFundsFutureConvention;
import com.opengamma.financial.convention.IMMFRAConvention;
import com.opengamma.financial.convention.IMMSwapConvention;
import com.opengamma.financial.convention.IborIndexConvention;
import com.opengamma.financial.convention.InflationLegConvention;
import com.opengamma.financial.convention.InterestRateFutureConvention;
import com.opengamma.financial.convention.OISLegConvention;
import com.opengamma.financial.convention.OvernightIndexConvention;
import com.opengamma.financial.convention.PriceIndexConvention;
import com.opengamma.financial.convention.SwapConvention;
import com.opengamma.financial.convention.SwapFixedLegConvention;
import com.opengamma.financial.convention.SwapIndexConvention;
import com.opengamma.financial.convention.VanillaIborLegConvention;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Returns all of the currencies relevant for a {@link CurveNode}. This information is pulled from
 * the convention(s) associated with the node. Returns null if there are no currencies applicable
 * to the curve node.
 */
public class CurveNodeCurrencyVisitor implements CurveNodeVisitor<Set<Currency>>, ConventionVisitor<Set<Currency>> {
  /** The convention source */
  private final ConventionSource _conventionSource;

  /**
   * @param conventionSource The convention source, not null
   */
  public CurveNodeCurrencyVisitor(final ConventionSource conventionSource) {
    ArgumentChecker.notNull(conventionSource, "convention source");
    _conventionSource = conventionSource;
  }

  /**
   * Gets the convention source.
   * @return The convention source
   */
  protected ConventionSource getConventionSource() {
    return _conventionSource;
  }

  @Override
  public Set<Currency> visitCashNode(final CashNode node) {
    final Convention convention = _conventionSource.getConvention(node.getConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + node.getConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitContinuouslyCompoundedRateNode(final ContinuouslyCompoundedRateNode node) {
    return null;
  }

  @Override
  public Set<Currency> visitCreditSpreadNode(final CreditSpreadNode node) {
    return null;
  }

  @Override
  public Set<Currency> visitDeliverableSwapFutureNode(final DeliverableSwapFutureNode node) {
    final Convention convention = _conventionSource.getConvention(node.getFutureConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + node.getFutureConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitDiscountFactorNode(final DiscountFactorNode node) {
    return null;
  }

  @Override
  public Set<Currency> visitFRANode(final FRANode node) {
    final Convention convention = _conventionSource.getConvention(node.getConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + node.getConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitFXForwardNode(final FXForwardNode node) {
    return Sets.newHashSet(node.getPayCurrency(), node.getReceiveCurrency());
  }

  @Override
  public Set<Currency> visitIMMFRANode(final IMMFRANode node) {
    final IMMFRAConvention convention = _conventionSource.getConvention(IMMFRAConvention.class, node.getImmFRAConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get IMM FRA convention with id " + node.getImmFRAConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitIMMSwapNode(final IMMSwapNode node) {
    final IMMSwapConvention convention = _conventionSource.getConvention(IMMSwapConvention.class, node.getSwapConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get swap convention with id " + node.getSwapConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitRateFutureNode(final RateFutureNode node) {
    final Convention convention = _conventionSource.getConvention(node.getFutureConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get future convention with id " + node.getFutureConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitSwapNode(final SwapNode node) {
    final Convention payConvention = _conventionSource.getConvention(node.getPayLegConvention());
    if (payConvention == null) {
      throw new OpenGammaRuntimeException("Could not get pay convention with id " + node.getPayLegConvention());
    }
    final Convention receiveConvention = _conventionSource.getConvention(node.getReceiveLegConvention());
    if (receiveConvention == null) {
      throw new OpenGammaRuntimeException("Could not get receive convention with id " + node.getReceiveLegConvention());
    }
    final Set<Currency> currencies = new HashSet<>(payConvention.accept(this));
    currencies.addAll(receiveConvention.accept(this));
    return currencies;
  }

  @Override
  public Set<Currency> visitThreeLegBasisSwapNode(final ThreeLegBasisSwapNode node) {
    final Convention payConvention = _conventionSource.getConvention(node.getPayLegConvention());
    if (payConvention == null) {
      throw new OpenGammaRuntimeException("Could not get pay convention with id " + node.getPayLegConvention());
    }
    final Convention receiveConvention = _conventionSource.getConvention(node.getReceiveLegConvention());
    if (receiveConvention == null) {
      throw new OpenGammaRuntimeException("Could not get receive convention with id " + node.getReceiveLegConvention());
    }
    final Convention spreadConvention = _conventionSource.getConvention(node.getSpreadLegConvention());
    if (spreadConvention == null) {
      throw new OpenGammaRuntimeException("Could not get spread convention with id " + node.getSpreadLegConvention());
    }
    final Set<Currency> currencies = new HashSet<>(payConvention.accept(this));
    currencies.addAll(receiveConvention.accept(this));
    currencies.addAll(spreadConvention.accept(this));
    return currencies;
  }

  @Override
  public Set<Currency> visitZeroCouponInflationNode(final ZeroCouponInflationNode node) {
    final Convention convention = _conventionSource.getConvention(InflationLegConvention.class, node.getInflationLegConvention());
    if (convention == null) {
      throw new OpenGammaRuntimeException("Could not get inflation leg convention with id " + node.getInflationLegConvention());
    }
    return convention.accept(this);
  }

  @Override
  public Set<Currency> visitCMSLegConvention(final CMSLegConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getSwapIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getSwapIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitCompoundingIborLegConvention(final CompoundingIborLegConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getIborIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getIborIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitDepositConvention(final DepositConvention convention) {
    return Collections.singleton(convention.getCurrency());
  }

  @Override
  public Set<Currency> visitEquityConvention(final EquityConvention convention) {
    return null;
  }

  @Override
  public Set<Currency> visitDeliverablePriceQuotedSwapFutureConvention(final DeliverablePriceQuotedSwapFutureConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getSwapConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getSwapConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitFederalFundsFutureConvention(final FederalFundsFutureConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitFXForwardAndSwapConvention(final FXForwardAndSwapConvention convention) {
    return null;
  }

  @Override
  public Set<Currency> visitFXSpotConvention(final FXSpotConvention convention) {
    return null;
  }

  @Override
  public Set<Currency> visitIborIndexConvention(final IborIndexConvention convention) {
    return Collections.singleton(convention.getCurrency());
  }

  @Override
  public Set<Currency> visitIMMFRAConvention(final IMMFRAConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitIMMSwapConvention(final IMMSwapConvention convention) {
    final Convention payConvention = _conventionSource.getConvention(convention.getPayLegConvention());
    if (payConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getPayLegConvention());
    }
    final Convention receiveConvention = _conventionSource.getConvention(convention.getReceiveLegConvention());
    if (receiveConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getReceiveLegConvention());
    }
    final Set<Currency> currencies = new HashSet<>(payConvention.accept(this));
    currencies.addAll(receiveConvention.accept(this));
    return currencies;
  }

  @Override
  public Set<Currency> visitInflationLegConvention(final InflationLegConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getPriceIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getPriceIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitInterestRateFutureConvention(final InterestRateFutureConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitOISLegConvention(final OISLegConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getOvernightIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getOvernightIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitOvernightIndexConvention(final OvernightIndexConvention convention) {
    return Collections.singleton(convention.getCurrency());
  }

  @Override
  public Set<Currency> visitPriceIndexConvention(final PriceIndexConvention convention) {
    return Collections.singleton(convention.getCurrency());
  }

  @Override
  public Set<Currency> visitSwapConvention(final SwapConvention convention) {
    final Convention payConvention = _conventionSource.getConvention(convention.getPayLegConvention());
    if (payConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getPayLegConvention());
    }
    final Convention receiveConvention = _conventionSource.getConvention(convention.getReceiveLegConvention());
    if (receiveConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getReceiveLegConvention());
    }
    final Set<Currency> currencies = new HashSet<>(payConvention.accept(this));
    currencies.addAll(receiveConvention.accept(this));
    return currencies;
  }

  @Override
  public Set<Currency> visitSwapFixedLegConvention(final SwapFixedLegConvention convention) {
    return Collections.singleton(convention.getCurrency());
  }

  @Override
  public Set<Currency> visitSwapIndexConvention(final SwapIndexConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getSwapConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getSwapConvention());
    }
    return underlyingConvention.accept(this);
  }

  @Override
  public Set<Currency> visitVanillaIborLegConvention(final VanillaIborLegConvention convention) {
    final Convention underlyingConvention = _conventionSource.getConvention(convention.getIborIndexConvention());
    if (underlyingConvention == null) {
      throw new OpenGammaRuntimeException("Could not get convention with id " + convention.getIborIndexConvention());
    }
    return underlyingConvention.accept(this);
  }

}
