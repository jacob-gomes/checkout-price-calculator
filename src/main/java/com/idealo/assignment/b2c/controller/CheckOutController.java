package com.idealo.assignment.b2c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idealo.assignment.b2c.checkout.service.CheckOutService;
import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;
import com.idealo.assignment.b2c.model.PricingRule;

@RestController("/checkout")
public class CheckOutController {
	
	private CheckOutService checkOutService;

	@Autowired
	public CheckOutController(CheckOutService checkOutService) {
		super();
		this.checkOutService = checkOutService;
	}
	
	@PostMapping("/cart")
	public ResponseEntity<?> addNewGoodToTheCart(@PathVariable String good) throws PricingRuleInconsistencyException{
		checkOutService.scan(good);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/pricing-rule")
	public ResponseEntity<?> addNewPricingRules(@RequestBody List<PricingRule> listOfPricingRule){
		checkOutService.addNewPricingRules(listOfPricingRule);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<Integer> getTotalCartCost(){
		Integer totalCartCost = checkOutService.getTotalCartCost();
		return new ResponseEntity<>(totalCartCost, HttpStatus.OK);
	}
	
	@DeleteMapping("/refresh-cart")
	public ResponseEntity<Integer> refreshCart(){
		checkOutService.refreshCart();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/refresh-rule")
	public ResponseEntity<Integer> refreshRules(){
		checkOutService.refreshRules();
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
}
