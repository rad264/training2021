package com.smbcgroup.training.atm;

public class NonUniqueIdException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NonUniqueIdException() {
		super();
	}
	
	public NonUniqueIdException(String message) {
		super(message);
	}
}