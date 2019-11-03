package com.idealo.assignment.b2c.model;

import com.idealo.assignment.b2c.offer.model.Offer;

public class PricingRule{
	private String good;
	
	private Integer price;
	
	private Offer offer;
	

	/**
	 * @return the good
	 */
	public String getGood() {
		return good;
	}

	/**
	 * @param good the good to set
	 */
	public void setGood(String good) {
		this.good = good;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
}

