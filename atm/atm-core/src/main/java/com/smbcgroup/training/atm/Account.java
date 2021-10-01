package com.smbcgroup.training.atm;

import java.io.IOException;
import java.math.BigDecimal;

public class Account {

	private String accountNumber;
	private BigDecimal balance;
	private long[] transactions;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public void addBalance(BigDecimal amount) throws IOException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IOException("Invalid amount.");
		}
		this.balance.add(amount);
	}

	public void setTransactions(long[] transactions) {
		this.transactions = transactions;
	}
	
	public long[] getTransactions() {
		return transactions;
	}
}
