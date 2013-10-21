/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.orgs;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.obligor.CreditRating;
import com.opengamma.core.obligor.CreditRatingFitch;
import com.opengamma.core.obligor.CreditRatingMoodys;
import com.opengamma.core.obligor.CreditRatingStandardAndPoors;
import com.opengamma.core.obligor.Region;
import com.opengamma.core.obligor.Sector;
import com.opengamma.core.obligor.definition.Obligor;
import com.opengamma.core.organization.Organization;
import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.PublicSPI;

/**
 * An organization
 * <p/>
 */
@PublicSPI
@BeanDefinition
public class ManageableOrganization extends DirectBean implements Organization, MutableUniqueIdentifiable, UniqueIdentifiable {

  /**
   * The organization unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  @PropertyDefinition
  private Obligor _obligor;

  /**
   * Creates an empty organization.
   */
  public ManageableOrganization() {
  }

  /**
   * Creates an organization.
   * 
   * @param obligorShortName the obligor short name, not null.
   * @param obligorREDCode the obligor red code, not null.
   * @param obligorTicker the obligor ticker, not null.
   * @param region the region, not null.
   * @param country the country, not null.
   * @param sector the sector, not null.
   * @param compositeRating the composite rating, not null.
   * @param impliedRating the implied rating, not null.
   * @param fitchCreditRating the fitch credit rating, not null.
   * @param moodysCreditRating the moodys credit rating, not null.
   * @param standardAndPoorsCreditRating the standard and poors credit rating, not null.
   * @param hasDefaulted true if has defaulted, false otherwise.
   */
  public ManageableOrganization(final String obligorShortName, 
      final String obligorREDCode, 
      final String obligorTicker, 
      final Region region, 
      final String country, 
      final Sector sector, 
      final CreditRating compositeRating, 
      final CreditRating impliedRating, 
      final CreditRatingFitch fitchCreditRating, 
      final CreditRatingMoodys moodysCreditRating, 
      final CreditRatingStandardAndPoors standardAndPoorsCreditRating, 
      final boolean hasDefaulted) {
    
    setObligor(new Obligor(obligorTicker, 
        obligorShortName, 
        obligorREDCode, 
        compositeRating, 
        impliedRating, 
        moodysCreditRating, 
        standardAndPoorsCreditRating, 
        fitchCreditRating, 
        hasDefaulted, 
        sector, 
        region, 
        country));
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageableOrganization}.
   * @return the meta-bean, not null
   */
  public static ManageableOrganization.Meta meta() {
    return ManageableOrganization.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ManageableOrganization.Meta.INSTANCE);
  }

  @Override
  public ManageableOrganization.Meta metaBean() {
    return ManageableOrganization.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the organization unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the organization unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the obligor.
   * @return the value of the property
   */
  public Obligor getObligor() {
    return _obligor;
  }

  /**
   * Sets the obligor.
   * @param obligor  the new value of the property
   */
  public void setObligor(Obligor obligor) {
    this._obligor = obligor;
  }

  /**
   * Gets the the {@code obligor} property.
   * @return the property, not null
   */
  public final Property<Obligor> obligor() {
    return metaBean().obligor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ManageableOrganization clone() {
    BeanBuilder<? extends ManageableOrganization> builder = metaBean().builder();
    for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
      if (mp.style().isBuildable()) {
        Object value = mp.get(this);
        if (value instanceof Bean) {
          value = ((Bean) value).clone();
        }
        builder.set(mp.name(), value);
      }
    }
    return builder.build();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ManageableOrganization other = (ManageableOrganization) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getObligor(), other.getObligor());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObligor());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("ManageableOrganization{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
    buf.append("obligor").append('=').append(JodaBeanUtils.toString(getObligor())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageableOrganization}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", ManageableOrganization.class, UniqueId.class);
    /**
     * The meta-property for the {@code obligor} property.
     */
    private final MetaProperty<Obligor> _obligor = DirectMetaProperty.ofReadWrite(
        this, "obligor", ManageableOrganization.class, Obligor.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "obligor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case -1657678854:  // obligor
          return _obligor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ManageableOrganization> builder() {
      return new DirectBeanBuilder<ManageableOrganization>(new ManageableOrganization());
    }

    @Override
    public Class<? extends ManageableOrganization> beanType() {
      return ManageableOrganization.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code obligor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Obligor> obligor() {
      return _obligor;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((ManageableOrganization) bean).getUniqueId();
        case -1657678854:  // obligor
          return ((ManageableOrganization) bean).getObligor();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((ManageableOrganization) bean).setUniqueId((UniqueId) newValue);
          return;
        case -1657678854:  // obligor
          ((ManageableOrganization) bean).setObligor((Obligor) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
