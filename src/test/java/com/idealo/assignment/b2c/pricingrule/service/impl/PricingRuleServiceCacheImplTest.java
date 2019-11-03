package com.idealo.assignment.b2c.pricingrule.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

public class PricingRuleServiceCacheImplTest {
	
	private PricingRuleServiceCacheImpl pricingRuleServiceCacheImpl;
	
	@Before
	public void initiatePricingRule() {
		pricingRuleServiceCacheImpl = new PricingRuleServiceCacheImpl();
		pricingRuleServiceCacheImpl.refreshPriceRules();
	}
	
	@Test
	public void addNewPricingRulesTest() {
		List<PricingRule> listOfPricingRule = new ArrayList<>();
		PricingRule pricingRule;
		OfferImpl offerImpl;
		Map<String, PricingRule> mapOfGoodAndPricingRule;
		
		//A
		pricingRule = new PricingRule();
		pricingRule.setGood("A");
		pricingRule.setPrice(40);

		offerImpl = new OfferImpl();
		offerImpl.setOfferCount(3);
		offerImpl.setOfferPrice(100);
		pricingRule.setOffer(offerImpl);
		
		listOfPricingRule.add(pricingRule);
		
		//B
		pricingRule = new PricingRule();
		pricingRule.setGood("B");
		pricingRule.setPrice(50);

		offerImpl = new OfferImpl();
		offerImpl.setOfferCount(2);
		offerImpl.setOfferPrice(80);
		pricingRule.setOffer(offerImpl);
		
		listOfPricingRule.add(pricingRule);
		
		pricingRuleServiceCacheImpl.addNewPricingRules(listOfPricingRule);
		
		mapOfGoodAndPricingRule = pricingRuleServiceCacheImpl.getCurrentPriceRuleState();
		
		assertNotNull(mapOfGoodAndPricingRule.get("A"));
		
		pricingRule = mapOfGoodAndPricingRule.get("A");
		
		assertEquals("A",pricingRule.getGood());
		assertEquals(40,(int)pricingRule.getPrice());
		
		offerImpl = (OfferImpl) pricingRule.getOffer();
		assertNotNull(offerImpl);
		assertEquals(3,(int)offerImpl.getOfferCount());
		assertEquals(100,(int)offerImpl.getOfferPrice());
		
		assertNotNull(mapOfGoodAndPricingRule.get("B"));
		
		pricingRule = mapOfGoodAndPricingRule.get("B");
		
		assertEquals("B",pricingRule.getGood());
		assertEquals(50,(int)pricingRule.getPrice());
		
		offerImpl = (OfferImpl) pricingRule.getOffer();
		assertNotNull(offerImpl);
		assertEquals(2,(int)offerImpl.getOfferCount());
		assertEquals(80,(int)offerImpl.getOfferPrice());
	}
	
	@Test
	public void addAlreadyExistingPricingRulesTest() {
		List<PricingRule> listOfPricingRule = new ArrayList<>();
		PricingRule pricingRule;
		OfferImpl offerImpl;
		Map<String, PricingRule> mapOfGoodAndPricingRule;
		
		//A
		pricingRule = new PricingRule();
		pricingRule.setGood("A");
		pricingRule.setPrice(40);

		offerImpl = new OfferImpl();
		offerImpl.setOfferCount(3);
		offerImpl.setOfferPrice(100);
		pricingRule.setOffer(offerImpl);
		
		listOfPricingRule.add(pricingRule);
		
		//A
		pricingRule = new PricingRule();
		pricingRule.setGood("A");
		pricingRule.setPrice(50);

		offerImpl = new OfferImpl();
		offerImpl.setOfferCount(2);
		offerImpl.setOfferPrice(80);
		pricingRule.setOffer(offerImpl);
		
		listOfPricingRule.add(pricingRule);
		
		pricingRuleServiceCacheImpl.addNewPricingRules(listOfPricingRule);
		
		mapOfGoodAndPricingRule = pricingRuleServiceCacheImpl.getCurrentPriceRuleState();

		
		assertNotNull(mapOfGoodAndPricingRule.get("A"));
		
		pricingRule = mapOfGoodAndPricingRule.get("A");
		
		assertEquals("A",pricingRule.getGood());
		assertEquals(50,(int)pricingRule.getPrice());
		
		offerImpl = (OfferImpl) pricingRule.getOffer();
		assertNotNull(offerImpl);
		assertEquals(2,(int)offerImpl.getOfferCount());
		assertEquals(80,(int)offerImpl.getOfferPrice());
	}
	
}
