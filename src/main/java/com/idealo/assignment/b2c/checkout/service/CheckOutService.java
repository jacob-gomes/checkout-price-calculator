package com.idealo.assignment.b2c.checkout.service;

import java.util.List;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.PricingRule;

public interface CheckOutService {

	void scan(String good) throws PricingRuleInconsistencyException;
	
	void addNewPricingRules(List<PricingRule> listOfPricingRule);

	int getTotalCartCost();

	void refreshCart();

	void refreshRules();
}
