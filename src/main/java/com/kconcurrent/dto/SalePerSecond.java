package com.kconcurrent.dto;

public class SalePerSecond {

	private Double totalSales;

	private Integer numberOfSales;

	private Double maxSale;

	private Double minSale;

	private Long previousTimeStamp;

	public SalePerSecond() {
		super();
		this.totalSales = 0.0;
		this.numberOfSales = 0;
		this.maxSale = 0.0;
		this.minSale = Double.MAX_VALUE;
		this.previousTimeStamp = 0L;
	}

	public SalePerSecond(Double totalSales, Integer numberOfSales,
			Double maxSale, Double minSale, Long previousTimeStamp) {
		super();
		this.totalSales = totalSales;
		this.numberOfSales = numberOfSales;
		this.maxSale = maxSale;
		this.minSale = minSale;
		this.previousTimeStamp = previousTimeStamp;
	}

	public Double getTotalSales() {
		return totalSales;
	}

	public Integer getNumberOfSales() {
		return numberOfSales;
	}

	public Double getMaxSale() {
		return maxSale;
	}

	public Double getMinSale() {
		return minSale;
	}

	public Long getPreviousTimeStamp() {
		return previousTimeStamp;
	}

		

}
