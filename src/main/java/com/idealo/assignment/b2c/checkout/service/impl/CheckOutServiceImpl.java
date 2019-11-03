package com.idealo.assignment.b2c.checkout.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idealo.assignment.b2c.cart.service.CartService;
import com.idealo.assignment.b2c.checkout.service.CheckOutService;
import com.idealo.assignment.b2c.checkout.service.util.CheckoutServiceUtil;
import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.Cart;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.pricingrule.service.PricingRuleService;

@Component
public class CheckOutServiceImpl implements CheckOutService{

	private CartService cartService; 
	
	private PricingRuleService pricingRuleService;
	
	private CheckoutServiceUtil checkoutServiceUtil;
	
	@Autowired
	public CheckOutServiceImpl(CartService cartService, PricingRuleService pricingRuleService,
			CheckoutServiceUtil checkoutServiceUtil) {
		super();
		this.cartService = cartService;
		this.pricingRuleService = pricingRuleService;
		this.checkoutServiceUtil = checkoutServiceUtil;
	}

	@Override
	public void scan(String good) throws PricingRuleInconsistencyException {
		cartService.addGoodToCart(good);
		
		Cart cart = cartService.getCart();
		
		Map<String, PricingRule> mapOfGoodAndPricingRule = pricingRuleService.getCurrentPriceRuleState();
		
		checkoutServiceUtil.calculateCartTotalCost(cart, mapOfGoodAndPricingRule);		
		
	}
	
	@Override
	public int getTotalCartCost() {
		return cartService.getCart().getTotalCost();
	}

	@Override
	public void addNewPricingRules(List<PricingRule> listOfPricingRule) {
		pricingRuleService.addNewPricingRules(listOfPricingRule);
		
	}

	@Override
	public void refreshCart() {
		cartService.refeshCart();
	}

	@Override
	public void refreshRules() {
		pricingRuleService.refreshPriceRules();
	}

	
}
