package com.smbcgroup.training.atm.dao;

import java.math.BigDecimal;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;

public interface AccountDAO {

	User getUser(String userId) throws UserNotFoundException;

	Account getAccount(String accountNumber) throws AccountNotFoundException;

	void updateAccount(Account account) throws AccountNotFoundException;
	
	void createAccount(String accountNumber, BigDecimal balance) throws Exception;
	
	void linkAccountToUser(String userId, String accountNumber) throws UserNotFoundException;
	
	void getAccountSummary(String userId, String accountNumber) throws Exception;
	
	void updateTransactionHistory(String userId, String message) throws Exception;
	
	void getTransactionHistory(String userId) throws Exception;
	

}
