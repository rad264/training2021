package com.smbcgroup.training.atm.api;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smbcgroup.training.atm.ATMService;
import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.InvalidAmountException;
import com.smbcgroup.training.atm.NonUniqueIdException;
import com.smbcgroup.training.atm.OverdraftException;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;
import com.smbcgroup.training.atm.dao.jpa.AccountJPAImpl;
import com.smbcgroup.training.atm.api.swagger.SwaggerConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "ATM API")
public class APIController {

	static ATMService service = new ATMService(new AccountJPAImpl());

	@ApiOperation("Get user")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
		try {
			return new ResponseEntity<User>(service.getUser(userId), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation("Get account")
	@RequestMapping(value = "/accounts/{accountNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
		try {
			return new ResponseEntity<Account>(service.getAccount(accountNumber), HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Deposit")
	@RequestMapping(value = "/accounts/{accountNumber}/deposits", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deposit(@PathVariable("accountNumber") String accountNumber, @RequestBody BigDecimal amount) {
		try {
			service.deposit(accountNumber, amount);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			
		} catch (InvalidAmountException e) {
			return new ResponseEntity<String>("Amount must be a positive number.", HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@ApiOperation("Withdraw")
	@RequestMapping(value = "/accounts/{accountNumber}/withdrawals", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity withdraw(@PathVariable("accountNumber") String accountNumber, @RequestBody BigDecimal amount) {
		try {
			service.withdraw(accountNumber, amount);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) { 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (OverdraftException e) {
			return new ResponseEntity<String>("Amount to withdraw must be positive and cannot be greater than current balance.", HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@ApiOperation("Transfer")
	@RequestMapping(value = "/accounts/{accountNumber}/transfers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity transfer(@PathVariable("accountNumber") String accountNumber, String accountNumberToTransfer, @RequestBody BigDecimal amount) {
		try {
			service.transfer(accountNumber, accountNumberToTransfer, amount);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) { 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (InvalidAmountException e) {
			return new ResponseEntity<String>("Amount to transfer must be positive.", HttpStatus.BAD_REQUEST);
		} catch (OverdraftException e) {
			return new ResponseEntity<String>("Amount to transfer cannot be greater than account balance.", HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	@ApiOperation("Get balance")
	@RequestMapping(value = "/accounts/{accountNumber}/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getBalance(@PathVariable("accountNumber") String accountNumber) {
		try {
			return new ResponseEntity<BigDecimal>(service.getAccount(accountNumber).getBalance(), HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<BigDecimal>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Open new account")
	@RequestMapping(value = "users/{userId}/accounts/new-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity openNewAccount(@PathVariable("userId") String userId, @RequestBody BigDecimal amount) {
		try {
			return new ResponseEntity<Integer>(service.openNewAccount(userId, amount), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<BigDecimal>(HttpStatus.NOT_FOUND);
		} catch (InvalidAmountException e) {
			return new ResponseEntity<String>("Amount must be a positive number.", HttpStatus.BAD_REQUEST);	
		}
	}
	
	@ApiOperation("Get account summary")
	@RequestMapping(value = "users/{userId}/accounts/account-summary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAccountSummary(@PathVariable("userId") String userId) {
		try {
			return new ResponseEntity<ArrayList>(service.accountSummary(userId), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Create a new user")
	@RequestMapping(value = "users/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createNewUser(@PathVariable("userId") String userId) {
		
		try {
			service.createNewUser(userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NonUniqueIdException e) {
			return new ResponseEntity<String>("This user ID is already taken. Please choose a different user ID.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation("Get transaction history")
	@RequestMapping(value = "users/{userId}/transaction-history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getTransactionHistory(@PathVariable("userId") String userId) {
		try {
			return new ResponseEntity<ArrayList>(service.getTransactionHistory(userId), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
