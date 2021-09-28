package com.smbcgroup.training.atm.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.NonUniqueIdException;
import com.smbcgroup.training.atm.User;

public interface AccountDAO {

	User getUser(String userId) throws UserNotFoundException;

	Account getAccount(String accountNumber) throws AccountNotFoundException;

	void updateAccount(Account account) throws AccountNotFoundException;
	
	void createAccount(String userId, String accountNumber, BigDecimal balance) throws UserNotFoundException;
	
	//void linkAccountToUser(String userId, String accountNumber) throws UserNotFoundException;
	
	//void getAccountSummary(String userId) throws Exception;
	
	void updateTransactionHistory(String userId, String message) throws Exception;
	
	ArrayList<String> getTransactionHistory(String userId) throws UserNotFoundException;
	
	void createUser(String userId) throws NonUniqueIdException;
	

}
