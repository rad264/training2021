package com.smbcgroup.training.atm.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smbcgroup.training.atm.ATMService;
import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.Transaction;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.TransactionNotFoundException;
import com.smbcgroup.training.atm.dao.UserAlreadyExistException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;
import com.smbcgroup.training.atm.dao.jpa.AccountJPAImpl;

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
	@RequestMapping(value = "accounts/{accountNumber}/deposit/{amount:.+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deposit(@PathVariable("accountNumber") String accountNumber, @PathVariable("amount") String amount) {
		try {
			BigDecimal depositAmount = new BigDecimal(amount);
			service.deposit(accountNumber, depositAmount);
			service.createTransaction(accountNumber, depositAmount, "CREDIT", "DEPOSIT");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@ApiOperation("Withdraw")
	@RequestMapping(value = "accounts/{accountNumber}/withdraw/{amount:.+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> withdraw(@PathVariable("accountNumber") String accountNumber, @PathVariable("amount") BigDecimal amount) {
		try {
			service.withdraw(accountNumber, amount);
			service.createTransaction(accountNumber, amount.negate(), "DEBIT", "WITHDRAW");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Transfer")
	@RequestMapping(value = "accounts/{fromAccountNumber}/transfer/{toAccountNumber}/{amount:.+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> transfer(@PathVariable("fromAccountNumber") String fromAccountNumber, @PathVariable("toAccountNumber") String toAccountNumber, @PathVariable("amount") BigDecimal amount){
		try {
			service.transfer(fromAccountNumber, toAccountNumber, amount);
			service.createTransaction(fromAccountNumber, amount.negate(), "DEBIT", "TRANSFER TO " + toAccountNumber);
			service.createTransaction(toAccountNumber, amount, "CREDIT", "TRANSFER FROM " + fromAccountNumber);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@ApiOperation("Create Account")
	@RequestMapping(value = "users/{userId}/create/{accountNumber}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@PathVariable("userId") String userId, @PathVariable("accountNumber") String accountNumber) {
		try {
			return new ResponseEntity<Account>(service.createAccount(userId, accountNumber), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Create User")
	@RequestMapping(value = "users/create/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@PathVariable("userId") String userId) {
		try {
			return new ResponseEntity<User>(service.createUser(userId), HttpStatus.OK);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

	@ApiOperation("Get Transaction")
	@RequestMapping(value = "transactions/{transactionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") String transactionId) {
		try {
			return new ResponseEntity<Transaction>(service.getTransaction(transactionId), HttpStatus.OK);
		} catch (TransactionNotFoundException e) {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Get All Transactions By Account")
	@RequestMapping(value = "transactions/accounts/{accountNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction[]> getAllTransactionsByAccount(@PathVariable("accountNumber") String accoutNumber) {
		try {
			return new ResponseEntity<Transaction[]>(service.getAllTransactionsByAccount(accoutNumber), HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			return new ResponseEntity<Transaction[]>(HttpStatus.NOT_FOUND);
		}
	}
}
