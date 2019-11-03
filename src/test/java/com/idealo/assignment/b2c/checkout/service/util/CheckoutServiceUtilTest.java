package com.idealo.assignment.b2c.checkout.service.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.Cart;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.factory.OfferCalculatorFactory;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceUtilTest {
	private CheckoutServiceUtil checkoutServiceUtil;
	
	Map<String, PricingRule>  mapOfGoodAndPricingRule;
	
	@Before
	public void init() {
		checkoutServiceUtil = new CheckoutServiceUtil(new OfferCalculatorFactory());
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
		offer.setOfferCount(2);
		offer.setOfferPrice(80);
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
	public void calculateCartTotalCostTest() throws PricingRuleInconsistencyException {
		Cart cart = new Cart();
		Map<String, Integer> goodAndQuantityMap = new HashMap<>();
		
		goodAndQuantityMap.put("A", 1);
		goodAndQuantityMap.put("B", 1);
		goodAndQuantityMap.put("C", 1);
		goodAndQuantityMap.put("D", 1);
		
		cart.setGoodAndQuantityMap(goodAndQuantityMap);
		
		checkoutServiceUtil.calculateCartTotalCost(cart, mapOfGoodAndPricingRule);
		
		assertEquals(135,(int)cart.getTotalCost());
	}
	
	@Test
	public void calculateCartTotalCostTestMultipleOffers() throws PricingRuleInconsistencyException {
		Cart cart = new Cart();
		Map<String, Integer> goodAndQuantityMap = new HashMap<>();
		
		goodAndQuantityMap.put("A", 4);
		goodAndQuantityMap.put("B", 4);
		
		cart.setGoodAndQuantityMap(goodAndQuantityMap);
		
		checkoutServiceUtil.calculateCartTotalCost(cart, mapOfGoodAndPricingRule);
		
		assertEquals(300,(int)cart.getTotalCost());
	}
	
	@Test(expected = PricingRuleInconsistencyException.class)
	public void calculateCartTotalCostWithUndefinedPricingRule() throws PricingRuleInconsistencyException {
		Cart cart = new Cart();
		Map<String, Integer> goodAndQuantityMap = new HashMap<>();
		
		goodAndQuantityMap.put("A", 1);
		goodAndQuantityMap.put("E", 1);
		
		cart.setGoodAndQuantityMap(goodAndQuantityMap);
		
		checkoutServiceUtil.calculateCartTotalCost(cart, mapOfGoodAndPricingRule);
		
	}
	
	@Test
	public void calculateCartTotalCostWithEmptyCart() throws PricingRuleInconsistencyException {
		Cart cart = new Cart();
		Map<String, Integer> goodAndQuantityMap = new HashMap<>();
		
		cart.setGoodAndQuantityMap(goodAndQuantityMap);
		
		checkoutServiceUtil.calculateCartTotalCost(cart, mapOfGoodAndPricingRule);
		
		assertEquals(0,(int)cart.getTotalCost());
	}
}
