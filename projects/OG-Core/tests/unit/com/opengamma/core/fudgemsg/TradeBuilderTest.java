/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.fudgemsg;

import java.math.BigDecimal;

import javax.time.calendar.LocalDate;
import javax.time.calendar.OffsetTime;

import org.testng.annotations.Test;

import com.opengamma.core.position.Trade;
import com.opengamma.core.position.impl.CounterpartyImpl;
import com.opengamma.core.position.impl.TradeImpl;
import com.opengamma.core.security.SecurityLink;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueId;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.AbstractBuilderTestCase;

/**
 * Test the {@link TradeBuilder} class.
 */
@Test
public class TradeBuilderTest extends AbstractBuilderTestCase {

  public void testEmpty() {
    TradeImpl trade = new TradeImpl();
    assertEncodeDecodeCycle(Trade.class, trade);
  }
  
  public void testTrade() {
    TradeImpl trade = new TradeImpl();
    trade.setUniqueId(UniqueId.of("A", "B"));
    trade.setParentPositionId(UniqueId.of("C", "D"));
    trade.setQuantity(BigDecimal.valueOf(12.34d));
    trade.setSecurityLink(new SecurityLink(ExternalId.of("E", "F")));
    trade.setCounterparty(new CounterpartyImpl(ExternalId.of("G", "H")));
    trade.setTradeDate(LocalDate.of(2011, 1, 5));
    trade.setTradeTime(OffsetTime.parse("14:30+02:00"));
    assertEncodeDecodeCycle(Trade.class, trade);
  }

  public void testFull() {
    TradeImpl trade = new TradeImpl();
    trade.setUniqueId(UniqueId.of("A", "B"));
    trade.setParentPositionId(UniqueId.of("C", "D"));
    trade.setQuantity(BigDecimal.valueOf(12.34d));
    trade.setSecurityLink(new SecurityLink(ExternalId.of("E", "F")));
    trade.setCounterparty(new CounterpartyImpl(ExternalId.of("G", "H")));
    trade.setTradeDate(LocalDate.of(2011, 1, 5));
    trade.setTradeTime(OffsetTime.parse("14:30+02:00"));
    
    //set premium
    trade.setPremium(100.00);
    trade.setPremiumCurrency(Currency.USD);
    trade.setPremiumDate(LocalDate.of(2011, 1, 6));
    trade.setPremiumTime(OffsetTime.parse("15:30+02:00"));
    
    //set attributes
    trade.addAttribute("A", "B");
    trade.addAttribute("C", "D");
    assertEncodeDecodeCycle(Trade.class, trade);
  }
  
  public void testTrade_withPremium() {
    TradeImpl trade = new TradeImpl();
    trade.setUniqueId(UniqueId.of("A", "B"));
    trade.setParentPositionId(UniqueId.of("C", "D"));
    trade.setQuantity(BigDecimal.valueOf(12.34d));
    trade.setSecurityLink(new SecurityLink(ObjectId.of("E", "F")));
    trade.setCounterparty(new CounterpartyImpl(ExternalId.of("G", "H")));
    trade.setTradeDate(LocalDate.of(2011, 1, 5));
    trade.setTradeTime(OffsetTime.parse("14:30+02:00"));
    
    //set premium
    trade.setPremium(100.00);
    trade.setPremiumCurrency(Currency.USD);
    trade.setPremiumDate(LocalDate.of(2011, 1, 6));
    trade.setPremiumTime(OffsetTime.parse("15:30+02:00"));
    assertEncodeDecodeCycle(Trade.class, trade);
  }
  
  public void testTrade_withAttributes() {
    TradeImpl trade = new TradeImpl();
    trade.setUniqueId(UniqueId.of("A", "B"));
    trade.setParentPositionId(UniqueId.of("C", "D"));
    trade.setQuantity(BigDecimal.valueOf(12.34d));
    trade.setSecurityLink(new SecurityLink(ExternalId.of("E", "F")));
    trade.setCounterparty(new CounterpartyImpl(ExternalId.of("G", "H")));
    trade.setTradeDate(LocalDate.of(2011, 1, 5));
    trade.setTradeTime(OffsetTime.parse("14:30+02:00"));
    
    //set attributes
    trade.addAttribute("A", "B");
    trade.addAttribute("C", "D");
    assertEncodeDecodeCycle(Trade.class, trade);
  }

}
