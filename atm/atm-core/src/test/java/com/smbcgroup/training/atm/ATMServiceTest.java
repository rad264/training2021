package com.smbcgroup.training.atm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class ATMServiceTest {

	private MockAccountDAO mockDAO = new MockAccountDAO();
	private ATMService service = new ATMService(mockDAO);

	@Test(expected = UserNotFoundException.class)
	public void testgetUser_AccountNumberDoesntExist() throws Exception {
		mockDAO.stub_getUser(new UserNotFoundException());
		service.getUser("rdelaney");
	}

	@Test
	public void testgetUser_Success() throws Exception {
		User user = new User();
		user.setAccounts(new String[] { "123456" });
		mockDAO.stub_getUser(user);
		assertArrayEquals(new String[] { "123456" }, service.getUser("rdelaney").getAccounts());
	}

	@Test(expected = AccountNotFoundException.class)
	public void testGetBalance_AccountNumberDoesntExist() throws Exception {
		mockDAO.stub_getAccount(new AccountNotFoundException());
		service.getAccount("123456");
	}

	@Test
	public void testGetAccount_Success() throws Exception {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setBalance(new BigDecimal("100.00"));
		mockDAO.stub_getAccount(account);
		assertSame(account, service.getAccount("123456"));
	}
	
	@Test
	public void testDeposit() throws AccountNotFoundException, Exception {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setBalance(new BigDecimal("100.00"));
		mockDAO.stub_getAccount(account);
		service.deposit(account.getAccountNumber(), new BigDecimal("10.00"), "rdelaney");
		Account updatedAccount = mockDAO.spy_updateAccount().get(0);
		assertEquals(new BigDecimal("110.00"), updatedAccount.getBalance());
		
	}
	
	@Test
	public void testWithdraw() throws AccountNotFoundException, Exception {
		//test1 - case where amount <= balance
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setBalance(new BigDecimal("100.00"));
		mockDAO.stub_getAccount(account);
		service.withdraw(account.getAccountNumber(), new BigDecimal("10.00"), "rdelaney");
		Account updatedAccount = mockDAO.spy_updateAccount().get(0);
		assertEquals(new BigDecimal("90.00"), updatedAccount.getBalance());
		
		//test2 - case where amount > balance
		//Account account2 = new Account();
		//account2.setAccountNumber("123456");
		//account2.setBalance(new BigDecimal("100.00"));
		//mockDAO.stub_getAccount(account2);
		//service.withdraw(account.getAccountNumber(), new BigDecimal("110.00"));
		
	}
	
	@Test
	public void testTransfer() throws AccountNotFoundException, Exception {
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccountNumber("123456");
		account2.setAccountNumber("654321");
		account1.setBalance(new BigDecimal("200.00"));
		account2.setBalance(new BigDecimal("100.00"));
		mockDAO.stub_getAccount(account1);
		mockDAO.stub_getAccount(account2);
		service.transfer(account1.getAccountNumber(), account2.getAccountNumber(), new BigDecimal("25.00"), "rdelaney");
		Account debitAccount = mockDAO.spy_updateAccount().get(0);
		Account creditAccount = mockDAO.spy_updateAccount().get(1);
		assertEquals(new BigDecimal("175.00"), debitAccount.getBalance());
		assertEquals(new BigDecimal("125.00"), creditAccount.getBalance());
			
	}
	
	@Test
	public void testOpenNewAccount() throws Exception {
		User user = new User();
		user.setUserId("rdelaney");
		mockDAO.stub_getUser(user);
		service.openNewAccount("rdelaney", new BigDecimal("10.00"));
		Account newAccount = mockDAO.spy_updateAccount().get(0);
		assertEquals(new BigDecimal("10.00"), newAccount.getBalance());
		
	}
	
	//have to test exception?
	//still have to test accountSummary, getTransactionHistory
	

	private static class MockAccountDAO implements AccountDAO {

		private User getUser_value;
		private UserNotFoundException getUser_exception;

		@Override
		public User getUser(String userId) throws UserNotFoundException {
			if (getUser_exception != null)
				throw getUser_exception;
			return getUser_value;
		}

		public void stub_getUser(User user) {
			getUser_value = user;
		}

		public void stub_getUser(UserNotFoundException exception) {
			getUser_exception = exception;
		}

		private Queue<Account> getAccount_value = new LinkedList<>(); //try to make a queue
		private AccountNotFoundException getAccount_exception;

		@Override
		public Account getAccount(String accountNumber) throws AccountNotFoundException {
			if (getAccount_exception != null)
				throw getAccount_exception;
			return getAccount_value.poll();
		}

		public void stub_getAccount(Account account) {
			getAccount_value.add(account);
		}
	

		public void stub_getAccount(AccountNotFoundException exception) {
			getAccount_exception = exception;
		}

		private List<Account> updateAccount_capture = new ArrayList<>();

		@Override
		public void updateAccount(Account account) {
			updateAccount_capture.add(account);
		}

		public List<Account> spy_updateAccount() {
			return updateAccount_capture;
		}

		@Override
		public void createAccount(String accountNumber, BigDecimal balance) throws Exception {
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(balance);
			updateAccount_capture.add(account);
			
		}

		@Override
		public void linkAccountToUser(String userId, String accountNumber) throws UserNotFoundException {
			User user = new User();
			user.setUserId(userId);
			String[] accounts = {accountNumber};
			user.setAccounts(accounts);
			
		}

		@Override
		public void getAccountSummary(String userId, String accountNumber) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateTransactionHistory(String userId, String message) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void getTransactionHistory(String userId) throws Exception {
			// TODO Auto-generated method stub
			
		}



	}

}
