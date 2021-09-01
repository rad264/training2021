package com.smbcgroup.training.atm.dao.txtFile;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Test;

import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImplTest {

	private static final String DATA_LOCATION = "../atm-dao-test-impl/target/test-data/";
	private AccountDAOTxtFileImpl dao = new AccountDAOTxtFileImpl();
	
	@Before
	public void setup() throws IOException {
		AccountAccessor.dataLocation = DATA_LOCATION;
		Files.deleteIfExists(Path.of(DATA_LOCATION));
		Files.createDirectory(Path.of(DATA_LOCATION));
		Files.writeString(Path.of(DATA_LOCATION + "rdelaney.txt"), "100.00", StandardOpenOption.CREATE);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testUserNotFound() throws Exception {
		dao.getUser("dasdf");
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = dao.getUser("rdelaney");
		assertArrayEquals(new String[] {"123456"}, user.getAccounts());
	}
}
