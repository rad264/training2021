package com.smbcgroup.training.atm;

import java.io.IOException;
import java.math.BigDecimal;

public class Transaction {

	private long transactionId;
	private BigDecimal amount;
	private String createdTime;
	private String type;
	private String description;
	private String accountNumber;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long id) {
		this.transactionId = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
