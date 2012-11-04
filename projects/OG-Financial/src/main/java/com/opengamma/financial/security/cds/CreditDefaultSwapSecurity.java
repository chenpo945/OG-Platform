/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.cds;

import java.util.Map;

import javax.time.calendar.ZonedDateTime;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.analytics.financial.credit.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.swap.InterestRateNotional;
import com.opengamma.id.ExternalId;

/**
 * An abstract base class for credit securities.
 */
@BeanDefinition
public abstract class CreditDefaultSwapSecurity extends FinancialSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security type
   */
  public static final String SECURITY_TYPE = "CREDIT_DEFAULT_SWAP";

  /**
   * Has the protection been bought. If false, protection has been sold.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _buy;

  /**
   * The protection buyer.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _protectionBuyer;

  /**
   * The protection seller.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _protectionSeller;

  /**
   * The reference entity.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _referenceEntity;

  /**
   * The debt seniority.
   */
  @PropertyDefinition(validate = "notNull")
  private String _debtSeniority;

  /**
   * The restructuring clause.
   */
  @PropertyDefinition(validate = "notNull")
  private String _restructuringClause;

  /**
   * The region id.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _regionId;

  /**
   * The start date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _startDate;

  /**
   * The effective date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _effectiveDate;

  /**
   * The maturity date.
   */
  @PropertyDefinition(validate = "notNull")
  private ZonedDateTime _maturityDate;

  /**
   * The stub type.
   */
  @PropertyDefinition(validate = "notNull")
  private StubType _stubType;

  /**
   * The coupon frequency.
   */
  @PropertyDefinition(validate = "notNull")
  private Frequency _couponFrequency;

  /**
   * The day-count convention.
   */
  @PropertyDefinition(validate = "notNull")
  private DayCount _dayCount;

  /**
   * The business-day convention.
   */
  @PropertyDefinition(validate = "notNull")
  private BusinessDayConvention _businessDayConvention;

  /**
   * Adjust maturity to the next IMM date.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _immAdjustMaturityDate;

  /**
   * Adjust effective date.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _adjustEffectiveDate;

  /**
   * Adjust maturity date.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _adjustMaturityDate;

  /**
   * The notional.
   */
  @PropertyDefinition(validate = "notNull")
  private InterestRateNotional _notional;

  /**
   * The recovery rate.
   */
  @PropertyDefinition(validate = "notNull")
  private double _recoveryRate;

  /**
   * Include accrued premium.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _includeAccruedPremium;

  /**
   * Protection start.
   */
  @PropertyDefinition(validate = "notNull")
  private boolean _protectionStart;

  /**
   * The CDS type.
   */
  @PropertyDefinition(validate = "notNull")
  private CDSType _cdsType;

  CreditDefaultSwapSecurity() { // For Fudge builder
  }

  public CreditDefaultSwapSecurity(final boolean isBuy, final ExternalId protectionSeller, final ExternalId protectionBuyer, final ExternalId referenceEntity, //CSIGNORE
      final String debtSeniority, final String restructuringClause, final ExternalId regionId, final ZonedDateTime startDate,
      final ZonedDateTime effectiveDate, final ZonedDateTime maturityDate, final StubType stubType, final Frequency couponFrequency, final DayCount dayCount,
      final BusinessDayConvention businessDayConvention, final boolean immAdjustMaturityDate, final boolean adjustEffectiveDate,
      final boolean adjustMaturityDate, final InterestRateNotional notional, final double recoveryRate, final boolean includeAccruedPremium,
      final boolean protectionStart, final CDSType cdsType) {
    super(SECURITY_TYPE);
    setBuy(isBuy);
    setProtectionSeller(protectionSeller);
    setProtectionBuyer(protectionBuyer);
    setReferenceEntity(referenceEntity);
    setDebtSeniority(debtSeniority);
    setRestructuringClause(restructuringClause);
    setRegionId(regionId);
    setStartDate(startDate);
    setEffectiveDate(effectiveDate);
    setMaturityDate(maturityDate);
    setStubType(stubType);
    setCouponFrequency(couponFrequency);
    setDayCount(dayCount);
    setBusinessDayConvention(businessDayConvention);
    setImmAdjustMaturityDate(immAdjustMaturityDate);
    setAdjustEffectiveDate(adjustEffectiveDate);
    setAdjustMaturityDate(adjustMaturityDate);
    setNotional(notional);
    setRecoveryRate(recoveryRate);
    setIncludeAccruedPremium(includeAccruedPremium);
    setProtectionStart(protectionStart);
    setCdsType(cdsType);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CreditDefaultSwapSecurity}.
   * @return the meta-bean, not null
   */
  public static CreditDefaultSwapSecurity.Meta meta() {
    return CreditDefaultSwapSecurity.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(CreditDefaultSwapSecurity.Meta.INSTANCE);
  }

  @Override
  public CreditDefaultSwapSecurity.Meta metaBean() {
    return CreditDefaultSwapSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(final String propertyName, final boolean quiet) {
    switch (propertyName.hashCode()) {
      case 97926:  // buy
        return isBuy();
      case 2087835226:  // protectionBuyer
        return getProtectionBuyer();
      case 769920952:  // protectionSeller
        return getProtectionSeller();
      case 480652046:  // referenceEntity
        return getReferenceEntity();
      case 1737168171:  // debtSeniority
        return getDebtSeniority();
      case -1774904020:  // restructuringClause
        return getRestructuringClause();
      case -690339025:  // regionId
        return getRegionId();
      case -2129778896:  // startDate
        return getStartDate();
      case -930389515:  // effectiveDate
        return getEffectiveDate();
      case -414641441:  // maturityDate
        return getMaturityDate();
      case 1873675528:  // stubType
        return getStubType();
      case 144480214:  // couponFrequency
        return getCouponFrequency();
      case 1905311443:  // dayCount
        return getDayCount();
      case -1002835891:  // businessDayConvention
        return getBusinessDayConvention();
      case -1168632905:  // immAdjustMaturityDate
        return isImmAdjustMaturityDate();
      case -490317146:  // adjustEffectiveDate
        return isAdjustEffectiveDate();
      case -261898226:  // adjustMaturityDate
        return isAdjustMaturityDate();
      case 1585636160:  // notional
        return getNotional();
      case 2002873877:  // recoveryRate
        return getRecoveryRate();
      case 2100149628:  // includeAccruedPremium
        return isIncludeAccruedPremium();
      case 2103482633:  // protectionStart
        return isProtectionStart();
      case 640293516:  // cdsType
        return getCdsType();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(final String propertyName, final Object newValue, final boolean quiet) {
    switch (propertyName.hashCode()) {
      case 97926:  // buy
        setBuy((Boolean) newValue);
        return;
      case 2087835226:  // protectionBuyer
        setProtectionBuyer((ExternalId) newValue);
        return;
      case 769920952:  // protectionSeller
        setProtectionSeller((ExternalId) newValue);
        return;
      case 480652046:  // referenceEntity
        setReferenceEntity((ExternalId) newValue);
        return;
      case 1737168171:  // debtSeniority
        setDebtSeniority((String) newValue);
        return;
      case -1774904020:  // restructuringClause
        setRestructuringClause((String) newValue);
        return;
      case -690339025:  // regionId
        setRegionId((ExternalId) newValue);
        return;
      case -2129778896:  // startDate
        setStartDate((ZonedDateTime) newValue);
        return;
      case -930389515:  // effectiveDate
        setEffectiveDate((ZonedDateTime) newValue);
        return;
      case -414641441:  // maturityDate
        setMaturityDate((ZonedDateTime) newValue);
        return;
      case 1873675528:  // stubType
        setStubType((StubType) newValue);
        return;
      case 144480214:  // couponFrequency
        setCouponFrequency((Frequency) newValue);
        return;
      case 1905311443:  // dayCount
        setDayCount((DayCount) newValue);
        return;
      case -1002835891:  // businessDayConvention
        setBusinessDayConvention((BusinessDayConvention) newValue);
        return;
      case -1168632905:  // immAdjustMaturityDate
        setImmAdjustMaturityDate((Boolean) newValue);
        return;
      case -490317146:  // adjustEffectiveDate
        setAdjustEffectiveDate((Boolean) newValue);
        return;
      case -261898226:  // adjustMaturityDate
        setAdjustMaturityDate((Boolean) newValue);
        return;
      case 1585636160:  // notional
        setNotional((InterestRateNotional) newValue);
        return;
      case 2002873877:  // recoveryRate
        setRecoveryRate((Double) newValue);
        return;
      case 2100149628:  // includeAccruedPremium
        setIncludeAccruedPremium((Boolean) newValue);
        return;
      case 2103482633:  // protectionStart
        setProtectionStart((Boolean) newValue);
        return;
      case 640293516:  // cdsType
        setCdsType((CDSType) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_buy, "buy");
    JodaBeanUtils.notNull(_protectionBuyer, "protectionBuyer");
    JodaBeanUtils.notNull(_protectionSeller, "protectionSeller");
    JodaBeanUtils.notNull(_referenceEntity, "referenceEntity");
    JodaBeanUtils.notNull(_debtSeniority, "debtSeniority");
    JodaBeanUtils.notNull(_restructuringClause, "restructuringClause");
    JodaBeanUtils.notNull(_regionId, "regionId");
    JodaBeanUtils.notNull(_startDate, "startDate");
    JodaBeanUtils.notNull(_effectiveDate, "effectiveDate");
    JodaBeanUtils.notNull(_maturityDate, "maturityDate");
    JodaBeanUtils.notNull(_stubType, "stubType");
    JodaBeanUtils.notNull(_couponFrequency, "couponFrequency");
    JodaBeanUtils.notNull(_dayCount, "dayCount");
    JodaBeanUtils.notNull(_businessDayConvention, "businessDayConvention");
    JodaBeanUtils.notNull(_immAdjustMaturityDate, "immAdjustMaturityDate");
    JodaBeanUtils.notNull(_adjustEffectiveDate, "adjustEffectiveDate");
    JodaBeanUtils.notNull(_adjustMaturityDate, "adjustMaturityDate");
    JodaBeanUtils.notNull(_notional, "notional");
    JodaBeanUtils.notNull(_recoveryRate, "recoveryRate");
    JodaBeanUtils.notNull(_includeAccruedPremium, "includeAccruedPremium");
    JodaBeanUtils.notNull(_protectionStart, "protectionStart");
    JodaBeanUtils.notNull(_cdsType, "cdsType");
    super.validate();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      final CreditDefaultSwapSecurity other = (CreditDefaultSwapSecurity) obj;
      return JodaBeanUtils.equal(isBuy(), other.isBuy()) &&
          JodaBeanUtils.equal(getProtectionBuyer(), other.getProtectionBuyer()) &&
          JodaBeanUtils.equal(getProtectionSeller(), other.getProtectionSeller()) &&
          JodaBeanUtils.equal(getReferenceEntity(), other.getReferenceEntity()) &&
          JodaBeanUtils.equal(getDebtSeniority(), other.getDebtSeniority()) &&
          JodaBeanUtils.equal(getRestructuringClause(), other.getRestructuringClause()) &&
          JodaBeanUtils.equal(getRegionId(), other.getRegionId()) &&
          JodaBeanUtils.equal(getStartDate(), other.getStartDate()) &&
          JodaBeanUtils.equal(getEffectiveDate(), other.getEffectiveDate()) &&
          JodaBeanUtils.equal(getMaturityDate(), other.getMaturityDate()) &&
          JodaBeanUtils.equal(getStubType(), other.getStubType()) &&
          JodaBeanUtils.equal(getCouponFrequency(), other.getCouponFrequency()) &&
          JodaBeanUtils.equal(getDayCount(), other.getDayCount()) &&
          JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          JodaBeanUtils.equal(isImmAdjustMaturityDate(), other.isImmAdjustMaturityDate()) &&
          JodaBeanUtils.equal(isAdjustEffectiveDate(), other.isAdjustEffectiveDate()) &&
          JodaBeanUtils.equal(isAdjustMaturityDate(), other.isAdjustMaturityDate()) &&
          JodaBeanUtils.equal(getNotional(), other.getNotional()) &&
          JodaBeanUtils.equal(getRecoveryRate(), other.getRecoveryRate()) &&
          JodaBeanUtils.equal(isIncludeAccruedPremium(), other.isIncludeAccruedPremium()) &&
          JodaBeanUtils.equal(isProtectionStart(), other.isProtectionStart()) &&
          JodaBeanUtils.equal(getCdsType(), other.getCdsType()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(isBuy());
    hash += hash * 31 + JodaBeanUtils.hashCode(getProtectionBuyer());
    hash += hash * 31 + JodaBeanUtils.hashCode(getProtectionSeller());
    hash += hash * 31 + JodaBeanUtils.hashCode(getReferenceEntity());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDebtSeniority());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRestructuringClause());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRegionId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStartDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getEffectiveDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMaturityDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStubType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCouponFrequency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDayCount());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(isImmAdjustMaturityDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(isAdjustEffectiveDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(isAdjustMaturityDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRecoveryRate());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIncludeAccruedPremium());
    hash += hash * 31 + JodaBeanUtils.hashCode(isProtectionStart());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCdsType());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets has the protection been bought. If false, protection has been sold.
   * @return the value of the property, not null
   */
  public boolean isBuy() {
    return _buy;
  }

  /**
   * Sets has the protection been bought. If false, protection has been sold.
   * @param buy  the new value of the property, not null
   */
  public void setBuy(final boolean buy) {
    JodaBeanUtils.notNull(buy, "buy");
    this._buy = buy;
  }

  /**
   * Gets the the {@code buy} property.
   * @return the property, not null
   */
  public final Property<Boolean> buy() {
    return metaBean().buy().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the protection buyer.
   * @return the value of the property, not null
   */
  public ExternalId getProtectionBuyer() {
    return _protectionBuyer;
  }

  /**
   * Sets the protection buyer.
   * @param protectionBuyer  the new value of the property, not null
   */
  public void setProtectionBuyer(final ExternalId protectionBuyer) {
    JodaBeanUtils.notNull(protectionBuyer, "protectionBuyer");
    this._protectionBuyer = protectionBuyer;
  }

  /**
   * Gets the the {@code protectionBuyer} property.
   * @return the property, not null
   */
  public final Property<ExternalId> protectionBuyer() {
    return metaBean().protectionBuyer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the protection seller.
   * @return the value of the property, not null
   */
  public ExternalId getProtectionSeller() {
    return _protectionSeller;
  }

  /**
   * Sets the protection seller.
   * @param protectionSeller  the new value of the property, not null
   */
  public void setProtectionSeller(final ExternalId protectionSeller) {
    JodaBeanUtils.notNull(protectionSeller, "protectionSeller");
    this._protectionSeller = protectionSeller;
  }

  /**
   * Gets the the {@code protectionSeller} property.
   * @return the property, not null
   */
  public final Property<ExternalId> protectionSeller() {
    return metaBean().protectionSeller().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference entity.
   * @return the value of the property, not null
   */
  public ExternalId getReferenceEntity() {
    return _referenceEntity;
  }

  /**
   * Sets the reference entity.
   * @param referenceEntity  the new value of the property, not null
   */
  public void setReferenceEntity(final ExternalId referenceEntity) {
    JodaBeanUtils.notNull(referenceEntity, "referenceEntity");
    this._referenceEntity = referenceEntity;
  }

  /**
   * Gets the the {@code referenceEntity} property.
   * @return the property, not null
   */
  public final Property<ExternalId> referenceEntity() {
    return metaBean().referenceEntity().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the debt seniority.
   * @return the value of the property, not null
   */
  public String getDebtSeniority() {
    return _debtSeniority;
  }

  /**
   * Sets the debt seniority.
   * @param debtSeniority  the new value of the property, not null
   */
  public void setDebtSeniority(final String debtSeniority) {
    JodaBeanUtils.notNull(debtSeniority, "debtSeniority");
    this._debtSeniority = debtSeniority;
  }

  /**
   * Gets the the {@code debtSeniority} property.
   * @return the property, not null
   */
  public final Property<String> debtSeniority() {
    return metaBean().debtSeniority().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the restructuring clause.
   * @return the value of the property, not null
   */
  public String getRestructuringClause() {
    return _restructuringClause;
  }

  /**
   * Sets the restructuring clause.
   * @param restructuringClause  the new value of the property, not null
   */
  public void setRestructuringClause(final String restructuringClause) {
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
  /**
   * Gets the region id.
   * @return the value of the property, not null
   */
  public ExternalId getRegionId() {
    return _regionId;
  }

  /**
   * Sets the region id.
   * @param regionId  the new value of the property, not null
   */
  public void setRegionId(final ExternalId regionId) {
    JodaBeanUtils.notNull(regionId, "regionId");
    this._regionId = regionId;
  }

  /**
   * Gets the the {@code regionId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> regionId() {
    return metaBean().regionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getStartDate() {
    return _startDate;
  }

  /**
   * Sets the start date.
   * @param startDate  the new value of the property, not null
   */
  public void setStartDate(final ZonedDateTime startDate) {
    JodaBeanUtils.notNull(startDate, "startDate");
    this._startDate = startDate;
  }

  /**
   * Gets the the {@code startDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> startDate() {
    return metaBean().startDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the effective date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getEffectiveDate() {
    return _effectiveDate;
  }

  /**
   * Sets the effective date.
   * @param effectiveDate  the new value of the property, not null
   */
  public void setEffectiveDate(final ZonedDateTime effectiveDate) {
    JodaBeanUtils.notNull(effectiveDate, "effectiveDate");
    this._effectiveDate = effectiveDate;
  }

  /**
   * Gets the the {@code effectiveDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> effectiveDate() {
    return metaBean().effectiveDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturity date.
   * @return the value of the property, not null
   */
  public ZonedDateTime getMaturityDate() {
    return _maturityDate;
  }

  /**
   * Sets the maturity date.
   * @param maturityDate  the new value of the property, not null
   */
  public void setMaturityDate(final ZonedDateTime maturityDate) {
    JodaBeanUtils.notNull(maturityDate, "maturityDate");
    this._maturityDate = maturityDate;
  }

  /**
   * Gets the the {@code maturityDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTime> maturityDate() {
    return metaBean().maturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stub type.
   * @return the value of the property, not null
   */
  public StubType getStubType() {
    return _stubType;
  }

  /**
   * Sets the stub type.
   * @param stubType  the new value of the property, not null
   */
  public void setStubType(final StubType stubType) {
    JodaBeanUtils.notNull(stubType, "stubType");
    this._stubType = stubType;
  }

  /**
   * Gets the the {@code stubType} property.
   * @return the property, not null
   */
  public final Property<StubType> stubType() {
    return metaBean().stubType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the coupon frequency.
   * @return the value of the property, not null
   */
  public Frequency getCouponFrequency() {
    return _couponFrequency;
  }

  /**
   * Sets the coupon frequency.
   * @param couponFrequency  the new value of the property, not null
   */
  public void setCouponFrequency(final Frequency couponFrequency) {
    JodaBeanUtils.notNull(couponFrequency, "couponFrequency");
    this._couponFrequency = couponFrequency;
  }

  /**
   * Gets the the {@code couponFrequency} property.
   * @return the property, not null
   */
  public final Property<Frequency> couponFrequency() {
    return metaBean().couponFrequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the day-count convention.
   * @return the value of the property, not null
   */
  public DayCount getDayCount() {
    return _dayCount;
  }

  /**
   * Sets the day-count convention.
   * @param dayCount  the new value of the property, not null
   */
  public void setDayCount(final DayCount dayCount) {
    JodaBeanUtils.notNull(dayCount, "dayCount");
    this._dayCount = dayCount;
  }

  /**
   * Gets the the {@code dayCount} property.
   * @return the property, not null
   */
  public final Property<DayCount> dayCount() {
    return metaBean().dayCount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the business-day convention.
   * @return the value of the property, not null
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Sets the business-day convention.
   * @param businessDayConvention  the new value of the property, not null
   */
  public void setBusinessDayConvention(final BusinessDayConvention businessDayConvention) {
    JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
    this._businessDayConvention = businessDayConvention;
  }

  /**
   * Gets the the {@code businessDayConvention} property.
   * @return the property, not null
   */
  public final Property<BusinessDayConvention> businessDayConvention() {
    return metaBean().businessDayConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets adjust maturity to the next IMM date.
   * @return the value of the property, not null
   */
  public boolean isImmAdjustMaturityDate() {
    return _immAdjustMaturityDate;
  }

  /**
   * Sets adjust maturity to the next IMM date.
   * @param immAdjustMaturityDate  the new value of the property, not null
   */
  public void setImmAdjustMaturityDate(final boolean immAdjustMaturityDate) {
    JodaBeanUtils.notNull(immAdjustMaturityDate, "immAdjustMaturityDate");
    this._immAdjustMaturityDate = immAdjustMaturityDate;
  }

  /**
   * Gets the the {@code immAdjustMaturityDate} property.
   * @return the property, not null
   */
  public final Property<Boolean> immAdjustMaturityDate() {
    return metaBean().immAdjustMaturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets adjust effective date.
   * @return the value of the property, not null
   */
  public boolean isAdjustEffectiveDate() {
    return _adjustEffectiveDate;
  }

  /**
   * Sets adjust effective date.
   * @param adjustEffectiveDate  the new value of the property, not null
   */
  public void setAdjustEffectiveDate(final boolean adjustEffectiveDate) {
    JodaBeanUtils.notNull(adjustEffectiveDate, "adjustEffectiveDate");
    this._adjustEffectiveDate = adjustEffectiveDate;
  }

  /**
   * Gets the the {@code adjustEffectiveDate} property.
   * @return the property, not null
   */
  public final Property<Boolean> adjustEffectiveDate() {
    return metaBean().adjustEffectiveDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets adjust maturity date.
   * @return the value of the property, not null
   */
  public boolean isAdjustMaturityDate() {
    return _adjustMaturityDate;
  }

  /**
   * Sets adjust maturity date.
   * @param adjustMaturityDate  the new value of the property, not null
   */
  public void setAdjustMaturityDate(final boolean adjustMaturityDate) {
    JodaBeanUtils.notNull(adjustMaturityDate, "adjustMaturityDate");
    this._adjustMaturityDate = adjustMaturityDate;
  }

  /**
   * Gets the the {@code adjustMaturityDate} property.
   * @return the property, not null
   */
  public final Property<Boolean> adjustMaturityDate() {
    return metaBean().adjustMaturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the notional.
   * @return the value of the property, not null
   */
  public InterestRateNotional getNotional() {
    return _notional;
  }

  /**
   * Sets the notional.
   * @param notional  the new value of the property, not null
   */
  public void setNotional(final InterestRateNotional notional) {
    JodaBeanUtils.notNull(notional, "notional");
    this._notional = notional;
  }

  /**
   * Gets the the {@code notional} property.
   * @return the property, not null
   */
  public final Property<InterestRateNotional> notional() {
    return metaBean().notional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the recovery rate.
   * @return the value of the property, not null
   */
  public double getRecoveryRate() {
    return _recoveryRate;
  }

  /**
   * Sets the recovery rate.
   * @param recoveryRate  the new value of the property, not null
   */
  public void setRecoveryRate(final double recoveryRate) {
    JodaBeanUtils.notNull(recoveryRate, "recoveryRate");
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
   * Gets include accrued premium.
   * @return the value of the property, not null
   */
  public boolean isIncludeAccruedPremium() {
    return _includeAccruedPremium;
  }

  /**
   * Sets include accrued premium.
   * @param includeAccruedPremium  the new value of the property, not null
   */
  public void setIncludeAccruedPremium(final boolean includeAccruedPremium) {
    JodaBeanUtils.notNull(includeAccruedPremium, "includeAccruedPremium");
    this._includeAccruedPremium = includeAccruedPremium;
  }

  /**
   * Gets the the {@code includeAccruedPremium} property.
   * @return the property, not null
   */
  public final Property<Boolean> includeAccruedPremium() {
    return metaBean().includeAccruedPremium().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets protection start.
   * @return the value of the property, not null
   */
  public boolean isProtectionStart() {
    return _protectionStart;
  }

  /**
   * Sets protection start.
   * @param protectionStart  the new value of the property, not null
   */
  public void setProtectionStart(final boolean protectionStart) {
    JodaBeanUtils.notNull(protectionStart, "protectionStart");
    this._protectionStart = protectionStart;
  }

  /**
   * Gets the the {@code protectionStart} property.
   * @return the property, not null
   */
  public final Property<Boolean> protectionStart() {
    return metaBean().protectionStart().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the CDS type.
   * @return the value of the property, not null
   */
  public CDSType getCdsType() {
    return _cdsType;
  }

  /**
   * Sets the CDS type.
   * @param cdsType  the new value of the property, not null
   */
  public void setCdsType(final CDSType cdsType) {
    JodaBeanUtils.notNull(cdsType, "cdsType");
    this._cdsType = cdsType;
  }

  /**
   * Gets the the {@code cdsType} property.
   * @return the property, not null
   */
  public final Property<CDSType> cdsType() {
    return metaBean().cdsType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CreditDefaultSwapSecurity}.
   */
  public static class Meta extends FinancialSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code buy} property.
     */
    private final MetaProperty<Boolean> _buy = DirectMetaProperty.ofReadWrite(
        this, "buy", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code protectionBuyer} property.
     */
    private final MetaProperty<ExternalId> _protectionBuyer = DirectMetaProperty.ofReadWrite(
        this, "protectionBuyer", CreditDefaultSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code protectionSeller} property.
     */
    private final MetaProperty<ExternalId> _protectionSeller = DirectMetaProperty.ofReadWrite(
        this, "protectionSeller", CreditDefaultSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code referenceEntity} property.
     */
    private final MetaProperty<ExternalId> _referenceEntity = DirectMetaProperty.ofReadWrite(
        this, "referenceEntity", CreditDefaultSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code debtSeniority} property.
     */
    private final MetaProperty<String> _debtSeniority = DirectMetaProperty.ofReadWrite(
        this, "debtSeniority", CreditDefaultSwapSecurity.class, String.class);
    /**
     * The meta-property for the {@code restructuringClause} property.
     */
    private final MetaProperty<String> _restructuringClause = DirectMetaProperty.ofReadWrite(
        this, "restructuringClause", CreditDefaultSwapSecurity.class, String.class);
    /**
     * The meta-property for the {@code regionId} property.
     */
    private final MetaProperty<ExternalId> _regionId = DirectMetaProperty.ofReadWrite(
        this, "regionId", CreditDefaultSwapSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code startDate} property.
     */
    private final MetaProperty<ZonedDateTime> _startDate = DirectMetaProperty.ofReadWrite(
        this, "startDate", CreditDefaultSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code effectiveDate} property.
     */
    private final MetaProperty<ZonedDateTime> _effectiveDate = DirectMetaProperty.ofReadWrite(
        this, "effectiveDate", CreditDefaultSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code maturityDate} property.
     */
    private final MetaProperty<ZonedDateTime> _maturityDate = DirectMetaProperty.ofReadWrite(
        this, "maturityDate", CreditDefaultSwapSecurity.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code stubType} property.
     */
    private final MetaProperty<StubType> _stubType = DirectMetaProperty.ofReadWrite(
        this, "stubType", CreditDefaultSwapSecurity.class, StubType.class);
    /**
     * The meta-property for the {@code couponFrequency} property.
     */
    private final MetaProperty<Frequency> _couponFrequency = DirectMetaProperty.ofReadWrite(
        this, "couponFrequency", CreditDefaultSwapSecurity.class, Frequency.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCount> _dayCount = DirectMetaProperty.ofReadWrite(
        this, "dayCount", CreditDefaultSwapSecurity.class, DayCount.class);
    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConvention> _businessDayConvention = DirectMetaProperty.ofReadWrite(
        this, "businessDayConvention", CreditDefaultSwapSecurity.class, BusinessDayConvention.class);
    /**
     * The meta-property for the {@code immAdjustMaturityDate} property.
     */
    private final MetaProperty<Boolean> _immAdjustMaturityDate = DirectMetaProperty.ofReadWrite(
        this, "immAdjustMaturityDate", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code adjustEffectiveDate} property.
     */
    private final MetaProperty<Boolean> _adjustEffectiveDate = DirectMetaProperty.ofReadWrite(
        this, "adjustEffectiveDate", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code adjustMaturityDate} property.
     */
    private final MetaProperty<Boolean> _adjustMaturityDate = DirectMetaProperty.ofReadWrite(
        this, "adjustMaturityDate", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code notional} property.
     */
    private final MetaProperty<InterestRateNotional> _notional = DirectMetaProperty.ofReadWrite(
        this, "notional", CreditDefaultSwapSecurity.class, InterestRateNotional.class);
    /**
     * The meta-property for the {@code recoveryRate} property.
     */
    private final MetaProperty<Double> _recoveryRate = DirectMetaProperty.ofReadWrite(
        this, "recoveryRate", CreditDefaultSwapSecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code includeAccruedPremium} property.
     */
    private final MetaProperty<Boolean> _includeAccruedPremium = DirectMetaProperty.ofReadWrite(
        this, "includeAccruedPremium", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code protectionStart} property.
     */
    private final MetaProperty<Boolean> _protectionStart = DirectMetaProperty.ofReadWrite(
        this, "protectionStart", CreditDefaultSwapSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code cdsType} property.
     */
    private final MetaProperty<CDSType> _cdsType = DirectMetaProperty.ofReadWrite(
        this, "cdsType", CreditDefaultSwapSecurity.class, CDSType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "buy",
        "protectionBuyer",
        "protectionSeller",
        "referenceEntity",
        "debtSeniority",
        "restructuringClause",
        "regionId",
        "startDate",
        "effectiveDate",
        "maturityDate",
        "stubType",
        "couponFrequency",
        "dayCount",
        "businessDayConvention",
        "immAdjustMaturityDate",
        "adjustEffectiveDate",
        "adjustMaturityDate",
        "notional",
        "recoveryRate",
        "includeAccruedPremium",
        "protectionStart",
        "cdsType");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(final String propertyName) {
      switch (propertyName.hashCode()) {
        case 97926:  // buy
          return _buy;
        case 2087835226:  // protectionBuyer
          return _protectionBuyer;
        case 769920952:  // protectionSeller
          return _protectionSeller;
        case 480652046:  // referenceEntity
          return _referenceEntity;
        case 1737168171:  // debtSeniority
          return _debtSeniority;
        case -1774904020:  // restructuringClause
          return _restructuringClause;
        case -690339025:  // regionId
          return _regionId;
        case -2129778896:  // startDate
          return _startDate;
        case -930389515:  // effectiveDate
          return _effectiveDate;
        case -414641441:  // maturityDate
          return _maturityDate;
        case 1873675528:  // stubType
          return _stubType;
        case 144480214:  // couponFrequency
          return _couponFrequency;
        case 1905311443:  // dayCount
          return _dayCount;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case -1168632905:  // immAdjustMaturityDate
          return _immAdjustMaturityDate;
        case -490317146:  // adjustEffectiveDate
          return _adjustEffectiveDate;
        case -261898226:  // adjustMaturityDate
          return _adjustMaturityDate;
        case 1585636160:  // notional
          return _notional;
        case 2002873877:  // recoveryRate
          return _recoveryRate;
        case 2100149628:  // includeAccruedPremium
          return _includeAccruedPremium;
        case 2103482633:  // protectionStart
          return _protectionStart;
        case 640293516:  // cdsType
          return _cdsType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CreditDefaultSwapSecurity> builder() {
      throw new UnsupportedOperationException("CreditDefaultSwapSecurity is an abstract class");
    }

    @Override
    public Class<? extends CreditDefaultSwapSecurity> beanType() {
      return CreditDefaultSwapSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code buy} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> buy() {
      return _buy;
    }

    /**
     * The meta-property for the {@code protectionBuyer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> protectionBuyer() {
      return _protectionBuyer;
    }

    /**
     * The meta-property for the {@code protectionSeller} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> protectionSeller() {
      return _protectionSeller;
    }

    /**
     * The meta-property for the {@code referenceEntity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> referenceEntity() {
      return _referenceEntity;
    }

    /**
     * The meta-property for the {@code debtSeniority} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> debtSeniority() {
      return _debtSeniority;
    }

    /**
     * The meta-property for the {@code restructuringClause} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> restructuringClause() {
      return _restructuringClause;
    }

    /**
     * The meta-property for the {@code regionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> regionId() {
      return _regionId;
    }

    /**
     * The meta-property for the {@code startDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> startDate() {
      return _startDate;
    }

    /**
     * The meta-property for the {@code effectiveDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> effectiveDate() {
      return _effectiveDate;
    }

    /**
     * The meta-property for the {@code maturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> maturityDate() {
      return _maturityDate;
    }

    /**
     * The meta-property for the {@code stubType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StubType> stubType() {
      return _stubType;
    }

    /**
     * The meta-property for the {@code couponFrequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Frequency> couponFrequency() {
      return _couponFrequency;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DayCount> dayCount() {
      return _dayCount;
    }

    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConvention> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code immAdjustMaturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> immAdjustMaturityDate() {
      return _immAdjustMaturityDate;
    }

    /**
     * The meta-property for the {@code adjustEffectiveDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> adjustEffectiveDate() {
      return _adjustEffectiveDate;
    }

    /**
     * The meta-property for the {@code adjustMaturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> adjustMaturityDate() {
      return _adjustMaturityDate;
    }

    /**
     * The meta-property for the {@code notional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<InterestRateNotional> notional() {
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
     * The meta-property for the {@code includeAccruedPremium} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> includeAccruedPremium() {
      return _includeAccruedPremium;
    }

    /**
     * The meta-property for the {@code protectionStart} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> protectionStart() {
      return _protectionStart;
    }

    /**
     * The meta-property for the {@code cdsType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CDSType> cdsType() {
      return _cdsType;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}



