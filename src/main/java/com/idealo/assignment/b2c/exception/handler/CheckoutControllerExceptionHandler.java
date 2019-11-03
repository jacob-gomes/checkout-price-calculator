package com.idealo.assignment.b2c.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idealo.assignment.b2c.exception.PricingRuleInconsistencyException;

@ControllerAdvice
public class CheckoutControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleGenericException(Exception ex) {
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(PricingRuleInconsistencyException.class)
    public final ResponseEntity<String> handlePricingRuleInconsistencyException(PricingRuleInconsistencyException ex) {
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}
