package com.smbcgroup.training.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

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
	
	public void deposit(String accountNumber, BigDecimal amount, String userId) throws AccountNotFoundException, Exception {
		Account account =  dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		BigDecimal newBalance = currentBalance.add(amount);
		account.setBalance(newBalance);
		dao.updateAccount(account);
		String message = "Deposited $" + amount + " in account " + account.getAccountNumber() + "\n";
		dao.updateTransactionHistory(userId, message);
		
	}
	
	public void withdraw(String accountNumber, BigDecimal amount, String userId) throws AccountNotFoundException, Exception {
		Account account = dao.getAccount(accountNumber);
		account.setAccountNumber(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		if (amount.compareTo(currentBalance) <= 0) {
			BigDecimal newBalance = currentBalance.subtract(amount);
			account.setBalance(newBalance);
			dao.updateAccount(account);
			String message = "Withdrew $" + amount + " from account " + account.getAccountNumber() + "\n";
			dao.updateTransactionHistory(userId, message);
		} else {
			throw new Exception("Amount to withdraw cannot be greater than current balance.");
		}
	}
	
	public void transfer(String accountNumber, String accountNumberToTransfer, BigDecimal amount, String userId) throws Exception {
		try {
			withdraw(accountNumber, amount, userId);
			deposit(accountNumberToTransfer, amount, userId);
			String message = "Transferred $" + amount + " from account " + accountNumber + " to " + accountNumberToTransfer + "\n";
			dao.updateTransactionHistory(userId, message);
		} catch (Exception e) {
			throw new Exception("Account to transfer cannot be greater than debiting account balance.", e);
		}
		
	}

	public void openNewAccount(String userId, BigDecimal amount) throws Exception {
		//for existing user
		Random randomAccountGenerator = new Random();
		int newAcctNumber = randomAccountGenerator.nextInt(999999);
		dao.createAccount(String.valueOf(newAcctNumber), amount);
		dao.linkAccountToUser(userId, String.valueOf(newAcctNumber));
		Account newAccount = dao.getAccount(String.valueOf(newAcctNumber));
		
	}
	
	
	public void accountSummary(String userId, String accountNumber) throws UserNotFoundException {
		try {
			dao.getAccountSummary(userId, accountNumber);
		
		} catch (Exception e) {
			throw new UserNotFoundException("File not found.", e);
		}

	}
	
	public void getTransactionHistory(String userId) throws UserNotFoundException, Exception {
		dao.getTransactionHistory(userId);
	}
	
	
	
}
