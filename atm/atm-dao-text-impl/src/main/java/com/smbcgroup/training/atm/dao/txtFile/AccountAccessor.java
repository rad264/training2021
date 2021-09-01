package com.smbcgroup.training.atm.dao.txtFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class AccountAccessor {

	static String dataLocation = "../atm-dao-text-impl/data/";

	public static String[] getUserAccounts(String userId) throws IOException {
		return resourceToString(dataLocation + "users/" + userId).split(",");
	}

	public static BigDecimal getAccountBalance(String accountNumber) throws IOException {
		return new BigDecimal(resourceToString(dataLocation + "accounts/" + accountNumber));
	}

	public static void updateAccountBalance(String accountNumber, BigDecimal balance) throws IOException {
		writeStringToFile(dataLocation + "accounts/" + accountNumber, balance != null ? balance.toPlainString() : "0");
	}

	private static String resourceToString(String fileName) throws IOException {
		return Files.readString(Path.of(fileName + ".txt"));
	}

	private static void writeStringToFile(String fileName, String newContents) throws IOException {
		Files.writeString(Path.of(fileName + ".txt"), newContents);
	}

}
