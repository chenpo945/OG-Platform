/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.depgraph;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetSpecification;
import com.opengamma.engine.function.MarketDataSourcingFunction;
import com.opengamma.engine.function.ParameterizedFunction;
import com.opengamma.engine.marketdata.availability.MarketDataNotSatisfiableException;
import com.opengamma.engine.target.ComputationTargetReferenceVisitor;
import com.opengamma.engine.target.ComputationTargetRequirement;
import com.opengamma.engine.target.lazy.LazyComputationTargetResolver;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.util.async.BlockingOperation;
import com.opengamma.util.tuple.Triple;

/* package */final class GetFunctionsStep extends ResolveTask.State {

  private static final Logger s_logger = LoggerFactory.getLogger(GetFunctionsStep.class);

  public GetFunctionsStep(final ResolveTask task) {
    super(task);
  }

  private static final ComputationTargetReferenceVisitor<Object> s_getTargetValue = new ComputationTargetReferenceVisitor<Object>() {

    @Override
    public Object visitComputationTargetRequirement(final ComputationTargetRequirement requirement) {
      return requirement.getIdentifiers();
    }

    @Override
    public Object visitComputationTargetSpecification(final ComputationTargetSpecification specification) {
      // If the object couldn't be resolved, then the rogue UID is the best we can present
      return specification.getUniqueId();
    }

  };

  @Override
  protected boolean run(final GraphBuildingContext context) {
    boolean missing = false;
    ValueSpecification marketDataSpec = null;
    ComputationTargetSpecification targetSpec = null;
    ComputationTarget target = null;
    Object targetValue = null;
    BlockingOperation.off();
    try {
      targetSpec = getTargetSpecification(context);
      if (targetSpec != null) {
        target = LazyComputationTargetResolver.resolve(context.getCompilationContext().getComputationTargetResolver(), targetSpec);
        if (target != null) {
          targetValue = target.getValue();
        } else {
          targetValue = getValueRequirement().getTargetReference().accept(s_getTargetValue);
        }
      } else {
        targetValue = getValueRequirement().getTargetReference().accept(s_getTargetValue);
      }
      marketDataSpec = context.getMarketDataAvailabilityProvider().getAvailability(targetSpec, targetValue, getValueRequirement());
    } catch (final BlockingOperation e) {
      return false;
    } catch (final MarketDataNotSatisfiableException e) {
      missing = true;
    } finally {
      BlockingOperation.on();
    }
    if (marketDataSpec != null) {
      s_logger.info("Found live data for {}", getValueRequirement());
      if ((getValueRequirement().getValueName() == marketDataSpec.getValueName())
          && getValueRequirement().getConstraints().isSatisfiedBy(marketDataSpec.getProperties())) {
        final MarketDataSourcingFunction function = MarketDataSourcingFunction.INSTANCE;
        final ValueSpecification resultSpec = context.simplifyType(marketDataSpec);
        final ResolvedValue resolvedValue = createResult(resultSpec, new ParameterizedFunction(function, function.getParameters(getValueRequirement())), Collections.<ValueSpecification>emptySet(),
            Collections.singleton(resultSpec));
        final ResolvedValueProducer producer = new SingleResolvedValueProducer(getValueRequirement(), resolvedValue);
        final ResolvedValueProducer existing = context.declareTaskProducing(resultSpec, getTask(), producer);
        if (existing == producer) {
          context.declareProduction(resolvedValue);
          if (!pushResult(context, resolvedValue, true)) {
            throw new IllegalStateException(resolvedValue + " rejected by pushResult");
          }
          // Leave in current state; will go to finished after being pumped
        } else {
          producer.release(context);
          existing.addCallback(context, new ResolvedValueCallback() {

            @Override
            public void resolved(final GraphBuildingContext context, final ValueRequirement valueRequirement, final ResolvedValue resolvedValue, final ResolutionPump pump) {
              if (pump != null) {
                pump.close(context);
              }
              if (!pushResult(context, resolvedValue, true)) {
                throw new IllegalStateException(resolvedValue + " rejected by pushResult");
              }
              // Leave in current state; will go to finished after being pumped
            }

            @Override
            public void failed(final GraphBuildingContext context, final ValueRequirement value, final ResolutionFailure failure) {
              storeFailure(failure);
              setTaskStateFinished(context);
            }

          });
          existing.release(context);
        }
        // Leave in current state; will go to finished after being pumped
      } else {
        // A well behaved market data provider shouldn't do this, treat as missing market data
        s_logger.warn("Live data {} cannot satisfy {}", marketDataSpec, getValueRequirement());
        storeFailure(context.marketDataMissing(getValueRequirement()));
        setTaskStateFinished(context);
      }
    } else {
      if (missing) {
        s_logger.info("Missing market data for {}", getValueRequirement());
        storeFailure(context.marketDataMissing(getValueRequirement()));
        setTaskStateFinished(context);
      } else {
        if (target != null) {
          final Iterator<Triple<ParameterizedFunction, ValueSpecification, Collection<ValueSpecification>>> itr = context.getFunctionResolver().resolveFunction(
              getValueRequirement().getValueName(), target, getValueRequirement().getConstraints());
          if (itr.hasNext()) {
            s_logger.debug("Found functions for {}", getValueRequirement());
            setRunnableTaskState(new NextFunctionStep(getTask(), itr), context);
          } else {
            s_logger.info("No functions for {}", getValueRequirement());
            storeFailure(context.noFunctions(getValueRequirement()));
            setTaskStateFinished(context);
          }
        } else {
          s_logger.info("No functions for unresolved target {}", getValueRequirement());
          storeFailure(context.couldNotResolve(getValueRequirement()));
          setTaskStateFinished(context);
        }
      }
    }
    return true;
  }

  @Override
  protected void pump(final GraphBuildingContext context) {
    // Only had one market data result so go to finished state
    setTaskStateFinished(context);
  }

  @Override
  protected boolean isActive() {
    // Get functions has no background behavior - if run isn't called, nothing will happen
    return false;
  }

  @Override
  public String toString() {
    return "GET_FUNCTIONS" + getObjectId();
  }

}
