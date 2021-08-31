package com.smbcgroup.training.atm.dao.txtFile;

import java.io.IOException;
import java.math.BigDecimal;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImpl implements AccountDAO {
	

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		String[] userAccounts;
		User user = new User();
		try {
			userAccounts = AccountAccessor.getUserAccounts(userId);
			user.setAccounts(userAccounts);
			user.setUserId(userId);
			return user;
		} catch (IOException e) {
			e.printStackTrace();
			throw new UserNotFoundException();
		}
	}

	@Override
	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account account = new Account();
		
		try {
			BigDecimal balance = AccountAccessor.getAccountBalance(accountNumber);
			account.setBalance(balance);
			account.setAccountNumber(accountNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		try {
			AccountAccessor.updateAccountBalance(account.getAccountNumber(), account.getBalance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Account createAccount(String userId, String accountNumber) {
		Account newAccount = new Account();
		
		try {
			AccountAccessor.createNewAccount(accountNumber, userId);
			newAccount.setAccountNumber(accountNumber);
			newAccount.setBalance(BigDecimal.ZERO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newAccount;
	}
}
