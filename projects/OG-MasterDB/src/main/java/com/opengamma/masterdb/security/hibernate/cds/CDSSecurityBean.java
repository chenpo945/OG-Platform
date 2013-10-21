/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security.hibernate.cds;

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

import com.opengamma.financial.security.cds.CDSSecurity;
import com.opengamma.masterdb.security.hibernate.BusinessDayConventionBean;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.DayCountBean;
import com.opengamma.masterdb.security.hibernate.FrequencyBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.StubTypeBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link CDSSecurity}.
 * @author Martin Traverse
 */
@BeanDefinition
public class CDSSecurityBean extends SecurityBean {

  @PropertyDefinition
  private double _notional;
  
  @PropertyDefinition
  private double _recoveryRate;
  
  @PropertyDefinition
  private double _spread;
  
  @PropertyDefinition(validate = "notNull")
  private CurrencyBean _currency;
  
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTimeBean _maturity;
  
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTimeBean _startDate;
  
  @PropertyDefinition(validate = "notNull")
  private FrequencyBean _premiumFrequency;
  
  @PropertyDefinition(validate = "notNull")
  private DayCountBean _dayCount;
  
  @PropertyDefinition(validate = "notNull")
  private BusinessDayConventionBean _businessDayConvention;
  
  @PropertyDefinition(validate = "notNull")
  private StubTypeBean _stubType;
  
  @PropertyDefinition
  private int _settlementDays;
  
  @PropertyDefinition(validate = "notNull")
  private String _underlyingIssuer;
  
  @PropertyDefinition(validate = "notNull")
  private CurrencyBean _underlyingCurrency;
  
  @PropertyDefinition(validate = "notNull")
  private String _underlyingSeniority;
  
