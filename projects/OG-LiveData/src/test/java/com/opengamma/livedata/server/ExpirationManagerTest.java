/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.livedata.server;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Timer;

import net.sf.ehcache.CacheManager;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalScheme;
import com.opengamma.livedata.LiveDataSpecification;
import com.opengamma.livedata.client.HeartbeatSender;
import com.opengamma.livedata.client.ValueDistributor;
import com.opengamma.livedata.test.CollectingLiveDataListener;
import com.opengamma.transport.DirectInvocationByteArrayMessageSender;
import com.opengamma.util.ehcache.EHCacheUtils;
import com.opengamma.util.fudgemsg.OpenGammaFudgeContext;
import com.opengamma.util.test.TestGroup;

/**
 * Test.
 */
@Test(groups = {TestGroup.INTEGRATION, "ehcache" })
public class ExpirationManagerTest {

  private CacheManager _cacheManager;

  @BeforeClass
  public void setUpClass() {
    _cacheManager = EHCacheUtils.createTestCacheManager(getClass());
  }

  @AfterClass
  public void tearDownClass() {
    EHCacheUtils.shutdownQuiet(_cacheManager);
  }

  //-------------------------------------------------------------------------
  public void expirationWithHeartbeatSendingClient() throws InterruptedException {
    ExternalScheme identificationDomain = ExternalScheme.of("BbgId");

    MockLiveDataServer dataServer = new MockLiveDataServer(identificationDomain, _cacheManager);
    dataServer.connect();
    ExpirationManager expirationManager = dataServer.getExpirationManager();
    HeartbeatReceiver receiver = new HeartbeatReceiver(expirationManager);
    DirectInvocationByteArrayMessageSender conduit = new DirectInvocationByteArrayMessageSender(receiver);
    ValueDistributor valueDistributor = new ValueDistributor();
    Timer t = new Timer("HeartbeatConduitTest");
    new HeartbeatSender(conduit, valueDistributor, OpenGammaFudgeContext.getInstance(), t, 100);

    // subscribe on the client side - starts sending heartbeats
    LiveDataSpecification subscription = new LiveDataSpecification(
        dataServer.getDefaultNormalizationRuleSetId(),
        ExternalId.of(identificationDomain, "USSw5 Curncy"));
    CollectingLiveDataListener listener = new CollectingLiveDataListener();
    valueDistributor.addListener(subscription, listener);

    // subscribe on the server side
    dataServer.subscribe(subscription, false);

    // Send a couple of heartbeats
    Thread.sleep(300);

    // Stop sending heartbeats
    valueDistributor.removeListener(subscription, listener);

    // Wait for expiry
    Thread.sleep(150);

    expirationManager.housekeep(dataServer);

    assertEquals(1, dataServer.getActualSubscriptions().size());
    assertEquals(1, dataServer.getActualUnsubscriptions().size());
    assertEquals(subscription.getIdentifier(identificationDomain), dataServer.getActualSubscriptions().get(0));
    assertEquals(subscription.getIdentifier(identificationDomain), dataServer.getActualUnsubscriptions().get(0));
  }

  public void expirationWithClientThatDoesNotSendHeartbeats() throws InterruptedException {
    ExternalScheme identificationDomain = ExternalScheme.of("BbgId");

    MockLiveDataServer dataServer = new MockLiveDataServer(identificationDomain, _cacheManager);
    dataServer.connect();
    ExpirationManager expirationManager = dataServer.getExpirationManager();

    // subscribe on the server side
    LiveDataSpecification subscription = new LiveDataSpecification(
        dataServer.getDefaultNormalizationRuleSetId(),
        ExternalId.of(identificationDomain, "USSw5 Curncy"));
    dataServer.subscribe("USSw5 Curncy");

    assertEquals(1, dataServer.getActualSubscriptions().size());
    assertEquals(subscription.getIdentifier(identificationDomain), dataServer.getActualSubscriptions().get(0));

    // Wait for expiry
    Thread.sleep(150);

    expirationManager.housekeep(dataServer);

    assertEquals(1, dataServer.getActualUnsubscriptions().size());
    assertEquals(subscription.getIdentifier(identificationDomain), dataServer.getActualUnsubscriptions().get(0));
  }

}
