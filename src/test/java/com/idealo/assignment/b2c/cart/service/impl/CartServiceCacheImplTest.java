package com.idealo.assignment.b2c.cart.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.idealo.assignment.b2c.model.Cart;

public class CartServiceCacheImplTest {
	
	private CartServiceCacheImpl cartServiceCacheImpl;
	
	@Before
	public void initiateCart() {
		cartServiceCacheImpl = new CartServiceCacheImpl();
		cartServiceCacheImpl.refeshCart();
	}
	
	@Test
	public void addGoodToCartTest() {
		Cart cart;
		
		cartServiceCacheImpl.addGoodToCart("A");
		
		cart = cartServiceCacheImpl.getCart();
		Map<String, Integer> goodAndQuantityMap = cart.getGoodAndQuantityMap();
		
		assertNotNull(goodAndQuantityMap.get("A"));
		assertEquals(1,(int)goodAndQuantityMap.get("A"));
	}
	
	@Test
	public void addMultipleGoodsToCartTest() {
		Cart cart;
		
		cartServiceCacheImpl.addGoodToCart("A");
		cartServiceCacheImpl.addGoodToCart("B");
		cartServiceCacheImpl.addGoodToCart("A");
		
		cart = cartServiceCacheImpl.getCart();
		Map<String, Integer> goodAndQuantityMap = cart.getGoodAndQuantityMap();
		
		assertNotNull(goodAndQuantityMap.get("A"));
		assertNotNull(goodAndQuantityMap.get("B"));
		assertEquals(2,(int)goodAndQuantityMap.get("A"));
		assertEquals(1,(int)goodAndQuantityMap.get("B"));
	}
}
