package com.kconcurrent.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleRequest {

	@NotNull
	@JsonProperty
	private Double amount;

	/*
	 * Time in milliseconds
	 */
	@NotNull
	@JsonProperty
	private Long timestamp;

	public SaleRequest(@JsonProperty("amount") Double amount,
			@JsonProperty("time") Long time) {
		this.amount = amount;
		this.timestamp = time;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return timestamp;
	}

}
