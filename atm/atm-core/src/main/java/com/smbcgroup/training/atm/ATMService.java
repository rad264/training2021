package com.smbcgroup.training.atm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.TransactionNotFoundException;
import com.smbcgroup.training.atm.dao.UserAlreadyExistException;
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
		if (!accountNumber.matches("^\\d{6}$"))
			throw new AccountNotFoundException("Invalid account number.", null);
		return dao.getAccount(accountNumber);
	}
	
	public void deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Invalid amount.");
		}
		Account account = dao.getAccount(accountNumber);
		account.setAccountNumber(accountNumber);
		account.setBalance(account.getBalance().add(amount));
		dao.updateAccount(account);
	}
	
	public void withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Invalid amount.");
		}
		Account account = dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		if (currentBalance.compareTo(amount) >= 0) {
			currentBalance = currentBalance.subtract(amount);
			account.setBalance(currentBalance);
			dao.updateAccount(account);
		} else {
			throw new IllegalArgumentException("Invalid amount.");
		}		
	}
	
	public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws AccountNotFoundException {
		Account fromAccount = dao.getAccount(fromAccountNumber);
		Account toAccount = dao.getAccount(toAccountNumber);
		BigDecimal fromBalance = fromAccount.getBalance();
		BigDecimal toBalance = toAccount.getBalance();
		
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Invalid amount.");
		}
		if (fromBalance.compareTo(amount) >= 0) {
			fromBalance = fromBalance.subtract(amount);
			fromAccount.setBalance(fromBalance);
			toBalance = toBalance.add(amount);
			toAccount.setBalance(toBalance);
			dao.updateAccount(fromAccount);
			dao.updateAccount(toAccount);
		} else {
			throw new IllegalArgumentException("Invalid amount.");
		}					
		
	}
	
	public Account createAccount(String userId, String input) throws UserNotFoundException {
		String accountNumber = "";
		User user = dao.getUser(userId);
		
		if ("1".equals(input)) {
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
			accountNumber = String.format("%06d", number);
		} else {
			if (!input.matches("^\\d{6}$"))
				throw new IllegalArgumentException("Invalid account number.");
			if (user.getAccounts() != null) {
				for (String userAccount : user.getAccounts()) {
					if (userAccount.equals(input)) {
						throw new IllegalArgumentException("Invalid account number.");
					} else {
						accountNumber = input;
					}
				}
			} else {
				accountNumber = input;
			}
		}
		return dao.createAccount(user.getUserId(), accountNumber);
	}
	
	public User createUser(String userId) throws UserAlreadyExistException {
		return dao.createUser(userId);
	}
	
	public void createTransaction(String accountNumber, BigDecimal amount, String type, String description) throws AccountNotFoundException {
		dao.createTransaction(accountNumber, amount, type, description);
	}
	
	public Transaction getTransaction(String transactionId) throws TransactionNotFoundException {
		long id = Long.valueOf(transactionId);
		return dao.getTransaction(id);
	}
	
	public Transaction[] getAllTransactionsByAccount(String accountNumber) throws AccountNotFoundException {
		return dao.getAllTransactionsByAccount(accountNumber);
	}
}
