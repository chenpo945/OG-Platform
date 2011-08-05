/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.region;

import java.util.Set;

import javax.time.calendar.TimeZone;

import org.joda.beans.impl.flexi.FlexiBean;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.util.PublicSPI;
import com.opengamma.util.i18n.Country;
import com.opengamma.util.money.Currency;

/**
 * A region of the world.
 * <p>
 * Many aspects of business, algorithms and contracts are specific to a region.
 * The region may be of any size, from a municipality to a super-national group.
 */
@PublicSPI
public interface Region extends UniqueIdentifiable {

  /**
   * Gets the unique identifier of the region.
   * 
   * @return the unique identifier for this region entry, not null
   */
  UniqueId getUniqueId();

  /**
   * Gets the classification of the region.
   * 
   * @return the classification of region, such as SUPER_NATIONAL or INDEPENDENT_STATE, not null
   */
  RegionClassification getClassification();

  /**
   * Gets the unique identifiers of the regions that this region is a member of.
   * For example, a country might be a member of the World, UN, European Union and NATO.
   * 
   * @return the parent unique identifiers, null if this is the root entry
   */
  Set<UniqueId> getParentRegionIds();

  /**
   * Gets the short descriptive name of the region.
   * 
   * @return the name of the region, not null
   */
  String getName();

  /**
   * Gets the full descriptive name of the region.
   * 
   * @return the full name of the region, not null
   */
  String getFullName();

  /**
   * Gets the external identifier bundle defining the region.
   * <p>
   * This will include the country, currency and time-zone.
   * 
   * @return the bundle, null if not applicable
   */
  ExternalIdBundle getExternalIdBundle();

  /**
   * Gets the country.
   * 
   * @return the country, null if not applicable
   */
  Country getCountry();

  /**
   * Gets the currency.
   * 
   * @return the currency, null if not applicable
   */
  Currency getCurrency();

  /**
   * Gets the time-zone.
   * For larger regions, there can be multiple time-zones, so this is only reliable
   * for municipalities.
   * 
   * @return the time-zone, null if not applicable
   */
  TimeZone getTimeZone();

  /**
   * Gets the extensible data store for additional information.
   * Applications may store additional region based information here.
   * 
   * @return the additional data, not null
   */
  FlexiBean getData();

}
