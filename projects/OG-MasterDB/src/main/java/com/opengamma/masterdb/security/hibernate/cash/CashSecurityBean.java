/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.cash;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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

import com.opengamma.financial.security.cash.CashSecurity;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.DayCountBean;
import com.opengamma.masterdb.security.hibernate.ExternalIdBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link CashSecurity}.
 */
@BeanDefinition
public class CashSecurityBean extends SecurityBean {
  @PropertyDefinition
  private CurrencyBean _currency;
  @PropertyDefinition
  private ExternalIdBean _region;
  @PropertyDefinition
  private ZonedDateTimeBean _start;
  @PropertyDefinition
  private ZonedDateTimeBean _maturity;
  @PropertyDefinition
  private DayCountBean _dayCount;
  @PropertyDefinition
  private double _rate;
  @PropertyDefinition
  private double _amount;
    
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof CashSecurityBean)) {
      return false;
    }
    CashSecurityBean cash = (CashSecurityBean) other;
    return new EqualsBuilder()
      .append(getId(), cash.getId())
      .append(getCurrency(), cash.getCurrency())
      .append(getRegion(), cash.getRegion())
      .append(getStart(), cash.getStart())
      .append(getMaturity(), cash.getMaturity())
      .append(getDayCount(), cash.getDayCount())
      .append(getRate(), cash.getRate())
      .append(getAmount(), cash.getAmount()).isEquals();
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getCurrency())
      .append(getRegion())
      .append(getStart())
      .append(getMaturity())
      .append(getDayCount())
      .append(getRate())
      .append(getAmount())
      .toHashCode();
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CashSecurityBean}.
   * @return the meta-bean, not null
   */
  public static CashSecurityBean.Meta meta() {
    return CashSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CashSecurityBean.Meta.INSTANCE);
  }

  @Override
  public CashSecurityBean.Meta metaBean() {
    return CashSecurityBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  public CurrencyBean getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property
   */
  public void setCurrency(CurrencyBean currency) {
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the region.
   * @return the value of the property
   */
  public ExternalIdBean getRegion() {
    return _region;
  }

  /**
   * Sets the region.
   * @param region  the new value of the property
   */
  public void setRegion(ExternalIdBean region) {
    this._region = region;
  }

  /**
   * Gets the the {@code region} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> region() {
    return metaBean().region().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start.
   * @return the value of the property
   */
  public ZonedDateTimeBean getStart() {
    return _start;
  }

  /**
   * Sets the start.
   * @param start  the new value of the property
   */
  public void setStart(ZonedDateTimeBean start) {
    this._start = start;
  }

  /**
   * Gets the the {@code start} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> start() {
    return metaBean().start().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturity.
   * @return the value of the property
   */
  public ZonedDateTimeBean getMaturity() {
    return _maturity;
  }

  /**
   * Sets the maturity.
   * @param maturity  the new value of the property
   */
  public void setMaturity(ZonedDateTimeBean maturity) {
    this._maturity = maturity;
  }

  /**
   * Gets the the {@code maturity} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> maturity() {
    return metaBean().maturity().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the dayCount.
   * @return the value of the property
   */
  public DayCountBean getDayCount() {
    return _dayCount;
  }

  /**
   * Sets the dayCount.
   * @param dayCount  the new value of the property
   */
  public void setDayCount(DayCountBean dayCount) {
    this._dayCount = dayCount;
  }

  /**
   * Gets the the {@code dayCount} property.
   * @return the property, not null
   */
  public final Property<DayCountBean> dayCount() {
    return metaBean().dayCount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the rate.
   * @return the value of the property
   */
  public double getRate() {
    return _rate;
  }

  /**
   * Sets the rate.
   * @param rate  the new value of the property
   */
  public void setRate(double rate) {
    this._rate = rate;
  }

  /**
   * Gets the the {@code rate} property.
   * @return the property, not null
   */
  public final Property<Double> rate() {
    return metaBean().rate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the amount.
   * @return the value of the property
   */
  public double getAmount() {
    return _amount;
  }

  /**
   * Sets the amount.
   * @param amount  the new value of the property
   */
  public void setAmount(double amount) {
    this._amount = amount;
  }

  /**
   * Gets the the {@code amount} property.
   * @return the property, not null
   */
  public final Property<Double> amount() {
    return metaBean().amount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public CashSecurityBean clone() {
    return (CashSecurityBean) super.clone();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("CashSecurityBean{");
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
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency())).append(',').append(' ');
    buf.append("region").append('=').append(JodaBeanUtils.toString(getRegion())).append(',').append(' ');
    buf.append("start").append('=').append(JodaBeanUtils.toString(getStart())).append(',').append(' ');
    buf.append("maturity").append('=').append(JodaBeanUtils.toString(getMaturity())).append(',').append(' ');
    buf.append("dayCount").append('=').append(JodaBeanUtils.toString(getDayCount())).append(',').append(' ');
    buf.append("rate").append('=').append(JodaBeanUtils.toString(getRate())).append(',').append(' ');
    buf.append("amount").append('=').append(JodaBeanUtils.toString(getAmount())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CashSecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<CurrencyBean> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", CashSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code region} property.
     */
    private final MetaProperty<ExternalIdBean> _region = DirectMetaProperty.ofReadWrite(
        this, "region", CashSecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-property for the {@code start} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _start = DirectMetaProperty.ofReadWrite(
        this, "start", CashSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code maturity} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _maturity = DirectMetaProperty.ofReadWrite(
        this, "maturity", CashSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCountBean> _dayCount = DirectMetaProperty.ofReadWrite(
        this, "dayCount", CashSecurityBean.class, DayCountBean.class);
    /**
     * The meta-property for the {@code rate} property.
     */
    private final MetaProperty<Double> _rate = DirectMetaProperty.ofReadWrite(
        this, "rate", CashSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code amount} property.
     */
    private final MetaProperty<Double> _amount = DirectMetaProperty.ofReadWrite(
        this, "amount", CashSecurityBean.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "currency",
        "region",
        "start",
        "maturity",
        "dayCount",
        "rate",
        "amount");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return _currency;
        case -934795532:  // region
          return _region;
        case 109757538:  // start
          return _start;
        case 313843601:  // maturity
          return _maturity;
        case 1905311443:  // dayCount
          return _dayCount;
        case 3493088:  // rate
          return _rate;
        case -1413853096:  // amount
          return _amount;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CashSecurityBean> builder() {
      return new DirectBeanBuilder<CashSecurityBean>(new CashSecurityBean());
    }

    @Override
    public Class<? extends CashSecurityBean> beanType() {
      return CashSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code region} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> region() {
      return _region;
    }

    /**
     * The meta-property for the {@code start} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> start() {
      return _start;
    }

    /**
     * The meta-property for the {@code maturity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> maturity() {
      return _maturity;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DayCountBean> dayCount() {
      return _dayCount;
    }

    /**
     * The meta-property for the {@code rate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> rate() {
      return _rate;
    }

    /**
     * The meta-property for the {@code amount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> amount() {
      return _amount;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return ((CashSecurityBean) bean).getCurrency();
        case -934795532:  // region
          return ((CashSecurityBean) bean).getRegion();
        case 109757538:  // start
          return ((CashSecurityBean) bean).getStart();
        case 313843601:  // maturity
          return ((CashSecurityBean) bean).getMaturity();
        case 1905311443:  // dayCount
          return ((CashSecurityBean) bean).getDayCount();
        case 3493088:  // rate
          return ((CashSecurityBean) bean).getRate();
        case -1413853096:  // amount
          return ((CashSecurityBean) bean).getAmount();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          ((CashSecurityBean) bean).setCurrency((CurrencyBean) newValue);
          return;
        case -934795532:  // region
          ((CashSecurityBean) bean).setRegion((ExternalIdBean) newValue);
          return;
        case 109757538:  // start
          ((CashSecurityBean) bean).setStart((ZonedDateTimeBean) newValue);
          return;
        case 313843601:  // maturity
          ((CashSecurityBean) bean).setMaturity((ZonedDateTimeBean) newValue);
          return;
        case 1905311443:  // dayCount
          ((CashSecurityBean) bean).setDayCount((DayCountBean) newValue);
          return;
        case 3493088:  // rate
          ((CashSecurityBean) bean).setRate((Double) newValue);
          return;
        case -1413853096:  // amount
          ((CashSecurityBean) bean).setAmount((Double) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
