/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb.spring;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.change.JmsChangeManager;
import com.opengamma.masterdb.portfolio.DbPortfolioMaster;

/**
 * Spring factory bean to create the database portfolio master.
 */
@BeanDefinition
public class DbPortfolioMasterFactoryBean extends AbstractDbMasterFactoryBean<DbPortfolioMaster> {

  /**
   * Creates an instance.
   */
  public DbPortfolioMasterFactoryBean() {
    super(DbPortfolioMaster.class);
  }

  //-------------------------------------------------------------------------
  @Override
  public DbPortfolioMaster createObject() {
    DbPortfolioMaster master = new DbPortfolioMaster(getDbConnector());
    if (getUniqueIdScheme() != null) {
      master.setUniqueIdScheme(getUniqueIdScheme());
    }
    if (getMaxRetries() != null) {
      master.setMaxRetries(getMaxRetries());
    }
    if (getJmsConnector() != null) {
      JmsChangeManager cm = new JmsChangeManager(getJmsConnector().ensureTopicName(getJmsChangeManagerTopic()));
      master.setChangeManager(cm);
      cm.start();
    }
    return master;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbPortfolioMasterFactoryBean}.
   * @return the meta-bean, not null
   */
  public static DbPortfolioMasterFactoryBean.Meta meta() {
    return DbPortfolioMasterFactoryBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DbPortfolioMasterFactoryBean.Meta.INSTANCE);
  }

  @Override
  public DbPortfolioMasterFactoryBean.Meta metaBean() {
    return DbPortfolioMasterFactoryBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public DbPortfolioMasterFactoryBean clone() {
    return (DbPortfolioMasterFactoryBean) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("DbPortfolioMasterFactoryBean{");
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
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbPortfolioMasterFactoryBean}.
   */
  public static class Meta extends AbstractDbMasterFactoryBean.Meta<DbPortfolioMaster> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends DbPortfolioMasterFactoryBean> builder() {
      return new DirectBeanBuilder<DbPortfolioMasterFactoryBean>(new DbPortfolioMasterFactoryBean());
    }

    @Override
    public Class<? extends DbPortfolioMasterFactoryBean> beanType() {
      return DbPortfolioMasterFactoryBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
