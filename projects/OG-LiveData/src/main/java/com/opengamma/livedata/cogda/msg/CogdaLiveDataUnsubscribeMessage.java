/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * A client-side request to unsubscribe from a particular external ID.
 * There is not response for this message in the protocol. 
 */
@BeanDefinition
public class CogdaLiveDataUnsubscribeMessage extends CogdaLiveDataCommandMessage {

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CogdaLiveDataUnsubscribeMessage}.
   * @return the meta-bean, not null
   */
  public static CogdaLiveDataUnsubscribeMessage.Meta meta() {
    return CogdaLiveDataUnsubscribeMessage.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CogdaLiveDataUnsubscribeMessage.Meta.INSTANCE);
  }

  @Override
  public CogdaLiveDataUnsubscribeMessage.Meta metaBean() {
    return CogdaLiveDataUnsubscribeMessage.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public CogdaLiveDataUnsubscribeMessage clone() {
    return (CogdaLiveDataUnsubscribeMessage) super.clone();
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
    buf.append("CogdaLiveDataUnsubscribeMessage{");
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
   * The meta-bean for {@code CogdaLiveDataUnsubscribeMessage}.
   */
  public static class Meta extends CogdaLiveDataCommandMessage.Meta {
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
    public BeanBuilder<? extends CogdaLiveDataUnsubscribeMessage> builder() {
      return new DirectBeanBuilder<CogdaLiveDataUnsubscribeMessage>(new CogdaLiveDataUnsubscribeMessage());
    }

    @Override
    public Class<? extends CogdaLiveDataUnsubscribeMessage> beanType() {
      return CogdaLiveDataUnsubscribeMessage.class;
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
