package com.idealo.assignment.b2c.offer.model.impl;

import com.idealo.assignment.b2c.offer.model.Offer;

public class OfferImpl extends Offer {
		private Integer offerCount;

		private Integer offerPrice;

		/**
		 * @return the offerCount
		 */
		public Integer getOfferCount() {
			return offerCount;
		}

		/**
		 * @param offerCount the offerCount to set
		 */
		public void setOfferCount(Integer offerCount) {
			this.offerCount = offerCount;
		}

		/**
		 * @return the offerPrice
		 */
		public Integer getOfferPrice() {
			return offerPrice;
		}

		/**
		 * @param offerPrice the offerPrice to set
		 */
		public void setOfferPrice(Integer offerPrice) {
			this.offerPrice = offerPrice;
		}
}