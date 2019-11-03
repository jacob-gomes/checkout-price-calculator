package com.idealo.assignment.b2c.checkout.service.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.Cart;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.factory.OfferCalculatorFactory;

@Component
public class CheckoutServiceUtil {
	
	private OfferCalculatorFactory offerCalculatorFactory;
	
	@Autowired
	public CheckoutServiceUtil(OfferCalculatorFactory offerCalculatorFactory) {
		this.offerCalculatorFactory = offerCalculatorFactory;
	}

	public void calculateCartTotalCost(Cart cart, Map<String, PricingRule> mapOfGoodAndPricingRule) throws PricingRuleInconsistencyException {
		int totalCartCost = 0;
		Map<String, Integer> goodAndQuantityMap = cart.getGoodAndQuantityMap();
		
		for(Entry<String, Integer> goodAndQuantityMapEntry: goodAndQuantityMap.entrySet()) {
			String goodName = goodAndQuantityMapEntry.getKey();
			
			if(mapOfGoodAndPricingRule.containsKey(goodName)) {
				
				PricingRule pricingRule = mapOfGoodAndPricingRule.get(goodName);
				Integer quantityOfGood = goodAndQuantityMapEntry.getValue();
				
				if(null == pricingRule.getOffer()) {
					totalCartCost += pricingRule.getPrice() * quantityOfGood;
				}else {
					
					BiFunction<PricingRule, Integer, Integer> offerPriceCalculator = offerCalculatorFactory.getOfferCalculator(pricingRule.getOffer());					
					
					Integer finalPriceOfGoodWithQuantity = offerPriceCalculator.apply(pricingRule, quantityOfGood);
					
					totalCartCost += finalPriceOfGoodWithQuantity; 
					
				}
			
			}	else {
				throw new PricingRuleInconsistencyException("No price rule found for the good: " + goodName);
			}
		}
		
		cart.setTotalCost(totalCartCost);
	}

}
