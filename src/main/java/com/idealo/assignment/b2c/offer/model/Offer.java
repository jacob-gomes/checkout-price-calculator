package com.idealo.assignment.b2c.offer.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.idealo.assignment.b2c.offer.model.impl.OfferImpl;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "offerType")
@JsonSubTypes({
	@Type(value = OfferImpl.class, name = "OfferImpl")})
public abstract class Offer {
	public String offerType;
}
