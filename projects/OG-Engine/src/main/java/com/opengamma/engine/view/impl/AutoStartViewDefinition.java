/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.impl;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicImmutableBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.engine.view.execution.ViewExecutionOptions;
import com.opengamma.id.UniqueId;
import com.opengamma.util.ArgumentChecker;

/**
 * Defines a view and execution options that should be started automatically
 * when the engine starts.
 */
@BeanDefinition
public final class AutoStartViewDefinition implements ImmutableBean {

  /**
   * Identifier for the view definition to be started.
   */
  @PropertyDefinition
  private final UniqueId _viewDefinitionId;

  /**
   * Execution options to be used when starting the view.
   */
  @PropertyDefinition
  private final ViewExecutionOptions _executionOptions;

  /**
   * Constructor for the definition.
   *
   * @param underlyingViewDefinitionId the id of the view to be started
   * @param executionOptions execution options to be used when starting the view
   */
  @ImmutableConstructor
  public AutoStartViewDefinition(
      UniqueId underlyingViewDefinitionId,
      ViewExecutionOptions executionOptions) {

    ArgumentChecker.notNull(underlyingViewDefinitionId, "underlyingViewDefinitionId");
    ArgumentChecker.notNull(executionOptions, "executionOptions");
    this._viewDefinitionId = underlyingViewDefinitionId;
    this._executionOptions = executionOptions;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AutoStartViewDefinition}.
   * @return the meta-bean, not null
   */
  public static AutoStartViewDefinition.Meta meta() {
    return AutoStartViewDefinition.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AutoStartViewDefinition.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   *
   * @return the builder, not null
   */
  public static AutoStartViewDefinition.Builder builder() {
    return new AutoStartViewDefinition.Builder();
  }

  @Override
  public AutoStartViewDefinition.Meta metaBean() {
    return AutoStartViewDefinition.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets identifier for the view definition to be started.
   * @return the value of the property
   */
  public UniqueId getViewDefinitionId() {
    return _viewDefinitionId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets execution options to be used when starting the view.
   * @return the value of the property
   */
  public ViewExecutionOptions getExecutionOptions() {
    return _executionOptions;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public AutoStartViewDefinition clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AutoStartViewDefinition other = (AutoStartViewDefinition) obj;
      return JodaBeanUtils.equal(getViewDefinitionId(), other.getViewDefinitionId()) &&
          JodaBeanUtils.equal(getExecutionOptions(), other.getExecutionOptions());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getViewDefinitionId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExecutionOptions());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("AutoStartViewDefinition{");
    buf.append("viewDefinitionId").append('=').append(getViewDefinitionId()).append(',').append(' ');
    buf.append("executionOptions").append('=').append(JodaBeanUtils.toString(getExecutionOptions()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AutoStartViewDefinition}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code viewDefinitionId} property.
     */
    private final MetaProperty<UniqueId> _viewDefinitionId = DirectMetaProperty.ofImmutable(
        this, "viewDefinitionId", AutoStartViewDefinition.class, UniqueId.class);
    /**
     * The meta-property for the {@code executionOptions} property.
     */
    private final MetaProperty<ViewExecutionOptions> _executionOptions = DirectMetaProperty.ofImmutable(
        this, "executionOptions", AutoStartViewDefinition.class, ViewExecutionOptions.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "viewDefinitionId",
        "executionOptions");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -545262317:  // viewDefinitionId
          return _viewDefinitionId;
        case -1448089498:  // executionOptions
          return _executionOptions;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public AutoStartViewDefinition.Builder builder() {
      return new AutoStartViewDefinition.Builder();
    }

    @Override
    public Class<? extends AutoStartViewDefinition> beanType() {
      return AutoStartViewDefinition.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code viewDefinitionId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<UniqueId> viewDefinitionId() {
      return _viewDefinitionId;
    }

    /**
     * The meta-property for the {@code executionOptions} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ViewExecutionOptions> executionOptions() {
      return _executionOptions;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -545262317:  // viewDefinitionId
          return ((AutoStartViewDefinition) bean).getViewDefinitionId();
        case -1448089498:  // executionOptions
          return ((AutoStartViewDefinition) bean).getExecutionOptions();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code AutoStartViewDefinition}.
   */
  public static final class Builder extends BasicImmutableBeanBuilder<AutoStartViewDefinition> {

    private UniqueId _viewDefinitionId;
    private ViewExecutionOptions _executionOptions;

    /**
     * Restricted constructor.
     */
    private Builder() {
      super(AutoStartViewDefinition.Meta.INSTANCE);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(AutoStartViewDefinition beanToCopy) {
      super(AutoStartViewDefinition.Meta.INSTANCE);
      this._viewDefinitionId = beanToCopy.getViewDefinitionId();
      this._executionOptions = beanToCopy.getExecutionOptions();
    }

    //-----------------------------------------------------------------------
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -545262317:  // viewDefinitionId
          this._viewDefinitionId = (UniqueId) newValue;
          break;
        case -1448089498:  // executionOptions
          this._executionOptions = (ViewExecutionOptions) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public AutoStartViewDefinition build() {
      return new AutoStartViewDefinition(
          _viewDefinitionId,
          _executionOptions);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code viewDefinitionId} property in the builder.
     * @param viewDefinitionId  the new value
     * @return this, for chaining, not null
     */
    public Builder viewDefinitionId(UniqueId viewDefinitionId) {
      this._viewDefinitionId = viewDefinitionId;
      return this;
    }

    /**
     * Sets the {@code executionOptions} property in the builder.
     * @param executionOptions  the new value
     * @return this, for chaining, not null
     */
    public Builder executionOptions(ViewExecutionOptions executionOptions) {
      this._executionOptions = executionOptions;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("AutoStartViewDefinition.Builder{");
      buf.append("viewDefinitionId").append('=').append(_viewDefinitionId).append(',').append(' ');
      buf.append("executionOptions").append('=').append(_executionOptions);
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
