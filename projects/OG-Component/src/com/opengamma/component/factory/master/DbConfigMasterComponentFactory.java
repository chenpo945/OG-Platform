/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.core.change.JmsChangeManager;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.impl.DataConfigsResource;
import com.opengamma.masterdb.config.DbConfigMaster;
import com.opengamma.util.db.DbConnector;
import com.opengamma.util.jms.JmsConnector;

/**
 * Component factory for the database config master.
 */
@BeanDefinition
public class DbConfigMasterComponentFactory extends AbstractComponentFactory {

  /**
   * The classifier that the factory should publish under.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The flag determining whether the component should be published by REST.
   */
  @PropertyDefinition
  private boolean _publishRest;
  /**
   * The database connector.
   */
  @PropertyDefinition(validate = "notNull")
  private DbConnector _dbConnector;
  /**
   * The JMS connector.
   */
  @PropertyDefinition
  private JmsConnector _jmsConnector;
  /**
   * The JMS change manager topic.
   */
  @PropertyDefinition
  private String _jmsChangeManagerTopic;
  /**
   * The scheme used by the {@code UniqueId}.
   */
  @PropertyDefinition
  private String _idScheme;
  /**
   * The maximum number of retries when updating.
   */
  @PropertyDefinition
  private Integer _maxRetries;

