package com.smbcgroup.training.factory;

public class LoggerImplFactory {

	static Logger getInstance() {
		return LoggerImpl.getLoggerImpl();
	}
}
