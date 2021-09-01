package com.smbcgroup.training.atm;

import java.math.BigDecimal;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class ATMService {

	private AccountDAO dao;

	public ATMService(AccountDAO dao) {
		this.dao = dao;
	}

	public User getUser(String userId) throws UserNotFoundException {
		return dao.getUser(userId);
	}

	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		return dao.getAccount(accountNumber);
	}

	public void deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
		Account account = dao.getAccount(accountNumber);
		account.setAccountNumber(accountNumber);
		account.setBalance(account.getBalance().add(amount));
		dao.saveAccount(account);
	}
	
}
