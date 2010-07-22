/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.PropertyReadWrite;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.engine.position.PortfolioNode;
import com.opengamma.util.db.Paging;

/**
 * Result from searching for historic nodes.
 */
@BeanDefinition
public class PortfolioNodeSearchHistoricResult extends DirectBean {

  /**
   * The paging information.
   */
  @PropertyDefinition
  private Paging _paging;
  /**
   * The list of matched node documents.
   */
  @PropertyDefinition(readWrite = PropertyReadWrite.READ_ONLY)
  private List<PortfolioNodeDocument> _documents = new ArrayList<PortfolioNodeDocument>();

  /**
   * Creates an instance.
   */
  public PortfolioNodeSearchHistoricResult() {
  }

  /**
   * Gets the returned nodes from within the documents.
   * @return the nodes, not null
   */
  public List<PortfolioNode> getPortfolioNodes() {
    List<PortfolioNode> result = new ArrayList<PortfolioNode>();
    if (_documents != null) {
      for (PortfolioNodeDocument doc : _documents) {
        result.add(doc.getPortfolioNode());
      }
    }
    return result;
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code PortfolioNodeSearchHistoricResult}.
   * @return the meta-bean, not null
   */
  public static PortfolioNodeSearchHistoricResult.Meta meta() {
    return PortfolioNodeSearchHistoricResult.Meta.INSTANCE;
  }

  @Override
  public PortfolioNodeSearchHistoricResult.Meta metaBean() {
    return PortfolioNodeSearchHistoricResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -995747956:  // paging
        return getPaging();
      case 943542968:  // documents
        return getDocuments();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -995747956:  // paging
        setPaging((Paging) newValue);
        return;
      case 943542968:  // documents
        throw new UnsupportedOperationException("Property cannot be written: documents");
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the paging information.
   * @return the value of the property
   */
  public Paging getPaging() {
    return _paging;
  }

  /**
   * Sets the paging information.
   * @param paging  the new value of the property
   */
  public void setPaging(Paging paging) {
    this._paging = paging;
  }

  /**
   * Gets the the {@code paging} property.
   * @return the property, not null
   */
  public final Property<Paging> paging() {
    return metaBean().paging().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the list of matched node documents.
   * @return the value of the property
   */
  public List<PortfolioNodeDocument> getDocuments() {
    return _documents;
  }

  /**
   * Gets the the {@code documents} property.
   * @return the property, not null
   */
  public final Property<List<PortfolioNodeDocument>> documents() {
    return metaBean().documents().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code PortfolioNodeSearchHistoricResult}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code paging} property.
     */
    private final MetaProperty<Paging> _paging = DirectMetaProperty.ofReadWrite(this, "paging", Paging.class);
    /**
     * The meta-property for the {@code documents} property.
     */
    @SuppressWarnings("unchecked")
    private final MetaProperty<List<PortfolioNodeDocument>> _documents = DirectMetaProperty.ofReadOnly(this, "documents", (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings("unchecked")
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("paging", _paging);
      temp.put("documents", _documents);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public PortfolioNodeSearchHistoricResult createBean() {
      return new PortfolioNodeSearchHistoricResult();
    }

    @Override
    public Class<? extends PortfolioNodeSearchHistoricResult> beanType() {
      return PortfolioNodeSearchHistoricResult.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code paging} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Paging> paging() {
      return _paging;
    }

    /**
     * The meta-property for the {@code documents} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<PortfolioNodeDocument>> documents() {
      return _documents;
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
