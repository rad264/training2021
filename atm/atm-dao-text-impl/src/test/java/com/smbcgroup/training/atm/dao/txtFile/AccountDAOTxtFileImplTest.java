//package com.smbcgroup.training.atm.dao.txtFile;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.smbcgroup.training.atm.Account;
//import com.smbcgroup.training.atm.dao.AccountNotFoundException;
//import com.smbcgroup.training.atm.dao.UserNotFoundException;
//
//public class AccountDAOTxtFileImplTest {
//
//	private static final String DATA_LOCATION = "../atm-dao-text-impl/test-data/";
//	
//	private AccountDAOTxtFileImpl dao = new AccountDAOTxtFileImpl();
//	
//	@Before
//	public void setup() throws IOException {
//		AccountAccessor.dataLocation = DATA_LOCATION;
//		Files.writeString(Path.of(DATA_LOCATION + "users/rdelaney.txt"), "123456", StandardOpenOption.CREATE);
//		Files.writeString(Path.of(DATA_LOCATION + "accounts/123456.txt"), "100.00", StandardOpenOption.CREATE);
//	}
//	
//	@Test (expected = UserNotFoundException.class)
//	public void testUserNotFound() throws Exception {
//		dao.getUser("asdfghjkl");
//	}
//	
//	@Test
//	public void testgetUser() throws Exception {
//		dao.getUser("rdelaney");
//	}
//	
//	@Test (expected = AccountNotFoundException.class)
//	public void testAccountNotFound() throws Exception {
//		dao.getAccount("asdfghjkl");
//	}
//	
//	@Test
//	public void testGetAccount() throws AccountNotFoundException {
//		dao.getAccount("123456");
//	}
//	
//	@Test 
//	public void testUpdateAccount() throws AccountNotFoundException {
//		Account account = dao.getAccount("123456");
//		account.updateBalance(new BigDecimal("200.00"));
//		account.getBalance();
//		dao.updateAccount(account);
//		assertEquals(new BigDecimal("200.00"), account.getBalance());
//		
//	}
//	
//	//still must test createAccount, linkAccountToUser, getAccountSummary
//	//updateTransactionHistory and getTransactionHistory
//	
//	
//	
//
//}
