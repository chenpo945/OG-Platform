/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.engine;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.engine.marketdata.NamedMarketDataSpecificationRepository;
import com.opengamma.financial.view.rest.RemoteNamedMarketDataSpecificationRepository;

/**
 * Factory for remote {@code NamedMarketDataSpecificationRepository}.
 */
@BeanDefinition
@SuppressWarnings("deprecation")
public class RemoteNamedMarketDataSpecificationRepositoryComponentFactory extends AbstractRemoteComponentServerComponentFactory {

  @Override
  protected void initComponent(ComponentRepository repo, ComponentInfo info) {
    if (NamedMarketDataSpecificationRepository.class.isAssignableFrom(info.getType())) {
      NamedMarketDataSpecificationRepository repository = new RemoteNamedMarketDataSpecificationRepository(info.getUri());
      repo.registerComponent(info, repository);
      if (isPublishRest()) {
        repo.getRestComponents().republish(info);
      }
    }   
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RemoteNamedMarketDataSpecificationRepositoryComponentFactory}.
   * @return the meta-bean, not null
   */
  public static RemoteNamedMarketDataSpecificationRepositoryComponentFactory.Meta meta() {
    return RemoteNamedMarketDataSpecificationRepositoryComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RemoteNamedMarketDataSpecificationRepositoryComponentFactory.Meta.INSTANCE);
  }

  @Override
  public RemoteNamedMarketDataSpecificationRepositoryComponentFactory.Meta metaBean() {
    return RemoteNamedMarketDataSpecificationRepositoryComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public RemoteNamedMarketDataSpecificationRepositoryComponentFactory clone() {
    return (RemoteNamedMarketDataSpecificationRepositoryComponentFactory) super.clone();
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
    buf.append("RemoteNamedMarketDataSpecificationRepositoryComponentFactory{");
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
   * The meta-bean for {@code RemoteNamedMarketDataSpecificationRepositoryComponentFactory}.
   */
  public static class Meta extends AbstractRemoteComponentServerComponentFactory.Meta {
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
    public BeanBuilder<? extends RemoteNamedMarketDataSpecificationRepositoryComponentFactory> builder() {
      return new DirectBeanBuilder<RemoteNamedMarketDataSpecificationRepositoryComponentFactory>(new RemoteNamedMarketDataSpecificationRepositoryComponentFactory());
    }

    @Override
    public Class<? extends RemoteNamedMarketDataSpecificationRepositoryComponentFactory> beanType() {
      return RemoteNamedMarketDataSpecificationRepositoryComponentFactory.class;
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
