/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.capfloor;

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

import com.opengamma.financial.security.capfloor.CapFloorCMSSpreadSecurity;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.DayCountBean;
import com.opengamma.masterdb.security.hibernate.ExternalIdBean;
import com.opengamma.masterdb.security.hibernate.FrequencyBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link CapFloorCMSSpreadSecurity}.
 */
@BeanDefinition
public class CapFloorCMSSpreadSecurityBean extends SecurityBean {
  @PropertyDefinition
  private CurrencyBean _currency;
  @PropertyDefinition
  private DayCountBean _dayCount;
  @PropertyDefinition
  private FrequencyBean _frequency;
  @PropertyDefinition
  private boolean _cap;
  @PropertyDefinition
  private boolean _payer;
  @PropertyDefinition
  private ExternalIdBean _longIdentifier;
  @PropertyDefinition
  private ZonedDateTimeBean _maturityDate;
  @PropertyDefinition
  private double _notional;
  @PropertyDefinition
  private ExternalIdBean _shortIdentifier;
  @PropertyDefinition
  private ZonedDateTimeBean _startDate;
  @PropertyDefinition
  private double _strike;
  
  //------------------------------------------------------------------------------
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof CapFloorCMSSpreadSecurityBean)) {
      return false;
    }
    CapFloorCMSSpreadSecurityBean capFloor = (CapFloorCMSSpreadSecurityBean) other;
    return new EqualsBuilder()
        .append(getId(), capFloor.getId())
        .append(getNotional(), capFloor.getNotional())
        .append(getCurrency(), capFloor.getCurrency())
        .append(getDayCount(), capFloor.getDayCount())
        .append(getFrequency(), capFloor.getFrequency())
        .append(getMaturityDate(), capFloor.getMaturityDate())
        .append(getStartDate(), capFloor.getStartDate())
        .append(getStrike(), capFloor.getStrike())
        .append(getLongIdentifier(), capFloor.getLongIdentifier())
        .append(getShortIdentifier(), capFloor.getShortIdentifier())
        .append(isCap(), capFloor.isCap())
        .append(isPayer(), capFloor.isPayer())
        .isEquals();
  }
  
  //------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(getNotional())
        .append(getCurrency())
        .append(getDayCount())
        .append(getFrequency())
        .append(getMaturityDate())
        .append(getStartDate())
        .append(getStrike())
        .append(getLongIdentifier())
        .append(getShortIdentifier())
        .append(isCap())
        .append(isPayer())
        .toHashCode();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CapFloorCMSSpreadSecurityBean}.
   * @return the meta-bean, not null
   */
  public static CapFloorCMSSpreadSecurityBean.Meta meta() {
    return CapFloorCMSSpreadSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CapFloorCMSSpreadSecurityBean.Meta.INSTANCE);
  }

  @Override
  public CapFloorCMSSpreadSecurityBean.Meta metaBean() {
    return CapFloorCMSSpreadSecurityBean.Meta.INSTANCE;
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
   * Gets the frequency.
   * @return the value of the property
   */
  public FrequencyBean getFrequency() {
    return _frequency;
  }

  /**
   * Sets the frequency.
   * @param frequency  the new value of the property
   */
  public void setFrequency(FrequencyBean frequency) {
    this._frequency = frequency;
  }

  /**
   * Gets the the {@code frequency} property.
   * @return the property, not null
   */
  public final Property<FrequencyBean> frequency() {
    return metaBean().frequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cap.
   * @return the value of the property
   */
  public boolean isCap() {
    return _cap;
  }

  /**
   * Sets the cap.
   * @param cap  the new value of the property
   */
  public void setCap(boolean cap) {
    this._cap = cap;
  }

  /**
   * Gets the the {@code cap} property.
   * @return the property, not null
   */
  public final Property<Boolean> cap() {
    return metaBean().cap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payer.
   * @return the value of the property
   */
  public boolean isPayer() {
    return _payer;
  }

  /**
   * Sets the payer.
   * @param payer  the new value of the property
   */
  public void setPayer(boolean payer) {
    this._payer = payer;
  }

  /**
   * Gets the the {@code payer} property.
   * @return the property, not null
   */
  public final Property<Boolean> payer() {
    return metaBean().payer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the longIdentifier.
   * @return the value of the property
   */
  public ExternalIdBean getLongIdentifier() {
    return _longIdentifier;
  }

  /**
   * Sets the longIdentifier.
   * @param longIdentifier  the new value of the property
   */
  public void setLongIdentifier(ExternalIdBean longIdentifier) {
    this._longIdentifier = longIdentifier;
  }

  /**
   * Gets the the {@code longIdentifier} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> longIdentifier() {
    return metaBean().longIdentifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturityDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getMaturityDate() {
    return _maturityDate;
  }

  /**
   * Sets the maturityDate.
   * @param maturityDate  the new value of the property
   */
  public void setMaturityDate(ZonedDateTimeBean maturityDate) {
    this._maturityDate = maturityDate;
  }

  /**
   * Gets the the {@code maturityDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> maturityDate() {
    return metaBean().maturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the notional.
   * @return the value of the property
   */
  public double getNotional() {
    return _notional;
  }

  /**
   * Sets the notional.
   * @param notional  the new value of the property
   */
  public void setNotional(double notional) {
    this._notional = notional;
  }

  /**
   * Gets the the {@code notional} property.
   * @return the property, not null
   */
  public final Property<Double> notional() {
    return metaBean().notional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the shortIdentifier.
   * @return the value of the property
   */
  public ExternalIdBean getShortIdentifier() {
    return _shortIdentifier;
  }

  /**
   * Sets the shortIdentifier.
   * @param shortIdentifier  the new value of the property
   */
  public void setShortIdentifier(ExternalIdBean shortIdentifier) {
    this._shortIdentifier = shortIdentifier;
  }

  /**
   * Gets the the {@code shortIdentifier} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> shortIdentifier() {
    return metaBean().shortIdentifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the startDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getStartDate() {
    return _startDate;
  }

  /**
   * Sets the startDate.
   * @param startDate  the new value of the property
   */
  public void setStartDate(ZonedDateTimeBean startDate) {
    this._startDate = startDate;
  }

  /**
   * Gets the the {@code startDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> startDate() {
    return metaBean().startDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike.
   * @return the value of the property
   */
  public double getStrike() {
    return _strike;
  }

  /**
   * Sets the strike.
   * @param strike  the new value of the property
   */
  public void setStrike(double strike) {
    this._strike = strike;
  }

  /**
   * Gets the the {@code strike} property.
   * @return the property, not null
   */
  public final Property<Double> strike() {
    return metaBean().strike().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public CapFloorCMSSpreadSecurityBean clone() {
    return (CapFloorCMSSpreadSecurityBean) super.clone();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(384);
    buf.append("CapFloorCMSSpreadSecurityBean{");
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
    buf.append("dayCount").append('=').append(JodaBeanUtils.toString(getDayCount())).append(',').append(' ');
    buf.append("frequency").append('=').append(JodaBeanUtils.toString(getFrequency())).append(',').append(' ');
    buf.append("cap").append('=').append(JodaBeanUtils.toString(isCap())).append(',').append(' ');
    buf.append("payer").append('=').append(JodaBeanUtils.toString(isPayer())).append(',').append(' ');
    buf.append("longIdentifier").append('=').append(JodaBeanUtils.toString(getLongIdentifier())).append(',').append(' ');
    buf.append("maturityDate").append('=').append(JodaBeanUtils.toString(getMaturityDate())).append(',').append(' ');
    buf.append("notional").append('=').append(JodaBeanUtils.toString(getNotional())).append(',').append(' ');
    buf.append("shortIdentifier").append('=').append(JodaBeanUtils.toString(getShortIdentifier())).append(',').append(' ');
    buf.append("startDate").append('=').append(JodaBeanUtils.toString(getStartDate())).append(',').append(' ');
    buf.append("strike").append('=').append(JodaBeanUtils.toString(getStrike())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CapFloorCMSSpreadSecurityBean}.
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
        this, "currency", CapFloorCMSSpreadSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCountBean> _dayCount = DirectMetaProperty.ofReadWrite(
        this, "dayCount", CapFloorCMSSpreadSecurityBean.class, DayCountBean.class);
    /**
     * The meta-property for the {@code frequency} property.
     */
    private final MetaProperty<FrequencyBean> _frequency = DirectMetaProperty.ofReadWrite(
        this, "frequency", CapFloorCMSSpreadSecurityBean.class, FrequencyBean.class);
    /**
     * The meta-property for the {@code cap} property.
     */
    private final MetaProperty<Boolean> _cap = DirectMetaProperty.ofReadWrite(
        this, "cap", CapFloorCMSSpreadSecurityBean.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code payer} property.
     */
    private final MetaProperty<Boolean> _payer = DirectMetaProperty.ofReadWrite(
        this, "payer", CapFloorCMSSpreadSecurityBean.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code longIdentifier} property.
     */
    private final MetaProperty<ExternalIdBean> _longIdentifier = DirectMetaProperty.ofReadWrite(
        this, "longIdentifier", CapFloorCMSSpreadSecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-property for the {@code maturityDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _maturityDate = DirectMetaProperty.ofReadWrite(
        this, "maturityDate", CapFloorCMSSpreadSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code notional} property.
     */
    private final MetaProperty<Double> _notional = DirectMetaProperty.ofReadWrite(
        this, "notional", CapFloorCMSSpreadSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code shortIdentifier} property.
     */
    private final MetaProperty<ExternalIdBean> _shortIdentifier = DirectMetaProperty.ofReadWrite(
        this, "shortIdentifier", CapFloorCMSSpreadSecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _startDate = DirectMetaProperty.ofReadWrite(
        this, "startDate", CapFloorCMSSpreadSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Double> _strike = DirectMetaProperty.ofReadWrite(
        this, "strike", CapFloorCMSSpreadSecurityBean.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "currency",
        "dayCount",
        "frequency",
        "cap",
        "payer",
        "longIdentifier",
        "maturityDate",
        "notional",
        "shortIdentifier",
        "startDate",
        "strike");

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
        case 1905311443:  // dayCount
          return _dayCount;
        case -70023844:  // frequency
          return _frequency;
        case 98258:  // cap
          return _cap;
        case 106443605:  // payer
          return _payer;
        case 18113605:  // longIdentifier
          return _longIdentifier;
        case -414641441:  // maturityDate
          return _maturityDate;
        case 1585636160:  // notional
          return _notional;
        case -2054053307:  // shortIdentifier
          return _shortIdentifier;
        case -2129778896:  // startDate
          return _startDate;
        case -891985998:  // strike
          return _strike;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CapFloorCMSSpreadSecurityBean> builder() {
      return new DirectBeanBuilder<CapFloorCMSSpreadSecurityBean>(new CapFloorCMSSpreadSecurityBean());
    }

    @Override
    public Class<? extends CapFloorCMSSpreadSecurityBean> beanType() {
      return CapFloorCMSSpreadSecurityBean.class;
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
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DayCountBean> dayCount() {
      return _dayCount;
    }

    /**
     * The meta-property for the {@code frequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FrequencyBean> frequency() {
      return _frequency;
    }

    /**
     * The meta-property for the {@code cap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> cap() {
      return _cap;
    }

    /**
     * The meta-property for the {@code payer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> payer() {
      return _payer;
    }

    /**
     * The meta-property for the {@code longIdentifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> longIdentifier() {
      return _longIdentifier;
    }

    /**
     * The meta-property for the {@code maturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> maturityDate() {
      return _maturityDate;
    }

    /**
     * The meta-property for the {@code notional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> notional() {
      return _notional;
    }

    /**
     * The meta-property for the {@code shortIdentifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> shortIdentifier() {
      return _shortIdentifier;
    }

    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> startDate() {
      return _startDate;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> strike() {
      return _strike;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          return ((CapFloorCMSSpreadSecurityBean) bean).getCurrency();
        case 1905311443:  // dayCount
          return ((CapFloorCMSSpreadSecurityBean) bean).getDayCount();
        case -70023844:  // frequency
          return ((CapFloorCMSSpreadSecurityBean) bean).getFrequency();
        case 98258:  // cap
          return ((CapFloorCMSSpreadSecurityBean) bean).isCap();
        case 106443605:  // payer
          return ((CapFloorCMSSpreadSecurityBean) bean).isPayer();
        case 18113605:  // longIdentifier
          return ((CapFloorCMSSpreadSecurityBean) bean).getLongIdentifier();
        case -414641441:  // maturityDate
          return ((CapFloorCMSSpreadSecurityBean) bean).getMaturityDate();
        case 1585636160:  // notional
          return ((CapFloorCMSSpreadSecurityBean) bean).getNotional();
        case -2054053307:  // shortIdentifier
          return ((CapFloorCMSSpreadSecurityBean) bean).getShortIdentifier();
        case -2129778896:  // startDate
          return ((CapFloorCMSSpreadSecurityBean) bean).getStartDate();
        case -891985998:  // strike
          return ((CapFloorCMSSpreadSecurityBean) bean).getStrike();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 575402001:  // currency
          ((CapFloorCMSSpreadSecurityBean) bean).setCurrency((CurrencyBean) newValue);
          return;
        case 1905311443:  // dayCount
          ((CapFloorCMSSpreadSecurityBean) bean).setDayCount((DayCountBean) newValue);
          return;
        case -70023844:  // frequency
          ((CapFloorCMSSpreadSecurityBean) bean).setFrequency((FrequencyBean) newValue);
          return;
        case 98258:  // cap
          ((CapFloorCMSSpreadSecurityBean) bean).setCap((Boolean) newValue);
          return;
        case 106443605:  // payer
          ((CapFloorCMSSpreadSecurityBean) bean).setPayer((Boolean) newValue);
          return;
        case 18113605:  // longIdentifier
          ((CapFloorCMSSpreadSecurityBean) bean).setLongIdentifier((ExternalIdBean) newValue);
          return;
        case -414641441:  // maturityDate
          ((CapFloorCMSSpreadSecurityBean) bean).setMaturityDate((ZonedDateTimeBean) newValue);
          return;
        case 1585636160:  // notional
          ((CapFloorCMSSpreadSecurityBean) bean).setNotional((Double) newValue);
          return;
        case -2054053307:  // shortIdentifier
          ((CapFloorCMSSpreadSecurityBean) bean).setShortIdentifier((ExternalIdBean) newValue);
          return;
        case -2129778896:  // startDate
          ((CapFloorCMSSpreadSecurityBean) bean).setStartDate((ZonedDateTimeBean) newValue);
          return;
        case -891985998:  // strike
          ((CapFloorCMSSpreadSecurityBean) bean).setStrike((Double) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
