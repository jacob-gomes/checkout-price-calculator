package com.idealo.assignment.b2c.checkout.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.idealo.assignment.b2c.cart.service.CartService;
import com.idealo.assignment.b2c.checkout.service.util.CheckoutServiceUtil;
import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.Cart;
import com.idealo.assignment.b2c.model.PricingRule;
import com.idealo.assignment.b2c.pricingrule.service.PricingRuleService;

@RunWith(MockitoJUnitRunner.class)
public class CheckOutServiceImplTest {
	
	private CheckOutServiceImpl checkOutServiceImpl;	

	@Mock
	private CartService cartService; 
	
	@Mock
	private PricingRuleService pricingRuleService;
	
	@Mock
	private CheckoutServiceUtil checkoutServiceUtil;
	
	
	@Before
	public void init() {
		checkOutServiceImpl = new CheckOutServiceImpl(cartService, 
				pricingRuleService,
				checkoutServiceUtil);
	}
	
	@Test
	public void scanTest() throws PricingRuleInconsistencyException {
		Cart cart = new Cart();

		Map<String, PricingRule>  map = new HashMap<>();
		
		Mockito.when(cartService.getCart()).thenReturn(cart);
		
		Mockito.when(pricingRuleService.getCurrentPriceRuleState()).thenReturn(map);
		
		Mockito.doAnswer(new Answer<Void>() { 
				@Override
		        public Void answer(final InvocationOnMock invocation) throws Throwable {
					Cart cartTest = (Cart)invocation.getArgument(0);
					Map<String, PricingRule> mapTest = (Map)invocation.getArgument(1);
					
					assertEquals(cartTest, cart);
					assertEquals(mapTest, map);
					return null;
				}
		}).when(checkoutServiceUtil).calculateCartTotalCost(Mockito.any(Cart.class), Mockito.any(Map.class));
		
		checkOutServiceImpl.scan("A");
	}
	
}
