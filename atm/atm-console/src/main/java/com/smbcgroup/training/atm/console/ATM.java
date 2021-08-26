package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;

import com.smbcgroup.training.atm.dao.txtFile.AccountAccessor;

public class ATM {
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit, withdraw;
		// TODO: add more actions
		//added: deposit, withdraw
	}
	
	private BufferedReader inputReader;
	private PrintStream output;
	private String[] loggedInUserAccounts;
	private String selectedAccount;
	private Action selectedAction = Action.login; //selectedAction is originally = login

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
		//case for deposit
		case deposit:
			output.println("Enter amount to deposit");
			return true;
		//case for withdraw
		case withdraw:
			output.println("Enter amount to withdraw");
			return true;
		default: //case of checkBalance
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
		//case for deposit
		case deposit:
			try {
				//convert input (a string of amount to deposit) into BigDecimal
				//do this by using BigDecimal(String) constructor
				BigDecimal depositAmount = new BigDecimal(input);
		
				//get original balance before deposit is made
				BigDecimal origBalance = AccountAccessor.getAccountBalance(selectedAccount);
				
				//get new balance
				BigDecimal newBalance = depositAmount.add(origBalance);
				
				//update the account balance
				AccountAccessor.updateAccountBalance(selectedAccount, newBalance);
				
				//print message saying deposit successful
				output.println("Deposit successful. Updated balance: $" + newBalance);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ATMException("Invalid amount to deposit.");
			}
			break;
		//case for withdraw
		case withdraw:
			try {
				BigDecimal withdrawAmount = new BigDecimal(input);
				BigDecimal origBalance = AccountAccessor.getAccountBalance(selectedAccount);
				BigDecimal newBalance = origBalance.subtract(withdrawAmount);
				AccountAccessor.updateAccountBalance(selectedAccount, newBalance);
				output.println("Withdraw successful. Updated balance: $" + newBalance);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ATMException("Invalid amount to withdraw.");
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
