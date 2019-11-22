package com.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.app.config.Config.currencyTypes;

@Entity
public class Trade extends IbSupport{
	
	@Id
	@GeneratedValue(generator = "trade_generator")
	@SequenceGenerator(
			name = "trade_generator",
			sequenceName = "trade_generator",
			initialValue = 1
			)
	private long tradeNo;

	private String customerName;

	@Enumerated(EnumType.STRING)
	private currencyTypes currencyPair;	

	private double amount;
	
	private double rate;
	
	private long userId;

	public long getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(long tradeNo) {
		this.tradeNo = tradeNo;
	}

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
