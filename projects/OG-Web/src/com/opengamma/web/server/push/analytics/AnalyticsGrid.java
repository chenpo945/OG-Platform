/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.server.push.analytics;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.DataNotFoundException;
import com.opengamma.engine.view.InMemoryViewComputationResultModel;
import com.opengamma.engine.view.ViewComputationResultModel;
import com.opengamma.util.ArgumentChecker;

/**
 *
 */
/* package */ class AnalyticsGrid {

  protected final AnalyticsGridStructure _gridStructure;

  private final Map<String, AnalyticsViewport> _viewports = new HashMap<String, AnalyticsViewport>();

  private ViewComputationResultModel _latestResults = new InMemoryViewComputationResultModel();

  protected AnalyticsGrid(AnalyticsGridStructure gridStructure) {
    ArgumentChecker.notNull(gridStructure, "gridStructure");
    _gridStructure = gridStructure;
  }

  /**
   * @return An empty grid structure with no rows or columns
   */
  /* package */ static AnalyticsGrid empty() {
    return new AnalyticsGrid(AnalyticsGridStructure.empty());
  }

  private AnalyticsViewport getViewport(String viewportId) {
    AnalyticsViewport viewport = _viewports.get(viewportId);
    if (viewport == null) {
      throw new DataNotFoundException("No viewport found with ID " + viewportId);
    }
    return viewport;
  }

  /* package */ void updateResults(ViewComputationResultModel fullResult, AnalyticsHistory history) {
    _latestResults = fullResult;
    // TODO should the row and cols be looked up here and passed to the viewports?
    // look up col index in _columns
    // iterate over _targets, query results for each target
    for (AnalyticsViewport viewport : _viewports.values()) {
      viewport.updateResults(fullResult, history);
    }
  }

  /* package */ String createViewport(String viewportId,
                                      ViewportSpecification viewportSpecification,
                                      AnalyticsHistory history) {
    if (_viewports.containsKey(viewportId)) {
      throw new IllegalArgumentException("Viewport ID " + viewportId + " is already in use");
    }
    _viewports.put(viewportId, new AnalyticsViewport(_gridStructure, viewportSpecification, _latestResults, history));
    return viewportId;
  }

  /* package */ void updateViewport(String viewportId,
                                    ViewportSpecification viewportSpecification,
                                    AnalyticsHistory history) {
    getViewport(viewportId).update(viewportSpecification, _latestResults, history);
  }

  /* package */ void deleteViewport(String viewportId) {
    AnalyticsViewport viewport = _viewports.remove(viewportId);
    if (viewport == null) {
      throw new DataNotFoundException("No viewport found with ID " + viewportId);
    }
  }

  /* package */ ViewportResults getData(String viewportId) {
    return getViewport(viewportId).getData();
  }
}
