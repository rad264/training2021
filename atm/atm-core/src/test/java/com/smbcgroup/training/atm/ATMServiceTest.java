package com.smbcgroup.training.atm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class ATMServiceTest {

	private MockAccountDAO mockDAO = new MockAccountDAO();
	private ATMService service = new ATMService(mockDAO);

	@Test(expected = UserNotFoundException.class)
	public void testGetUser_AccountNumberDoesntExist() throws Exception {
		mockDAO.stub_getUser(new UserNotFoundException());
		service.getUser("rdelaney");
	}

	@Test
	public void testGetUser_Success() throws Exception {
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

	@Test(expected = AccountNotFoundException.class)
	public void testDeposit_AccountNumberDoesntExist() throws Exception {
		mockDAO.stub_getAccount(new AccountNotFoundException());
		service.deposit("123456", new BigDecimal("50.00"));
	}

	@Test
	public void testDeposit_Success() throws Exception {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setBalance(new BigDecimal("100.00"));
		mockDAO.stub_getAccount(account);
		service.deposit("123456", new BigDecimal("50.00"));
		Account accountAfterDeposit = mockDAO.spy_updateAccount().get(0);
		assertEquals(new BigDecimal("150.00"), accountAfterDeposit.getBalance());
	}

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

		private Account getAccount_value;
		private AccountNotFoundException getAccount_exception;

		@Override
		public Account getAccount(String accountNumber) throws AccountNotFoundException {
			if (getAccount_exception != null)
				throw getAccount_exception;
			return getAccount_value;
		}

		public void stub_getAccount(Account account) {
			getAccount_value = account;
		}

		public void stub_getAccount(AccountNotFoundException exception) {
			getAccount_exception = exception;
		}

		private List<Account> updateAccount_capture = new ArrayList<>();

		@Override
		public void saveAccount(Account account) {
			updateAccount_capture.add(account);
		}

		public List<Account> spy_updateAccount() {
			return updateAccount_capture;
		}

	}

}
