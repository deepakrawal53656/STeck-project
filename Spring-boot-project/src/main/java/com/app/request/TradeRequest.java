package com.app.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.app.config.Config.currencyTypes;

public class TradeRequest {
	
	@NotNull(message="Customer Name cannot be empty")
	@NotEmpty(message="Customer Name cannot be empty")
	private String customerName;

	@Enumerated(EnumType.STRING)
	private currencyTypes currencyPair;	

	@Min(value = 1, message = "Amound must be greater than or equal to 1")
	private double amount;
	
	@Min(value = 1, message = "Rate must be greater than or equal to 1")
	private double rate;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public currencyTypes getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(currencyTypes currencyPair) {
		this.currencyPair = currencyPair;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
}
