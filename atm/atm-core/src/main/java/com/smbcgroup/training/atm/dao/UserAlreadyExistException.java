package com.smbcgroup.training.atm.dao;

public class UserAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException() {
		super();
	}
	
	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
