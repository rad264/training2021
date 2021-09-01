package com.smbcgroup.training.atm.dao.txtFile;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImplTest {

	private static final String DATA_LOCATION = "../atm-dao-text-impl/test-data/";

	private AccountDAOTxtFileImpl dao = new AccountDAOTxtFileImpl();
	
	@BeforeClass
	public static void staticSetup() {
		AccountAccessor.dataLocation = DATA_LOCATION;
	}

	@Before
	public void setup() throws IOException {
		clearTestData();
		createFile("users/rdelaney.txt", "123456");
		createFile("accounts/123456.txt", "100.00");
	}
	
	private static void clearTestData() throws IOException {
		Path rootPath = Paths.get(DATA_LOCATION);
		try (Stream<Path> walk = Files.walk(rootPath)) {
		    walk.sorted(Comparator.reverseOrder())
		        .map(Path::toFile)
		        .forEach(File::delete);
		}
		Files.createDirectory(Path.of(DATA_LOCATION));
		Files.createDirectory(Path.of(DATA_LOCATION + "users"));
		Files.createDirectory(Path.of(DATA_LOCATION + "accounts"));
	}

	private static void createFile(String fileName, String contents) throws IOException {
		Files.writeString(Path.of(DATA_LOCATION + fileName), contents, StandardOpenOption.CREATE);
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetUser_NotFound() throws Exception {
		dao.getUser("dfgfdg");
	}

	@Test
	public void testGetUser() throws Exception {
		User user = dao.getUser("rdelaney");
		assertEquals("rdelaney", user.getUserId());
		assertArrayEquals(new String[] {"123456"}, user.getAccounts());
	}

	@Test(expected = AccountNotFoundException.class)
	public void testGetAccount_NotFound() throws Exception {
		dao.getAccount("111");
	}

	@Test
	public void testGetAccount() throws Exception {
		Account account = dao.getAccount("123456");
		assertEquals("123456", account.getAccountNumber());
		assertEquals(new BigDecimal("100.00"), account.getBalance());
	}

	@Test
	public void testSaveAccount_ExistingAccount() throws Exception {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setBalance(new BigDecimal("10.00"));
		dao.saveAccount(account);
		assertEquals("10.00", readFile("accounts/123456.txt"));
	}

	@Test
	public void testSaveAccount_NewAccount() throws Exception {
		Account account = new Account();
		account.setAccountNumber("555555");
		account.setBalance(new BigDecimal("200.00"));
		dao.saveAccount(account);
		assertEquals("200.00", readFile("accounts/555555.txt"));
	}

	private String readFile(String fileName) throws IOException {
		return Files.readString(Path.of(DATA_LOCATION + fileName));
	}

}
