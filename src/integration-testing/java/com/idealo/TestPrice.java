package com.idealo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealo.assignment.b2c.App;
import com.idealo.assignment.b2c.checkout.service.CheckOutService;
import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
public class TestPrice {

	@Autowired
	private CheckOutService co;

	public int calculatePrice(String goods) throws PricingRuleInconsistencyException {
		for(int i=0; i<goods.length(); i++) {
			co.scan(String.valueOf(goods.charAt(i)));
		}
		int totalCartCost = co.getTotalCartCost();
		co.refreshCart();
		return totalCartCost;
	}
	
	@Before
	public void initiatePricingRule() {
		List<PricingRule> listOfPricingRule = new ArrayList<>();
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
		
		listOfPricingRule.add(pricingRule);
		
		//B
		pricingRule = new PricingRule();
		pricingRule.setGood("B");
		pricingRule.setPrice(50);

		offer = new OfferImpl();
		offer.setOfferCount(2);
		offer.setOfferPrice(80);
		pricingRule.setOffer(offer);
		
		listOfPricingRule.add(pricingRule);
		
		
		//C
		pricingRule = new PricingRule();
		pricingRule.setGood("C");
		pricingRule.setPrice(25);
		
		listOfPricingRule.add(pricingRule);
		
		//D
		pricingRule = new PricingRule();
		pricingRule.setGood("D");
		pricingRule.setPrice(20);
		
		listOfPricingRule.add(pricingRule);
		
		
		co.addNewPricingRules(listOfPricingRule);
	}
	
	@Test
	public void totals() throws PricingRuleInconsistencyException {
		assertEquals(0, calculatePrice(""));
		assertEquals(40, calculatePrice("A"));
		assertEquals(90, calculatePrice("AB"));
		assertEquals(135, calculatePrice("CDBA"));
		assertEquals(80, calculatePrice("AA"));
		assertEquals(100, calculatePrice("AAA"));
		assertEquals(140, calculatePrice("AAAA"));
		assertEquals(180, calculatePrice("AAAAA"));
		assertEquals(200, calculatePrice("AAAAAA"));
		assertEquals(150, calculatePrice("AAAB"));
		assertEquals(180, calculatePrice("AAABB"));
		assertEquals(200, calculatePrice("AAABBD"));
		assertEquals(200, calculatePrice("DABABA"));
	}
	
	@Test
	public void incremental() throws PricingRuleInconsistencyException {
		assertEquals(0, co.getTotalCartCost());
		co.scan("A"); assertEquals(40, co.getTotalCartCost());
		co.scan("B"); assertEquals(90, co.getTotalCartCost());
		co.scan("A"); assertEquals(130, co.getTotalCartCost());
		co.scan("A"); assertEquals(150, co.getTotalCartCost());
		co.scan("B"); assertEquals(180, co.getTotalCartCost());
	}
}
