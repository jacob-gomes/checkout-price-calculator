package com.idealo.assignment.b2c.pricingrule.service;

import java.util.List;
import java.util.Map;

import com.idealo.assignment.b2c.model.PricingRule;

public interface PricingRuleService {
	void addNewPricingRules(List<PricingRule> listOfPricingRule);
	
	Map<String, PricingRule> getCurrentPriceRuleState();
	
	void refreshPriceRules();
}
