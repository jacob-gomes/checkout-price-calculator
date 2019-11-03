package com.idealo.assignment.b2c.cart.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.idealo.assignment.b2c.cart.service.CartService;
import com.idealo.assignment.b2c.model.Cart;

@Component
public class CartServiceCacheImpl implements CartService {
	
	private static Cart cart;
	
	@PostConstruct
	private void initiateCart() {
		cart = new Cart();
		cart.setGoodAndQuantityMap(new HashMap<>());
	}
	
	@Override
	public void addGoodToCart(String good) {
		int goodsQuantity = 1;
		Map<String, Integer> goodAndQuantityMap = cart.getGoodAndQuantityMap();
		
		if(goodAndQuantityMap.containsKey(good)) {
			goodsQuantity = goodAndQuantityMap.get(good) + 1;
		}
		
		goodAndQuantityMap.put(good,
				goodsQuantity);
	}

	@Override
	public Cart getCart(){
		return cart;
	}

	@Override
	public void refeshCart() {
		initiateCart();
		
	}

}
