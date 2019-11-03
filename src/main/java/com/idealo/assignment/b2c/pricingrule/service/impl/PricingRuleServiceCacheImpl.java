package com.idealo.assignment.b2c.pricingrule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.pricingrule.service.PricingRuleService;

@Component
public class PricingRuleServiceCacheImpl implements PricingRuleService{
	private static Map<String, PricingRule> mapOfGoodAndPricingRule;
	
	@PostConstruct
	private void initiateMapOfPricingRuleInstance(){
		mapOfGoodAndPricingRule = new HashMap<>();
	}

	@Override
	public void addNewPricingRules(List<PricingRule> listOfPricingRule) {
		if(null == PricingRuleServiceCacheImpl.mapOfGoodAndPricingRule) {
			return;
		}
		
		for(PricingRule pricingRule : listOfPricingRule) {
			PricingRuleServiceCacheImpl.mapOfGoodAndPricingRule.put(pricingRule.getGood(), pricingRule);
		}
	}

	@Override
	public Map<String, PricingRule> getCurrentPriceRuleState() {
		return mapOfGoodAndPricingRule;
	}

	@Override
	public void refreshPriceRules() {
		initiateMapOfPricingRuleInstance();		
	}
	
	
}
