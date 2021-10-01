package com.smbcgroup.training.atm;

public class InvalidAmountException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidAmountException() {
		super();
	}
	
	public InvalidAmountException(String message) {
		super(message);
	}
}
