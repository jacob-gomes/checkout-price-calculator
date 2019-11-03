package com.idealo.assignment.b2c.offer.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

public class OfferCalculatorForOfferImplTest {

	private Map<String, PricingRule>  mapOfGoodAndPricingRule;
	
	@Before
	public void init() {
		mapOfGoodAndPricingRule = new HashMap<>();

		PricingRule pricingRule;
		OfferImpl offer;
		
		//A
		pricingRule = new PricingRule();
		pricingRule.setGood("A");
		pricingRule.setPrice(40);

		offer = new OfferImpl();
		offer.setOfferCount(3);
		offer.setOfferPrice(100);
		pricingRule.setOffer(offer);
		
		mapOfGoodAndPricingRule.put("A",pricingRule);
		
		//B
		pricingRule = new PricingRule();
		pricingRule.setGood("B");
		pricingRule.setPrice(50);

		offer = new OfferImpl();
		offer.setOfferCount(null);
		offer.setOfferPrice(null);
		pricingRule.setOffer(offer);
		
		mapOfGoodAndPricingRule.put("B",pricingRule);
		
		
		//C
		pricingRule = new PricingRule();
		pricingRule.setGood("C");
		pricingRule.setPrice(25);
		
		mapOfGoodAndPricingRule.put("C",pricingRule);
		
		//D
		pricingRule = new PricingRule();
		pricingRule.setGood("D");
		pricingRule.setPrice(20);
		
		mapOfGoodAndPricingRule.put("D",pricingRule);
		
	
	}
	
	@Test
	public void calculateCartTotalCostTestMultipleOffers() throws PricingRuleInconsistencyException {
		
		int totalCostOfItem = OfferCalculatorForOfferImpl.calculateOfferPrice(mapOfGoodAndPricingRule.get("A"), 4);
		
		assertEquals(140,totalCostOfItem);
	}
	
	@Test(expected = PricingRuleInconsistencyException.class)
	public void calculateCartTotalCostWithUndefinedOffer() throws PricingRuleInconsistencyException {

		 OfferCalculatorForOfferImpl.calculateOfferPrice(mapOfGoodAndPricingRule.get("D"), 4);
		
	}
	
	@Test(expected = PricingRuleInconsistencyException.class)
	public void calculateCartTotalCostWithNullValuesInOfferAttributes() throws PricingRuleInconsistencyException {

		OfferCalculatorForOfferImpl.calculateOfferPrice(mapOfGoodAndPricingRule.get("B"), 4);
		
	}
}
