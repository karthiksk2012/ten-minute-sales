package com.kconcurrent.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleData {

	@NotNull
	@JsonProperty
	private Double totalSale;

	@NotNull
	@JsonProperty
	private Double mean;

	@NotNull
	@JsonProperty
	private Double maxSale;

	@NotNull
	@JsonProperty
	private Double minSale;

	
	@NotNull
	@JsonProperty
	private Integer numberOfSales;

	public SaleData(@JsonProperty("totalSale") Double totalSale,
			@JsonProperty("mean") Double mean, 
			@JsonProperty("maxSale") Double maxSale,
			@JsonProperty("minSale") Double minSale,
			@JsonProperty("numberOfSales") Integer numberOfSales) {
		super();
		this.totalSale = totalSale;
		this.mean = mean;
		this.maxSale = maxSale;
		this.minSale = minSale;
		this.numberOfSales = numberOfSales;
	}

	/**
	 * @return the total Sale value
	 */
	@JsonProperty
	public Double getTotalSale() {
		return totalSale;
	}

	/**
	 * @return the average sale
	 */
	@JsonProperty
	public Double getMean() {
		return mean;
	}

	/**
	 * @return the max sale amount
	 */
	@JsonProperty
	public Double getMaxSale() {
		return maxSale;
	}

	/**
	 * @return the min sale amount
	 */
	@JsonProperty
	public Double getMinSale() {
		return minSale;
	}

	/**
	 * @return the number of sales
	 */
	@JsonProperty
	public Integer getNumberOfSales() {
		return numberOfSales;
	}

}
