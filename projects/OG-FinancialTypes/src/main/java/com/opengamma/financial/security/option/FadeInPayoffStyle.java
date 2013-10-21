/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.option;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * The fade in payoff style.
 */
@BeanDefinition
public class FadeInPayoffStyle extends PayoffStyle {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The upper bound.
   */
  @PropertyDefinition
  private double _lowerBound;
  /**
   * The lower bound.
   */
  @PropertyDefinition
  private double _upperBound;

  /**
   * Creates an instance.
   */
  private FadeInPayoffStyle() {
  }

  /**
   * Creates an instance.
   * 
   * @param upperBound  the upper bound
   * @param lowerBound  the lower bound
   */
  public FadeInPayoffStyle(double upperBound, double lowerBound) {
    setUpperBound(upperBound);
    setLowerBound(lowerBound);
  }

  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(PayoffStyleVisitor<T> visitor) {
    return visitor.visitFadeInPayoffStyle(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FadeInPayoffStyle}.
   * @return the meta-bean, not null
   */
  public static FadeInPayoffStyle.Meta meta() {
    return FadeInPayoffStyle.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FadeInPayoffStyle.Meta.INSTANCE);
  }

  @Override
  public FadeInPayoffStyle.Meta metaBean() {
    return FadeInPayoffStyle.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the upper bound.
   * @return the value of the property
   */
  public double getLowerBound() {
    return _lowerBound;
  }

  /**
   * Sets the upper bound.
   * @param lowerBound  the new value of the property
   */
  public void setLowerBound(double lowerBound) {
    this._lowerBound = lowerBound;
  }

  /**
   * Gets the the {@code lowerBound} property.
   * @return the property, not null
   */
  public final Property<Double> lowerBound() {
    return metaBean().lowerBound().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the lower bound.
   * @return the value of the property
   */
  public double getUpperBound() {
    return _upperBound;
  }

  /**
   * Sets the lower bound.
   * @param upperBound  the new value of the property
   */
  public void setUpperBound(double upperBound) {
    this._upperBound = upperBound;
  }

  /**
   * Gets the the {@code upperBound} property.
   * @return the property, not null
   */
  public final Property<Double> upperBound() {
    return metaBean().upperBound().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FadeInPayoffStyle clone() {
    return (FadeInPayoffStyle) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FadeInPayoffStyle other = (FadeInPayoffStyle) obj;
      return JodaBeanUtils.equal(getLowerBound(), other.getLowerBound()) &&
          JodaBeanUtils.equal(getUpperBound(), other.getUpperBound()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getLowerBound());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUpperBound());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("FadeInPayoffStyle{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("lowerBound").append('=').append(JodaBeanUtils.toString(getLowerBound())).append(',').append(' ');
    buf.append("upperBound").append('=').append(JodaBeanUtils.toString(getUpperBound())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FadeInPayoffStyle}.
   */
  public static class Meta extends PayoffStyle.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code lowerBound} property.
     */
    private final MetaProperty<Double> _lowerBound = DirectMetaProperty.ofReadWrite(
        this, "lowerBound", FadeInPayoffStyle.class, Double.TYPE);
    /**
     * The meta-property for the {@code upperBound} property.
     */
    private final MetaProperty<Double> _upperBound = DirectMetaProperty.ofReadWrite(
        this, "upperBound", FadeInPayoffStyle.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "lowerBound",
        "upperBound");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1200084733:  // lowerBound
          return _lowerBound;
        case -1690761732:  // upperBound
          return _upperBound;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FadeInPayoffStyle> builder() {
      return new DirectBeanBuilder<FadeInPayoffStyle>(new FadeInPayoffStyle());
    }

    @Override
    public Class<? extends FadeInPayoffStyle> beanType() {
      return FadeInPayoffStyle.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code lowerBound} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> lowerBound() {
      return _lowerBound;
    }

    /**
     * The meta-property for the {@code upperBound} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> upperBound() {
      return _upperBound;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1200084733:  // lowerBound
          return ((FadeInPayoffStyle) bean).getLowerBound();
        case -1690761732:  // upperBound
          return ((FadeInPayoffStyle) bean).getUpperBound();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1200084733:  // lowerBound
          ((FadeInPayoffStyle) bean).setLowerBound((Double) newValue);
          return;
        case -1690761732:  // upperBound
          ((FadeInPayoffStyle) bean).setUpperBound((Double) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
