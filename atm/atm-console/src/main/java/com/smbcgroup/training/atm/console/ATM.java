package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;

import com.smbcgroup.training.atm.ATMService;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;
import com.smbcgroup.training.atm.dao.txtFile.AccountDAOTxtFileImpl;

public class ATM {
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit;
		// TODO: add more actions
	}
	
	private ATMService service = new ATMService(new AccountDAOTxtFileImpl());
	private BufferedReader inputReader;
	private PrintStream output;
	private String[] loggedInUserAccounts;
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
		case deposit:
			output.println("Enter amount:");
			return true;
		// TODO: prompts for other actions(?)
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
				loggedInUserAccounts = service.getUser(input).getAccounts();
				return Action.changeAccount;
			} catch (UserNotFoundException e) {
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
				BigDecimal balance = service.getAccount(selectedAccount).getBalance();
				output.println("Balance: $" + balance);
			} catch (AccountNotFoundException e) {
				throw new RuntimeException(e);
			}
			break;
		case deposit:
			try {
				service.deposit(selectedAccount, new BigDecimal(input));
				output.println("Deposit accepted");
			} catch (NumberFormatException e) {
				throw new ATMException("Invalid amount");
			} catch (AccountNotFoundException e) {
				throw new RuntimeException(e);
			}
			break;
		// TODO: handle other actions
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
