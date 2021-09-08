package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import com.smbcgroup.training.atm.dao.jpa.AccountJPAImpl;
import com.smbcgroup.training.atm.dao.txtFile.AccountDAOTxtFileImpl;
import com.smbcgroup.training.atm.ATMService;
import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.UserNotFoundException;


public class ATM {
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit, withdraw, transfer, newAccount, accountSummary, accountHistory, transferAmount;
		// TODO: add more actions
	}
	
	private BufferedReader inputReader;
	private PrintStream output;
	private Account selectedAccount;
	private Account transferAccount;
	private Action selectedAction = Action.login;
	private User loggedInUser;
	private AccountJPAImpl dao = new AccountJPAImpl();
//	private AccountDAOTxtFileImpl dao = new AccountDAOTxtFileImpl();
	private ATMService atmService;

	private ATM(InputStream input, PrintStream output) {
		this.inputReader = new BufferedReader(new InputStreamReader(input));
		this.output = output;
	}

	private void beginSession() throws IOException {
		try {
			atmService = new ATMService(dao);
			output.println("Welcome!");
			while (true)
				triggerAction();
		} catch (SystemExit e) {

		} catch (Exception e) {
			output.println("An unexpected error occurred.");
			e.printStackTrace();
		} finally {
			output.println("Goodbye!");
			inputReader.close();
		}
	}

	private void triggerAction() throws IOException, SystemExit, AccountNotFoundException, UserNotFoundException {
		try {
			String input = null;
			if (promptUserInput())
				input = inputReader.readLine();
			selectedAction = performActionAndGetNextAction(input);
		} catch (ATMException e) {
			output.println(e.getMessage());
		}
	}

	private boolean promptUserInput() {
		if (selectedAction == null) {
			output.println("What would you like to do?");
			return true;
		}
		switch (selectedAction) {
		case login:
			output.println("Enter user ID:");
			return true;
		case changeAccount:
			output.println("Enter account number: (" + String.join(", ", loggedInUser.getAccounts()) + ")");
			return true;
		// TODO: prompts for other actions(?)
		case deposit:
			output.println("Enter deposit amount:");
			return true;
		case withdraw:
			BigDecimal balance = selectedAccount.getBalance();
			output.println("Enter withdrawal amount: (current balance: $" + balance.toString() + ")");
			return true;
		case transfer:
			String[] otherAccounts;
			otherAccounts = Arrays.stream(loggedInUser.getAccounts()).filter(account -> !account.equals(selectedAccount.getAccountNumber())).toArray(String[]::new);
			if (otherAccounts.length >= 1) {
				output.println("Enter account number to transfer to: (" + String.join(", ", otherAccounts) + ")");
				return true;
			} else {
				output.println("You have no other account.");
				return True;
			}
		case transferAmount:
			BigDecimal currentBalance = selectedAccount.getBalance();
			output.println("Enter transfer amount: (current balance: $" + currentBalance.toString() + ")");
			return true;
			
		case newAccount:
			output.println("To randomly generate a new account number, press 1. Otherwise, enter a new 6 digit account number:");
			return true;
		default:
			return false;
		}
	}

	private Action performActionAndGetNextAction(String input) throws ATMException, SystemExit, UserNotFoundException, AccountNotFoundException {
		if ("exit".equals(input))
			throw new SystemExit();
		if (selectedAction == null) {
			try {
				return Action.valueOf(input);
			} catch (IllegalArgumentException e) {
				throw new ATMException("Invalid command.");
			}
		}
		switch (selectedAction) {
		case login:
			loggedInUser = atmService.getUser(input);
			return Action.changeAccount;
				
		case changeAccount:
			for (String userAccount : loggedInUser.getAccounts()) {
				if (userAccount.equals(input)) {
					try {						
						selectedAccount = atmService.getAccount(input);
					} catch (Exception e) {
						throw new ATMException(e.getMessage());
					}
					return null;
				}
			}
			throw new ATMException("Account number not found.");
			
		case checkBalance:
			BigDecimal balance = selectedAccount.getBalance();
			output.println("Balance: $" + balance);
			break;
		// TODO: handle other actions
		case deposit:			
			try {
				BigDecimal depositAmount = new BigDecimal(input);
				atmService.deposit(selectedAccount, depositAmount);
				BigDecimal newBalance = selectedAccount.getBalance();
				output.println("New Balance: $" + newBalance);
			} catch (NumberFormatException e) {
				throw new ATMException("Invalid amount.");
			} catch (IllegalArgumentException e) {
				throw new ATMException(e.getMessage());
			}
			break;
			
		case withdraw:
			try {
				BigDecimal withdrawAmount = new BigDecimal(input);
				atmService.withdraw(selectedAccount, withdrawAmount);
				BigDecimal newBalance = selectedAccount.getBalance();
				output.println("New Balance: $" + newBalance);
			} catch (NumberFormatException e) {
				throw new ATMException("Invalid amount.");
			} catch (IllegalArgumentException e) {
				throw new ATMException(e.getMessage());
			}
			break;
			
		case transfer:
			for (String userAccount : loggedInUser.getAccounts()) {
				if (userAccount.equals(input)) {
					transferAccount = atmService.getAccount(input);
					return Action.transferAmount;
				}
			}
			throw new ATMException("Account number not found.");
			
		case transferAmount:
			try {
				BigDecimal transferAmount = new BigDecimal(input);
				atmService.transfer(selectedAccount, transferAccount, transferAmount);
				output.println("Account #" + selectedAccount.getAccountNumber() + " Balance: $" + selectedAccount.getBalance());
				output.println("Account #" + transferAccount.getAccountNumber() + " Balance: $" + transferAccount.getBalance());
			} catch (NumberFormatException e) {
				throw new ATMException("Invalid amount.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new ATMException("Invalid amount.");
			}
			break;
		
		case newAccount:
			try {
				selectedAccount = atmService.createAccount(loggedInUser, input);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ATMException("Invalid amount.");
			}
			break;
		}
		return null;
	}

	private static class SystemExit extends Throwable {
		private static final long serialVersionUID = 1L;
	}

	private static class ATMException extends Exception {
		private static final long serialVersionUID = 1L;

		public ATMException(String message) {
			super(message);
		}
	}

}