  //-------------------------------------------------------------------------
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) {
    ComponentInfo info = new ComponentInfo(ConfigMaster.class, getClassifier());
    
    // create
    DbConfigMaster master = new DbConfigMaster(getDbConnector());
    if (getIdScheme() != null) {
      master.setUniqueIdScheme(getIdScheme());
    }
    if (getMaxRetries() != null) {
      master.setMaxRetries(getMaxRetries());
    }
    if (getJmsChangeManagerTopic() != null) {
      JmsChangeManager cm = new JmsChangeManager(getJmsConnector(), getJmsChangeManagerTopic());
      master.setChangeManager(cm);
      repo.registerLifecycle(cm);
      info.addAttribute(ComponentInfoAttributes.JMS_CHANGE_MANAGER_TOPIC, getJmsChangeManagerTopic());
    }
    
    // register
    info.addAttribute(ComponentInfoAttributes.UNIQUE_ID_SCHEME, master.getUniqueIdScheme());
    repo.registerComponent(info, master);
    
    // publish
    if (isPublishRest()) {
      repo.publishRest(info, new DataConfigsResource(master));
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbConfigMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static DbConfigMasterComponentFactory.Meta meta() {
    return DbConfigMasterComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(DbConfigMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public DbConfigMasterComponentFactory.Meta metaBean() {
    return DbConfigMasterComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -614707837:  // publishRest
        return isPublishRest();
      case 39794031:  // dbConnector
        return getDbConnector();
      case -1495762275:  // jmsConnector
        return getJmsConnector();
      case -758086398:  // jmsChangeManagerTopic
        return getJmsChangeManagerTopic();
      case -661606752:  // idScheme
        return getIdScheme();
      case -2022653118:  // maxRetries
        return getMaxRetries();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -614707837:  // publishRest
        setPublishRest((Boolean) newValue);
        return;
      case 39794031:  // dbConnector
        setDbConnector((DbConnector) newValue);
        return;
      case -1495762275:  // jmsConnector
        setJmsConnector((JmsConnector) newValue);
        return;
      case -758086398:  // jmsChangeManagerTopic
        setJmsChangeManagerTopic((String) newValue);
        return;
      case -661606752:  // idScheme
        setIdScheme((String) newValue);
        return;
      case -2022653118:  // maxRetries
        setMaxRetries((Integer) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_dbConnector, "dbConnector");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      DbConfigMasterComponentFactory other = (DbConfigMasterComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(isPublishRest(), other.isPublishRest()) &&
          JodaBeanUtils.equal(getDbConnector(), other.getDbConnector()) &&
          JodaBeanUtils.equal(getJmsConnector(), other.getJmsConnector()) &&
          JodaBeanUtils.equal(getJmsChangeManagerTopic(), other.getJmsChangeManagerTopic()) &&
          JodaBeanUtils.equal(getIdScheme(), other.getIdScheme()) &&
          JodaBeanUtils.equal(getMaxRetries(), other.getMaxRetries()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(isPublishRest());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getJmsConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getJmsChangeManagerTopic());
    hash += hash * 31 + JodaBeanUtils.hashCode(getIdScheme());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMaxRetries());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier that the factory should publish under.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier that the factory should publish under.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the flag determining whether the component should be published by REST.
   * @return the value of the property
   */
  public boolean isPublishRest() {
    return _publishRest;
  }

  /**
   * Sets the flag determining whether the component should be published by REST.
   * @param publishRest  the new value of the property
   */
  public void setPublishRest(boolean publishRest) {
    this._publishRest = publishRest;
  }

  /**
   * Gets the the {@code publishRest} property.
   * @return the property, not null
   */
  public final Property<Boolean> publishRest() {
    return metaBean().publishRest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database connector.
   * @return the value of the property, not null
   */
  public DbConnector getDbConnector() {
    return _dbConnector;
  }

  /**
   * Sets the database connector.
   * @param dbConnector  the new value of the property, not null
   */
  public void setDbConnector(DbConnector dbConnector) {
    JodaBeanUtils.notNull(dbConnector, "dbConnector");
    this._dbConnector = dbConnector;
  }

  /**
   * Gets the the {@code dbConnector} property.
   * @return the property, not null
   */
  public final Property<DbConnector> dbConnector() {
    return metaBean().dbConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS connector.
   * @return the value of the property
   */
  public JmsConnector getJmsConnector() {
    return _jmsConnector;
  }

  /**
   * Sets the JMS connector.
   * @param jmsConnector  the new value of the property
   */
  public void setJmsConnector(JmsConnector jmsConnector) {
    this._jmsConnector = jmsConnector;
  }

  /**
   * Gets the the {@code jmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> jmsConnector() {
    return metaBean().jmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS change manager topic.
   * @return the value of the property
   */
  public String getJmsChangeManagerTopic() {
    return _jmsChangeManagerTopic;
  }

  /**
   * Sets the JMS change manager topic.
   * @param jmsChangeManagerTopic  the new value of the property
   */
  public void setJmsChangeManagerTopic(String jmsChangeManagerTopic) {
    this._jmsChangeManagerTopic = jmsChangeManagerTopic;
  }

  /**
   * Gets the the {@code jmsChangeManagerTopic} property.
   * @return the property, not null
   */
  public final Property<String> jmsChangeManagerTopic() {
    return metaBean().jmsChangeManagerTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the scheme used by the {@code UniqueId}.
   * @return the value of the property
   */
  public String getIdScheme() {
    return _idScheme;
  }

  /**
   * Sets the scheme used by the {@code UniqueId}.
   * @param idScheme  the new value of the property
   */
  public void setIdScheme(String idScheme) {
    this._idScheme = idScheme;
  }

  /**
   * Gets the the {@code idScheme} property.
   * @return the property, not null
   */
  public final Property<String> idScheme() {
    return metaBean().idScheme().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maximum number of retries when updating.
   * @return the value of the property
   */
  public Integer getMaxRetries() {
    return _maxRetries;
  }

  /**
   * Sets the maximum number of retries when updating.
   * @param maxRetries  the new value of the property
   */
  public void setMaxRetries(Integer maxRetries) {
    this._maxRetries = maxRetries;
  }

  /**
   * Gets the the {@code maxRetries} property.
   * @return the property, not null
   */
  public final Property<Integer> maxRetries() {
    return metaBean().maxRetries().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbConfigMasterComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", DbConfigMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code publishRest} property.
     */
    private final MetaProperty<Boolean> _publishRest = DirectMetaProperty.ofReadWrite(
        this, "publishRest", DbConfigMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code dbConnector} property.
     */
    private final MetaProperty<DbConnector> _dbConnector = DirectMetaProperty.ofReadWrite(
        this, "dbConnector", DbConfigMasterComponentFactory.class, DbConnector.class);
    /**
     * The meta-property for the {@code jmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _jmsConnector = DirectMetaProperty.ofReadWrite(
        this, "jmsConnector", DbConfigMasterComponentFactory.class, JmsConnector.class);
    /**
     * The meta-property for the {@code jmsChangeManagerTopic} property.
     */
    private final MetaProperty<String> _jmsChangeManagerTopic = DirectMetaProperty.ofReadWrite(
        this, "jmsChangeManagerTopic", DbConfigMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code idScheme} property.
     */
    private final MetaProperty<String> _idScheme = DirectMetaProperty.ofReadWrite(
        this, "idScheme", DbConfigMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code maxRetries} property.
     */
    private final MetaProperty<Integer> _maxRetries = DirectMetaProperty.ofReadWrite(
        this, "maxRetries", DbConfigMasterComponentFactory.class, Integer.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "publishRest",
        "dbConnector",
        "jmsConnector",
        "jmsChangeManagerTopic",
        "idScheme",
        "maxRetries");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -614707837:  // publishRest
          return _publishRest;
        case 39794031:  // dbConnector
          return _dbConnector;
        case -1495762275:  // jmsConnector
          return _jmsConnector;
        case -758086398:  // jmsChangeManagerTopic
          return _jmsChangeManagerTopic;
        case -661606752:  // idScheme
          return _idScheme;
        case -2022653118:  // maxRetries
          return _maxRetries;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends DbConfigMasterComponentFactory> builder() {
      return new DirectBeanBuilder<DbConfigMasterComponentFactory>(new DbConfigMasterComponentFactory());
    }

    @Override
    public Class<? extends DbConfigMasterComponentFactory> beanType() {
      return DbConfigMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code publishRest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> publishRest() {
      return _publishRest;
    }

    /**
     * The meta-property for the {@code dbConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbConnector> dbConnector() {
      return _dbConnector;
    }

    /**
     * The meta-property for the {@code jmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> jmsConnector() {
      return _jmsConnector;
    }

    /**
     * The meta-property for the {@code jmsChangeManagerTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> jmsChangeManagerTopic() {
      return _jmsChangeManagerTopic;
    }

    /**
     * The meta-property for the {@code idScheme} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> idScheme() {
      return _idScheme;
    }

    /**
     * The meta-property for the {@code maxRetries} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> maxRetries() {
      return _maxRetries;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
