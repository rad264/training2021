package com.smbcgroup.training.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;
import com.smbcgroup.training.atm.OverdraftException;



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
	
	public void deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException  {
		Account account =  dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		BigDecimal newBalance = currentBalance.add(amount);
		account.setBalance(newBalance);
		dao.updateAccount(account);
		String message = "Deposited $" + amount + " in account " + account.getAccountNumber() + "\n";
		//dao.updateTransactionHistory(userId, message);
		
	}
	
	public void withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException, OverdraftException {
		Account account = dao.getAccount(accountNumber); 
		account.setAccountNumber(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		if (amount.compareTo(currentBalance) <= 0) {
			BigDecimal newBalance = currentBalance.subtract(amount);
			account.setBalance(newBalance);
			dao.updateAccount(account);
			String message = "Withdrew $" + amount + " from account " + account.getAccountNumber() + "\n";
			//dao.updateTransactionHistory(userId, message);
		} else {
			throw new OverdraftException("Amount to withdraw cannot be greater than current balance.");
		}
	}
	
	public void transfer(String accountNumber, String accountNumberToTransfer, BigDecimal amount) throws AccountNotFoundException, OverdraftException {
		withdraw(accountNumber, amount);
		deposit(accountNumberToTransfer, amount);
		String message = "Transferred $" + amount + " from account " + accountNumber + " to " + accountNumberToTransfer + "\n";
		//dao.updateTransactionHistory(userId, message);
			
	}

	public void openNewAccount(String userId, BigDecimal amount) throws UserNotFoundException {
		//for existing user
		Random randomAccountGenerator = new Random();
		int newAcctNumber = randomAccountGenerator.nextInt(999999);
		dao.createAccount(userId, String.valueOf(newAcctNumber), amount);
		//dao.linkAccountToUser(userId, String.valueOf(newAcctNumber));
		//Account newAccount = dao.getAccount(String.valueOf(newAcctNumber));
		
	}
	
	
	public ArrayList<String> accountSummary(String userId) throws UserNotFoundException {
		try {
			User user = dao.getUser(userId);
			String[] accounts = user.getAccounts();
			ArrayList<String> summary = new ArrayList<>();
			for(int i = 0; i < accounts.length; i++) {
				Account account = dao.getAccount(accounts[i]);
				summary.add("Account number: " + account.getAccountNumber() + "; balance: $" + account.getBalance());
			}
			return summary;
		
		} catch (Exception e) {
			throw new UserNotFoundException("User not found.", e);
		}

	}
	
	public void createNewUser(String newUserId) throws NonUniqueIdException {
		dao.createUser(newUserId);
		
		
	}
	
	public ArrayList<String> getTransactionHistory(String userId) throws UserNotFoundException {
		return dao.getTransactionHistory(userId);
	}
	
	
	
}
