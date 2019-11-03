package com.idealo.assignment.b2c.cart.service;

import com.idealo.assignment.b2c.model.Cart;

public interface CartService {
	void addGoodToCart(String good);
	
	Cart getCart();
	
	void refeshCart();
}
