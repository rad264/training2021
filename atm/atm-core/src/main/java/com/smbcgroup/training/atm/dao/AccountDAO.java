package com.smbcgroup.training.atm.dao;

import java.math.BigDecimal;
import java.util.List;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.Transaction;
import com.smbcgroup.training.atm.User;

public interface AccountDAO {

	User getUser(String userId) throws UserNotFoundException;

	Account getAccount(String accountNumber) throws AccountNotFoundException;

	void updateAccount(Account account);
	
	Account createAccount(String userId, String accountNumber) throws UserNotFoundException;

	User createUser(String userId) throws UserAlreadyExistException;

	void createTransaction(String accountNumber, BigDecimal amount, String type, String description)
			throws AccountNotFoundException;

	Transaction getTransaction(long id) throws TransactionNotFoundException;

	Transaction[] getAllTransactionsByAccount(String accountNumber) throws AccountNotFoundException;

}
