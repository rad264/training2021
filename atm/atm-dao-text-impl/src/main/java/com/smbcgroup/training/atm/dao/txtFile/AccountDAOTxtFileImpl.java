package com.smbcgroup.training.atm.dao.txtFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImpl implements AccountDAO {
	//move accountaccessor stuff to here

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		try {
			User user = new User();
			user.setUserId(userId);
			user.setAccounts(AccountAccessor.getUserAccounts(userId));
			return user;
		} catch (IOException e) {
			throw new UserNotFoundException("User file not found", e);
		}
	}

	@Override
	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		try {
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(AccountAccessor.getAccountBalance(accountNumber));
			return account;
		} catch (IOException e) {
			throw new AccountNotFoundException("Account file not found", e);
		}
	}

	@Override
	public void updateAccount(Account account) throws AccountNotFoundException {
		//only for updating existing account
		try {
			AccountAccessor.updateAccountBalance(account.getAccountNumber(), account.getBalance());
		} catch (Exception e) {
			throw new AccountNotFoundException("Account file not found", e);
			
		}
	}
	
	@Override
	public void createAccount(String accountNumber, BigDecimal balance) throws Exception {
			try {
				AccountAccessor.createNewAccount(accountNumber, balance.toString());
			} catch (Exception e) {
				throw new Exception("Could not create account");
			}	
	}
	
	@Override
	public void linkAccountToUser(String userId, String accountNumber) throws UserNotFoundException {
		try {
			AccountAccessor.addNewAccountToUserID(userId, accountNumber);
		} catch (Exception e) {
			throw new UserNotFoundException("User file not found", e);
		}
	}
	
	
	@Override
	public void getAccountSummary(String userId, String accountNumber) throws Exception {
		Account account = getAccount(accountNumber);
		AccountAccessor.updateAccountSummary(userId, account.getAccountNumber(), account.getBalance());
		System.out.println(AccountAccessor.readAccountSummary(userId, account.getAccountNumber()));
	}

	@Override
	public void updateTransactionHistory(String userId, String message) throws Exception {
		AccountAccessor.updateTransactionHistory(userId, message);
		
	}

	@Override
	public void getTransactionHistory(String userId) throws Exception {
		ArrayList<String> transactions = AccountAccessor.readTransactionHistory(userId);
		for(int i = 0; i < transactions.size() ; i++) {
			System.out.println(transactions.get(i));
		}
		
	}

}
