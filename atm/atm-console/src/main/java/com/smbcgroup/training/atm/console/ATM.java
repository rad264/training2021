package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.RestClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;

public class ATM {
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit;
		// TODO: add more actions
	}

	private static final String API_ROOT_URL = "http://localhost:8080/atm-api/";

	private RestClient restClient;
	private BufferedReader inputReader;
	private PrintStream output;
	private String[] loggedInUserAccounts;
	private String selectedAccount;
	private Action selectedAction = Action.login;

	private ATM(InputStream input, PrintStream output) {
		Application restClientApp = new Application() {
			@Override
			public Set<Object> getSingletons() {
				HashSet<Object> set = new HashSet<Object>();
				set.add(new JacksonJsonProvider());
				return set;
			}
		};
		this.restClient = new RestClient(new ClientConfig().applications(restClientApp));
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
				User user = restClient.resource(API_ROOT_URL + "users/" + input).accept(MediaType.APPLICATION_JSON_TYPE)
						.get(User.class);
				loggedInUserAccounts = user.getAccounts();
				return Action.changeAccount;
			} catch (ClientWebException e) {
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
				Account account = restClient.resource(API_ROOT_URL + "accounts/" + selectedAccount)
						.accept(MediaType.APPLICATION_JSON_TYPE).get(Account.class);
				output.println("Balance: $" + account.getBalance());
			} catch (ClientWebException e) {
				throw new RuntimeException(e);
			}
			break;
		case deposit:
			try {
				restClient.resource(API_ROOT_URL + "accounts/" + selectedAccount + "/deposits")
						.contentType(MediaType.APPLICATION_JSON_TYPE).post(Account.class, new BigDecimal(input));
				output.println("Deposit accepted");
			} catch (NumberFormatException e) {
				throw new ATMException("Invalid amount");
			} catch (ClientWebException e) {
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
