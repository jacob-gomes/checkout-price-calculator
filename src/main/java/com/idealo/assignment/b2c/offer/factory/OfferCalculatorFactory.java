package com.idealo.assignment.b2c.offer.factory;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.offer.model.Offer;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;
import com.idealo.assignment.b2c.offer.service.OfferCalculatorForOfferImpl;

@Component
public class OfferCalculatorFactory {
	
	public BiFunction<PricingRule, Integer, Integer> getOfferCalculator(Offer offerObject){
		
		if(offerObject instanceof OfferImpl) {
			return OfferCalculatorForOfferImpl::calculateOfferPrice;
		}
		
		return null;
	}
}
