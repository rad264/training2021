package com.smbcgroup.training.atm;

import java.io.IOException;
import java.math.BigDecimal;
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
		if (!accountNumber.matches("^\\d{6}$"))
			throw new AccountNotFoundException("Invalid account number.", null);
		return dao.getAccount(accountNumber);
	}
	
	public void deposit(Account account, BigDecimal amount) throws AccountNotFoundException, IOException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IOException("Invalid amount.");
		}
		BigDecimal currentBalance = account.getBalance();
		currentBalance = currentBalance.add(amount);
		account.setBalance(currentBalance);
		dao.updateAccount(account);
	}
	
	public void updateAccount(Account account) throws AccountNotFoundException {
		dao.updateAccount(account);
	}

	public void withdraw(Account account, BigDecimal amount) throws IOException {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IOException("Invalid amount.");
		}
		BigDecimal currentBalance = account.getBalance();
		if (currentBalance.compareTo(amount) >= 0) {
			currentBalance = currentBalance.subtract(amount);
			account.setBalance(currentBalance);
			dao.updateAccount(account);
		} else {
			throw new IOException("Invalid amount.");
		}		
	}
	
	public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) throws IOException {
		BigDecimal fromBalance = fromAccount.getBalance();
		BigDecimal toBalance = toAccount.getBalance();
		
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IOException("Invalid amount.");
		}
		if (fromBalance.compareTo(amount) >= 0) {
			fromBalance = fromBalance.subtract(amount);
			fromAccount.setBalance(fromBalance);
			toBalance = toBalance.add(amount);
			toAccount.setBalance(toBalance);
			dao.updateAccount(fromAccount);
			dao.updateAccount(toAccount);
		} else {
			throw new IOException("Invalid amount.");
		}					
		
	}
	
	public Account createAccount(User user, String input) throws IOException {
		String accountNumber = "";
		
		if ("1".equals(input)) {
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
			accountNumber = String.format("%06d", number);
		} else {
			if (!input.matches("^\\d{6}$"))
				throw new IOException("Invalid account number.");
			for (String userAccount : user.getAccounts()) {
				if (userAccount.equals(input)) {
					throw new IOException("Invalid account number.");
				} else {
					accountNumber = input;
				}
			}
		}
		return dao.createAccount(user.getUserId(), accountNumber);
	}
}
