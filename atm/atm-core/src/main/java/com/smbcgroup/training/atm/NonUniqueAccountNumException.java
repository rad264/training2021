package com.smbcgroup.training.atm;

public class NonUniqueAccountNumException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NonUniqueAccountNumException() {
		super();
	}
	
	public NonUniqueAccountNumException(String message) {
		super(message);
	}
}
