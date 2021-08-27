package com.smbcgroup.training.factory;

public interface Logger {
	
	void debug(String message);
	
	void info(String message);
	
	void error(String message, Exception e);
	
	String print(LogLevel level);

}
