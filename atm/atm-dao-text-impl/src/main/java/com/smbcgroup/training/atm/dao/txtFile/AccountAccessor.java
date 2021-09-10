package com.smbcgroup.training.atm.dao.txtFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

public class AccountAccessor {

	static String dataLocation = "../atm-dao-text-impl/data/";

	public static String[] getUserAccounts(String userId) throws IOException {
		return resourceToString(dataLocation + "users/" + userId).split(",");
	}

	public static BigDecimal getAccountBalance(String accountNumber) throws IOException {
		return new BigDecimal(resourceToString(dataLocation + "accounts/" + accountNumber));
	}

	public static void updateAccountBalance(String accountNumber, BigDecimal balance) throws IOException {
		writeStringToFile(dataLocation + "accounts/" + accountNumber, balance.toPlainString());
	}
	
	public static void updateAccountSummary(String userId, String accountNumber, BigDecimal balance) throws IOException {
		String message = "Account: " + accountNumber + "; balance: " + balance + "\n";
		Files.writeString(Path.of(dataLocation + "summary/" + userId + "-"+ accountNumber + "summary.txt"), message, StandardOpenOption.CREATE);
	}
	
	//add reading files here
	public static String readAccountSummary(String userId, String accountNumber) throws FileNotFoundException, IOException {
		File accountSummaryFile = new File(dataLocation + "summary/" + userId + "-" + accountNumber + "summary.txt");
		FileReader fileReader = new FileReader(accountSummaryFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lineFromFile = bufferedReader.readLine();
		fileReader.close();
		bufferedReader.close();
		
		return lineFromFile;
	}
	
	public static void updateTransactionHistory(String userId, String message) throws IOException {
		Files.writeString(Path.of(dataLocation + "transActionHistory/" + userId + "-transactionHistory.txt"), message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	}
	
	public static ArrayList<String> readTransactionHistory(String userId) throws FileNotFoundException, IOException {
		File transactionHistoryFile = new File(dataLocation + "transActionHistory/" + userId + "-transactionHistory.txt");
		FileReader fileReader = new FileReader(transactionHistoryFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> transactions = new ArrayList<>();
		while(bufferedReader.readLine() != null) {
			transactions.add(bufferedReader.readLine());
		}
		fileReader.close();
		bufferedReader.close();
		
		return transactions;
	}
	
	
	public static void createNewAccount(String accountNumber, String balance) throws IOException {
		File newAcctFile = new File(dataLocation + "accounts/" + accountNumber + ".txt");
		FileWriter fw = new FileWriter(newAcctFile, true);
		fw.write(balance);
		fw.close();
	}
	
	public static void addNewAccountToUserID(String userId, String accountNumber) throws IOException {
		FileWriter fw = new FileWriter(dataLocation + "users/" + userId + ".txt", true);
		fw.write("," + accountNumber);
		fw.close();
		
	}
	

	private static String resourceToString(String fileName) throws IOException {
		return Files.readString(Path.of(fileName + ".txt"));
	}

	private static void writeStringToFile(String fileName, String newContents) throws IOException {
		Files.writeString(Path.of(fileName + ".txt"), newContents);
	}
	
	

}
