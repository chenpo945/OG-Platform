/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.loader;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.ComponentRepository;
import com.opengamma.master.security.SecurityLoader;
import com.opengamma.master.security.impl.UnsupportedSecurityLoader;

/**
 * Component factory providing the {@code SecurityLoader}.
 * <p>
 * This implementation uses {@link UnsupportedSecurityLoader}.
 */
@BeanDefinition
public class UnsupportedSecurityLoaderComponentFactory extends AbstractSecurityLoaderComponentFactory {

  /**
   * Creates the loader, without registering it.
   * <p>
   * This implementation uses {@link UnsupportedSecurityLoader}.
   * 
   * @param repo  the component repository, only used to register secondary items like lifecycle, not null
   * @return the loader, not null
   */
  protected SecurityLoader createSecurityLoader(ComponentRepository repo) {
    return new UnsupportedSecurityLoader();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code UnsupportedSecurityLoaderComponentFactory}.
   * @return the meta-bean, not null
   */
  public static UnsupportedSecurityLoaderComponentFactory.Meta meta() {
    return UnsupportedSecurityLoaderComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(UnsupportedSecurityLoaderComponentFactory.Meta.INSTANCE);
  }

  @Override
  public UnsupportedSecurityLoaderComponentFactory.Meta metaBean() {
    return UnsupportedSecurityLoaderComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public UnsupportedSecurityLoaderComponentFactory clone() {
    return (UnsupportedSecurityLoaderComponentFactory) super.clone();
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
    buf.append("UnsupportedSecurityLoaderComponentFactory{");
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
   * The meta-bean for {@code UnsupportedSecurityLoaderComponentFactory}.
   */
  public static class Meta extends AbstractSecurityLoaderComponentFactory.Meta {
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
    public BeanBuilder<? extends UnsupportedSecurityLoaderComponentFactory> builder() {
      return new DirectBeanBuilder<UnsupportedSecurityLoaderComponentFactory>(new UnsupportedSecurityLoaderComponentFactory());
    }

    @Override
    public Class<? extends UnsupportedSecurityLoaderComponentFactory> beanType() {
      return UnsupportedSecurityLoaderComponentFactory.class;
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
