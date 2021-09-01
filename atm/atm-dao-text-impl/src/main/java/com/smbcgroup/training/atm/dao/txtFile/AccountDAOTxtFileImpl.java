package com.smbcgroup.training.atm.dao.txtFile;

import java.io.IOException;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImpl implements AccountDAO {
	//move account accessor stuff to here

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

}
