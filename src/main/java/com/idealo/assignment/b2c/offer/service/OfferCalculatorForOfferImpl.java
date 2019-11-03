package com.idealo.assignment.b2c.offer.service;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

public class OfferCalculatorForOfferImpl {
	
	public static Integer calculateOfferPrice(PricingRule pricingRule, Integer quantityOfGood) {
		
		OfferImpl offer = (OfferImpl)pricingRule.getOffer();
		Integer quantityCountInOfferPrice;
		Integer quantityCountInRegularPrice;
		Integer finalPriceOfGoodWithQuantity;
		Integer offerPrice;
		Integer offerCount;

		if(null == offer) {
			throw new PricingRuleInconsistencyException("offer is null");
		}
		
		offerCount = offer.getOfferCount();
		offerPrice = offer.getOfferPrice();
		
		if(null == offerPrice || null == offerCount) {
			throw new PricingRuleInconsistencyException("Offerprice or offerCount is null");
		}
		
		
		quantityCountInOfferPrice = quantityOfGood / offerCount;
		quantityCountInRegularPrice = quantityOfGood % offerCount;
		
		finalPriceOfGoodWithQuantity = quantityCountInOfferPrice * offerPrice + quantityCountInRegularPrice * pricingRule.getPrice();
		return finalPriceOfGoodWithQuantity;
	}
}