  @PropertyDefinition(validate = "notNull")
  private String _restructuringClause;
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CDSSecurityBean}.
   * @return the meta-bean, not null
   */
  public static CDSSecurityBean.Meta meta() {
    return CDSSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CDSSecurityBean.Meta.INSTANCE);
  }

  @Override
  public CDSSecurityBean.Meta metaBean() {
    return CDSSecurityBean.Meta.INSTANCE;
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
   * Gets the recoveryRate.
   * @return the value of the property
   */
  public double getRecoveryRate() {
    return _recoveryRate;
  }

  /**
   * Sets the recoveryRate.
   * @param recoveryRate  the new value of the property
   */
  public void setRecoveryRate(double recoveryRate) {
    this._recoveryRate = recoveryRate;
  }

  /**
   * Gets the the {@code recoveryRate} property.
   * @return the property, not null
   */
  public final Property<Double> recoveryRate() {
    return metaBean().recoveryRate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spread.
   * @return the value of the property
   */
  public double getSpread() {
    return _spread;
  }

  /**
   * Sets the spread.
   * @param spread  the new value of the property
   */
  public void setSpread(double spread) {
    this._spread = spread;
  }

  /**
   * Gets the the {@code spread} property.
   * @return the property, not null
   */
  public final Property<Double> spread() {
    return metaBean().spread().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property, not null
   */
  public CurrencyBean getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property, not null
   */
  public void setCurrency(CurrencyBean currency) {
    JodaBeanUtils.notNull(currency, "currency");
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
   * Gets the maturity.
   * @return the value of the property, not null
   */
  public ZonedDateTimeBean getMaturity() {
    return _maturity;
  }

  /**
   * Sets the maturity.
   * @param maturity  the new value of the property, not null
   */
  public void setMaturity(ZonedDateTimeBean maturity) {
    JodaBeanUtils.notNull(maturity, "maturity");
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
   * Gets the startDate.
   * @return the value of the property, not null
   */
  public ZonedDateTimeBean getStartDate() {
    return _startDate;
  }

  /**
   * Sets the startDate.
   * @param startDate  the new value of the property, not null
   */
  public void setStartDate(ZonedDateTimeBean startDate) {
    JodaBeanUtils.notNull(startDate, "startDate");
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
   * Gets the premiumFrequency.
   * @return the value of the property, not null
   */
  public FrequencyBean getPremiumFrequency() {
    return _premiumFrequency;
  }

  /**
   * Sets the premiumFrequency.
   * @param premiumFrequency  the new value of the property, not null
   */
  public void setPremiumFrequency(FrequencyBean premiumFrequency) {
    JodaBeanUtils.notNull(premiumFrequency, "premiumFrequency");
    this._premiumFrequency = premiumFrequency;
  }

  /**
   * Gets the the {@code premiumFrequency} property.
   * @return the property, not null
   */
  public final Property<FrequencyBean> premiumFrequency() {
    return metaBean().premiumFrequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the dayCount.
   * @return the value of the property, not null
   */
  public DayCountBean getDayCount() {
    return _dayCount;
  }

  /**
   * Sets the dayCount.
   * @param dayCount  the new value of the property, not null
   */
  public void setDayCount(DayCountBean dayCount) {
    JodaBeanUtils.notNull(dayCount, "dayCount");
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
   * Gets the businessDayConvention.
   * @return the value of the property, not null
   */
  public BusinessDayConventionBean getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Sets the businessDayConvention.
   * @param businessDayConvention  the new value of the property, not null
   */
  public void setBusinessDayConvention(BusinessDayConventionBean businessDayConvention) {
    JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
    this._businessDayConvention = businessDayConvention;
  }

  /**
   * Gets the the {@code businessDayConvention} property.
   * @return the property, not null
   */
  public final Property<BusinessDayConventionBean> businessDayConvention() {
    return metaBean().businessDayConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stubType.
   * @return the value of the property, not null
   */
  public StubTypeBean getStubType() {
    return _stubType;
  }

  /**
   * Sets the stubType.
   * @param stubType  the new value of the property, not null
   */
  public void setStubType(StubTypeBean stubType) {
    JodaBeanUtils.notNull(stubType, "stubType");
    this._stubType = stubType;
  }

  /**
   * Gets the the {@code stubType} property.
   * @return the property, not null
   */
  public final Property<StubTypeBean> stubType() {
    return metaBean().stubType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlementDays.
   * @return the value of the property
   */
  public int getSettlementDays() {
    return _settlementDays;
  }

  /**
   * Sets the settlementDays.
   * @param settlementDays  the new value of the property
   */
  public void setSettlementDays(int settlementDays) {
    this._settlementDays = settlementDays;
  }

  /**
   * Gets the the {@code settlementDays} property.
   * @return the property, not null
   */
  public final Property<Integer> settlementDays() {
    return metaBean().settlementDays().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlyingIssuer.
   * @return the value of the property, not null
   */
  public String getUnderlyingIssuer() {
    return _underlyingIssuer;
  }

  /**
   * Sets the underlyingIssuer.
   * @param underlyingIssuer  the new value of the property, not null
   */
  public void setUnderlyingIssuer(String underlyingIssuer) {
    JodaBeanUtils.notNull(underlyingIssuer, "underlyingIssuer");
    this._underlyingIssuer = underlyingIssuer;
  }

  /**
   * Gets the the {@code underlyingIssuer} property.
   * @return the property, not null
   */
  public final Property<String> underlyingIssuer() {
    return metaBean().underlyingIssuer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlyingCurrency.
   * @return the value of the property, not null
   */
  public CurrencyBean getUnderlyingCurrency() {
    return _underlyingCurrency;
  }

  /**
   * Sets the underlyingCurrency.
   * @param underlyingCurrency  the new value of the property, not null
   */
  public void setUnderlyingCurrency(CurrencyBean underlyingCurrency) {
    JodaBeanUtils.notNull(underlyingCurrency, "underlyingCurrency");
    this._underlyingCurrency = underlyingCurrency;
  }

  /**
   * Gets the the {@code underlyingCurrency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> underlyingCurrency() {
    return metaBean().underlyingCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlyingSeniority.
   * @return the value of the property, not null
   */
  public String getUnderlyingSeniority() {
    return _underlyingSeniority;
  }

  /**
   * Sets the underlyingSeniority.
   * @param underlyingSeniority  the new value of the property, not null
   */
  public void setUnderlyingSeniority(String underlyingSeniority) {
    JodaBeanUtils.notNull(underlyingSeniority, "underlyingSeniority");
    this._underlyingSeniority = underlyingSeniority;
  }

  /**
   * Gets the the {@code underlyingSeniority} property.
   * @return the property, not null
   */
  public final Property<String> underlyingSeniority() {
    return metaBean().underlyingSeniority().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the restructuringClause.
   * @return the value of the property, not null
   */
  public String getRestructuringClause() {
    return _restructuringClause;
  }

  /**
   * Sets the restructuringClause.
   * @param restructuringClause  the new value of the property, not null
   */
  public void setRestructuringClause(String restructuringClause) {
    JodaBeanUtils.notNull(restructuringClause, "restructuringClause");
    this._restructuringClause = restructuringClause;
  }

  /**
   * Gets the the {@code restructuringClause} property.
   * @return the property, not null
   */
  public final Property<String> restructuringClause() {
    return metaBean().restructuringClause().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public CDSSecurityBean clone() {
    return (CDSSecurityBean) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CDSSecurityBean other = (CDSSecurityBean) obj;
      return JodaBeanUtils.equal(getNotional(), other.getNotional()) &&
          JodaBeanUtils.equal(getRecoveryRate(), other.getRecoveryRate()) &&
          JodaBeanUtils.equal(getSpread(), other.getSpread()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getMaturity(), other.getMaturity()) &&
          JodaBeanUtils.equal(getStartDate(), other.getStartDate()) &&
          JodaBeanUtils.equal(getPremiumFrequency(), other.getPremiumFrequency()) &&
          JodaBeanUtils.equal(getDayCount(), other.getDayCount()) &&
          JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          JodaBeanUtils.equal(getStubType(), other.getStubType()) &&
          (getSettlementDays() == other.getSettlementDays()) &&
          JodaBeanUtils.equal(getUnderlyingIssuer(), other.getUnderlyingIssuer()) &&
          JodaBeanUtils.equal(getUnderlyingCurrency(), other.getUnderlyingCurrency()) &&
          JodaBeanUtils.equal(getUnderlyingSeniority(), other.getUnderlyingSeniority()) &&
          JodaBeanUtils.equal(getRestructuringClause(), other.getRestructuringClause()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRecoveryRate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpread());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMaturity());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStartDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPremiumFrequency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDayCount());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStubType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementDays());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlyingIssuer());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlyingCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlyingSeniority());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRestructuringClause());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(512);
    buf.append("CDSSecurityBean{");
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
    buf.append("notional").append('=').append(JodaBeanUtils.toString(getNotional())).append(',').append(' ');
    buf.append("recoveryRate").append('=').append(JodaBeanUtils.toString(getRecoveryRate())).append(',').append(' ');
    buf.append("spread").append('=').append(JodaBeanUtils.toString(getSpread())).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency())).append(',').append(' ');
    buf.append("maturity").append('=').append(JodaBeanUtils.toString(getMaturity())).append(',').append(' ');
    buf.append("startDate").append('=').append(JodaBeanUtils.toString(getStartDate())).append(',').append(' ');
    buf.append("premiumFrequency").append('=').append(JodaBeanUtils.toString(getPremiumFrequency())).append(',').append(' ');
    buf.append("dayCount").append('=').append(JodaBeanUtils.toString(getDayCount())).append(',').append(' ');
    buf.append("businessDayConvention").append('=').append(JodaBeanUtils.toString(getBusinessDayConvention())).append(',').append(' ');
    buf.append("stubType").append('=').append(JodaBeanUtils.toString(getStubType())).append(',').append(' ');
    buf.append("settlementDays").append('=').append(JodaBeanUtils.toString(getSettlementDays())).append(',').append(' ');
    buf.append("underlyingIssuer").append('=').append(JodaBeanUtils.toString(getUnderlyingIssuer())).append(',').append(' ');
    buf.append("underlyingCurrency").append('=').append(JodaBeanUtils.toString(getUnderlyingCurrency())).append(',').append(' ');
    buf.append("underlyingSeniority").append('=').append(JodaBeanUtils.toString(getUnderlyingSeniority())).append(',').append(' ');
    buf.append("restructuringClause").append('=').append(JodaBeanUtils.toString(getRestructuringClause())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CDSSecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code notional} property.
     */
    private final MetaProperty<Double> _notional = DirectMetaProperty.ofReadWrite(
        this, "notional", CDSSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code recoveryRate} property.
     */
    private final MetaProperty<Double> _recoveryRate = DirectMetaProperty.ofReadWrite(
        this, "recoveryRate", CDSSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code spread} property.
     */
    private final MetaProperty<Double> _spread = DirectMetaProperty.ofReadWrite(
        this, "spread", CDSSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<CurrencyBean> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", CDSSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code maturity} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _maturity = DirectMetaProperty.ofReadWrite(
        this, "maturity", CDSSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _startDate = DirectMetaProperty.ofReadWrite(
        this, "startDate", CDSSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code premiumFrequency} property.
     */
    private final MetaProperty<FrequencyBean> _premiumFrequency = DirectMetaProperty.ofReadWrite(
        this, "premiumFrequency", CDSSecurityBean.class, FrequencyBean.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCountBean> _dayCount = DirectMetaProperty.ofReadWrite(
        this, "dayCount", CDSSecurityBean.class, DayCountBean.class);
    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConventionBean> _businessDayConvention = DirectMetaProperty.ofReadWrite(
        this, "businessDayConvention", CDSSecurityBean.class, BusinessDayConventionBean.class);
    /**
     * The meta-property for the {@code stubType} property.
     */
    private final MetaProperty<StubTypeBean> _stubType = DirectMetaProperty.ofReadWrite(
        this, "stubType", CDSSecurityBean.class, StubTypeBean.class);
    /**
     * The meta-property for the {@code settlementDays} property.
     */
    private final MetaProperty<Integer> _settlementDays = DirectMetaProperty.ofReadWrite(
        this, "settlementDays", CDSSecurityBean.class, Integer.TYPE);
    /**
     * The meta-property for the {@code underlyingIssuer} property.
     */
    private final MetaProperty<String> _underlyingIssuer = DirectMetaProperty.ofReadWrite(
        this, "underlyingIssuer", CDSSecurityBean.class, String.class);
    /**
     * The meta-property for the {@code underlyingCurrency} property.
     */
    private final MetaProperty<CurrencyBean> _underlyingCurrency = DirectMetaProperty.ofReadWrite(
        this, "underlyingCurrency", CDSSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code underlyingSeniority} property.
     */
    private final MetaProperty<String> _underlyingSeniority = DirectMetaProperty.ofReadWrite(
        this, "underlyingSeniority", CDSSecurityBean.class, String.class);
    /**
     * The meta-property for the {@code restructuringClause} property.
     */
    private final MetaProperty<String> _restructuringClause = DirectMetaProperty.ofReadWrite(
        this, "restructuringClause", CDSSecurityBean.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "notional",
        "recoveryRate",
        "spread",
        "currency",
        "maturity",
        "startDate",
        "premiumFrequency",
        "dayCount",
        "businessDayConvention",
        "stubType",
        "settlementDays",
        "underlyingIssuer",
        "underlyingCurrency",
        "underlyingSeniority",
        "restructuringClause");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          return _notional;
        case 2002873877:  // recoveryRate
          return _recoveryRate;
        case -895684237:  // spread
          return _spread;
        case 575402001:  // currency
          return _currency;
        case 313843601:  // maturity
          return _maturity;
        case -2129778896:  // startDate
          return _startDate;
        case 146671813:  // premiumFrequency
          return _premiumFrequency;
        case 1905311443:  // dayCount
          return _dayCount;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case 1873675528:  // stubType
          return _stubType;
        case -295948000:  // settlementDays
          return _settlementDays;
        case -81466250:  // underlyingIssuer
          return _underlyingIssuer;
        case -1102975346:  // underlyingCurrency
          return _underlyingCurrency;
        case -305508959:  // underlyingSeniority
          return _underlyingSeniority;
        case -1774904020:  // restructuringClause
          return _restructuringClause;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CDSSecurityBean> builder() {
      return new DirectBeanBuilder<CDSSecurityBean>(new CDSSecurityBean());
    }

    @Override
    public Class<? extends CDSSecurityBean> beanType() {
      return CDSSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code notional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> notional() {
      return _notional;
    }

    /**
     * The meta-property for the {@code recoveryRate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> recoveryRate() {
      return _recoveryRate;
    }

    /**
     * The meta-property for the {@code spread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> spread() {
      return _spread;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code maturity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> maturity() {
      return _maturity;
    }

    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> startDate() {
      return _startDate;
    }

    /**
     * The meta-property for the {@code premiumFrequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FrequencyBean> premiumFrequency() {
      return _premiumFrequency;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DayCountBean> dayCount() {
      return _dayCount;
    }

    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConventionBean> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code stubType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StubTypeBean> stubType() {
      return _stubType;
    }

    /**
     * The meta-property for the {@code settlementDays} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> settlementDays() {
      return _settlementDays;
    }

    /**
     * The meta-property for the {@code underlyingIssuer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> underlyingIssuer() {
      return _underlyingIssuer;
    }

    /**
     * The meta-property for the {@code underlyingCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> underlyingCurrency() {
      return _underlyingCurrency;
    }

    /**
     * The meta-property for the {@code underlyingSeniority} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> underlyingSeniority() {
      return _underlyingSeniority;
    }

    /**
     * The meta-property for the {@code restructuringClause} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> restructuringClause() {
      return _restructuringClause;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          return ((CDSSecurityBean) bean).getNotional();
        case 2002873877:  // recoveryRate
          return ((CDSSecurityBean) bean).getRecoveryRate();
        case -895684237:  // spread
          return ((CDSSecurityBean) bean).getSpread();
        case 575402001:  // currency
          return ((CDSSecurityBean) bean).getCurrency();
        case 313843601:  // maturity
          return ((CDSSecurityBean) bean).getMaturity();
        case -2129778896:  // startDate
          return ((CDSSecurityBean) bean).getStartDate();
        case 146671813:  // premiumFrequency
          return ((CDSSecurityBean) bean).getPremiumFrequency();
        case 1905311443:  // dayCount
          return ((CDSSecurityBean) bean).getDayCount();
        case -1002835891:  // businessDayConvention
          return ((CDSSecurityBean) bean).getBusinessDayConvention();
        case 1873675528:  // stubType
          return ((CDSSecurityBean) bean).getStubType();
        case -295948000:  // settlementDays
          return ((CDSSecurityBean) bean).getSettlementDays();
        case -81466250:  // underlyingIssuer
          return ((CDSSecurityBean) bean).getUnderlyingIssuer();
        case -1102975346:  // underlyingCurrency
          return ((CDSSecurityBean) bean).getUnderlyingCurrency();
        case -305508959:  // underlyingSeniority
          return ((CDSSecurityBean) bean).getUnderlyingSeniority();
        case -1774904020:  // restructuringClause
          return ((CDSSecurityBean) bean).getRestructuringClause();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          ((CDSSecurityBean) bean).setNotional((Double) newValue);
          return;
        case 2002873877:  // recoveryRate
          ((CDSSecurityBean) bean).setRecoveryRate((Double) newValue);
          return;
        case -895684237:  // spread
          ((CDSSecurityBean) bean).setSpread((Double) newValue);
          return;
        case 575402001:  // currency
          ((CDSSecurityBean) bean).setCurrency((CurrencyBean) newValue);
          return;
        case 313843601:  // maturity
          ((CDSSecurityBean) bean).setMaturity((ZonedDateTimeBean) newValue);
          return;
        case -2129778896:  // startDate
          ((CDSSecurityBean) bean).setStartDate((ZonedDateTimeBean) newValue);
          return;
        case 146671813:  // premiumFrequency
          ((CDSSecurityBean) bean).setPremiumFrequency((FrequencyBean) newValue);
          return;
        case 1905311443:  // dayCount
          ((CDSSecurityBean) bean).setDayCount((DayCountBean) newValue);
          return;
        case -1002835891:  // businessDayConvention
          ((CDSSecurityBean) bean).setBusinessDayConvention((BusinessDayConventionBean) newValue);
          return;
        case 1873675528:  // stubType
          ((CDSSecurityBean) bean).setStubType((StubTypeBean) newValue);
          return;
        case -295948000:  // settlementDays
          ((CDSSecurityBean) bean).setSettlementDays((Integer) newValue);
          return;
        case -81466250:  // underlyingIssuer
          ((CDSSecurityBean) bean).setUnderlyingIssuer((String) newValue);
          return;
        case -1102975346:  // underlyingCurrency
          ((CDSSecurityBean) bean).setUnderlyingCurrency((CurrencyBean) newValue);
          return;
        case -305508959:  // underlyingSeniority
          ((CDSSecurityBean) bean).setUnderlyingSeniority((String) newValue);
          return;
        case -1774904020:  // restructuringClause
          ((CDSSecurityBean) bean).setRestructuringClause((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._currency, "currency");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._maturity, "maturity");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._startDate, "startDate");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._premiumFrequency, "premiumFrequency");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._dayCount, "dayCount");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._businessDayConvention, "businessDayConvention");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._stubType, "stubType");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._underlyingIssuer, "underlyingIssuer");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._underlyingCurrency, "underlyingCurrency");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._underlyingSeniority, "underlyingSeniority");
      JodaBeanUtils.notNull(((CDSSecurityBean) bean)._restructuringClause, "restructuringClause");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
