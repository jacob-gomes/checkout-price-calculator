package com.idealo.assignment.b2c.model;

import java.util.Map;

public class Cart {
	private Map<String, Integer> goodAndQuantityMap;
	
	private Integer totalCost;

	/**
	 * @return the goodAndQuantityMap
	 */
	public final Map<String, Integer> getGoodAndQuantityMap() {
		return goodAndQuantityMap;
	}

	/**
	 * @param goodAndQuantityMap the goodAndQuantityMap to set
	 */
	public final void setGoodAndQuantityMap(Map<String, Integer> goodAndQuantityMap) {
		this.goodAndQuantityMap = goodAndQuantityMap;
	}

	/**
	 * @return the totalCost
	 */
	public final Integer getTotalCost() {
		return totalCost == null ? 0 : totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public final void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
