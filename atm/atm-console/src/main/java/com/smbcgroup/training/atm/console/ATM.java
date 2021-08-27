package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import com.smbcgroup.training.atm.dao.txtFile.AccountAccessor;

public class ATM {
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit, withdraw, transfer, newAccount, accountSummary, accountHistory;
		// TODO: add more actions
	}
	
	private BufferedReader inputReader;
	private PrintStream output;
	private String[] loggedInUserAccounts;
	private String loggedInUserId;
	private String selectedAccount;
	private Action selectedAction = Action.login;

	private ATM(InputStream input, PrintStream output) {
		this.inputReader = new BufferedReader(new InputStreamReader(input));
		this.output = output;
	}

	private void beginSession() throws IOException {
		try {
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

	private void triggerAction() throws IOException, SystemExit {
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
			output.println("Enter account number: (" + String.join(", ", loggedInUserAccounts) + ")");
			return true;
		// TODO: prompts for other actions(?)
		case deposit:
			output.println("Enter deposit amount:");
			return true;
		case withdraw:
			try {
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("Enter withdrawal amount: (current balance: $" + balance.toString() + ")");
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return true;
			}
		case transfer:
			String[] otherAccounts;
			otherAccounts = Arrays.stream(loggedInUserAccounts).filter(account -> !account.equals(selectedAccount)).toArray(String[]::new);
			if (otherAccounts.length >= 1) {
				output.println("Enter account number to transfer to: (" + String.join(", ", otherAccounts) + ")");
				return true;
			} else {
				output.println("You have no other account.");
				return false;
			}
		case newAccount:
			output.println("To randomly generate a new account number, press 1. Otherwise, enter a new 6 digit account number:");
			return true;
		default:
			return false;
		}
	}

	private Action performActionAndGetNextAction(String input) throws ATMException, SystemExit {
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
			try {
				loggedInUserAccounts = AccountAccessor.getUserAccounts(input);
				loggedInUserId = input;
				return Action.changeAccount;
			} catch (IOException e) {
				e.printStackTrace();
				throw new ATMException("Invalid user ID.");
			}
		case changeAccount:
			if (!input.matches("^\\d{6}$"))
				throw new ATMException("Invalid account number.");
			for (String userAccount : loggedInUserAccounts) {
				if (userAccount.equals(input)) {
					selectedAccount = input;
					return null;
				}
			}
			throw new ATMException("Account number not found.");
		case checkBalance:
			try {
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("Balance: $" + balance);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		// TODO: handle other actions
		case deposit:
			try {
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				try {
					BigDecimal depositAmount = new BigDecimal(input);
					if (depositAmount.compareTo(BigDecimal.ZERO) < 0) {
						throw new ATMException("Invalid deposit amount.");
					}
					balance = balance.add(depositAmount);
					
				} catch (NumberFormatException e) {
					throw new ATMException("Invalid deposit amount.");
				}
				AccountAccessor.updateAccountBalance(selectedAccount, balance);
				BigDecimal newBalance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("New Balance: $" + newBalance);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		case withdraw:
			try {
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				try {
					BigDecimal withdrawAmount = new BigDecimal(input);
					if (balance.compareTo(withdrawAmount) >= 0) {
						balance = balance.subtract(withdrawAmount);
					} else {
						throw new ATMException("Invalid withdrawal amount.");
					}					
				} catch (NumberFormatException e) {
					throw new ATMException("Invalid deposit amount.");
				}
				AccountAccessor.updateAccountBalance(selectedAccount, balance);
				BigDecimal newBalance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("New Balance: $" + newBalance);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		case transfer:
			if (!input.matches("^\\d{6}$"))
				throw new ATMException("Invalid account number.");
			for (String userAccount : loggedInUserAccounts) {
				if (userAccount.equals(input)) {
					selectedAccount = input;
					return null;
				}
			}
			throw new ATMException("Account number not found.");
		
		case newAccount:
			if ("1".equals(input)) {
				Random rnd = new Random();
				int number = rnd.nextInt(999999);
				selectedAccount = String.format("%06d", number);
			} else {
				if (!input.matches("^\\d{6}$"))
					throw new ATMException("Invalid account number.");
				for (String userAccount : loggedInUserAccounts) {
					if (userAccount.equals(input)) {
						throw new ATMException("Invalid account number.");
					} else {
						selectedAccount = input;
					}
				}
			}
			try {
				AccountAccessor.createNewAccount(selectedAccount, loggedInUserId);
				loggedInUserAccounts = AccountAccessor.getUserAccounts(loggedInUserId);
				output.println("Account created successfully! (" + String.join(", ", loggedInUserAccounts) + ")");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new ATMException("Could not create a new account. Try again.");
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
