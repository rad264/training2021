package com.smbcgroup.training.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

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
		Account account =  dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		BigDecimal newBalance = currentBalance.add(amount);
		account.setBalance(newBalance);
		dao.updateAccount(account);
		//System.out.println("Deposit successful. New balance of account " + account.getAccountNumber() + ": " + account.getBalance());
		
	}
	
	public void withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException, Exception {
		Account account = dao.getAccount(accountNumber);
		account.setAccountNumber(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		if (amount.compareTo(currentBalance) < 0) { //if amount is < currentBalance
			BigDecimal newBalance = currentBalance.subtract(amount);
			account.setBalance(newBalance);
			dao.updateAccount(account);
			//System.out.println("Withdraw successful. New balance of account " + account.getAccountNumber() + ": " + account.getBalance());
		} else {
			throw new Exception("Amount to withdraw cannot be greater than current balance.");
		}
	}
	
	public void transfer(String accountNumber, String accountNumberToTransfer, BigDecimal amount) throws AccountNotFoundException, Exception {
		Account origAccount = dao.getAccount(accountNumber);
		Account transferAccount = dao.getAccount(accountNumberToTransfer);
		BigDecimal currentBalanceOrigAccount = origAccount.getBalance();
		if(amount.compareTo(currentBalanceOrigAccount) < 0) {
			withdraw(origAccount.getAccountNumber(), amount);
			dao.updateAccount(origAccount);
			deposit(transferAccount.getAccountNumber(), amount);
			dao.updateAccount(transferAccount);
		} else {
			throw new Exception("Amount to transfer cannot be greater than balance of initiating account.");
		}
	}

	public void openNewAccount(BigDecimal amount) {
		//for existing user
	}
	
	public void createUser(String userId) {
		User user = new User();
		user.setUserId(userId);
	}
	
	public Collection<Account> getUserAccounts(String userId) {
		Collection<Account> accounts = new ArrayList<Account>();
		
		return null;
	}
	
	public void addUserAccount(String userId, String account) {
		
	}
	
	
	
}
