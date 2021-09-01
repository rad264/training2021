package com.smbcgroup.training.factory;

public class LoggerFactory {
	
	public static Logger getLogger() {
		return LoggerImpl.getInstance();
	}

}
