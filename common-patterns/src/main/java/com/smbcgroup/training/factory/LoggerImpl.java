package com.smbcgroup.training.factory;

import java.util.EnumMap;

public class LoggerImpl implements Logger {
	
	private static LoggerImpl loggerImpl = new LoggerImpl(); //static initializer block
	
	
	private StringBuilder debugLogs = new StringBuilder("DEBUG LOGS:");
	private StringBuilder infoLogs = new StringBuilder("INFO LOGS:");
	private StringBuilder errorLogs = new StringBuilder("ERROR LOGS:");
	private EnumMap<LogLevel, StringBuilder> logs = new EnumMap<>(LogLevel.class);
	
	private LoggerImpl() { //make private to prevent other classes from instantiating
		logs.put(LogLevel.DEBUG, debugLogs);
		logs.put(LogLevel.INFO, infoLogs);
		logs.put(LogLevel.ERROR, errorLogs);
	}
	
	//static instance method so it belongs to the class
	//and there is only one instance of class so therefore only one instance of the obj
	public static LoggerImpl getLoggerImpl() {
		return loggerImpl;
	}

	@Override
	public void debug(String message) {
		newLog(LogLevel.DEBUG, message);
	}

	@Override
	public void info(String message) {
		newLog(LogLevel.INFO, message);
	}

	@Override
	public void error(String message, Exception e) {
		newLog(LogLevel.ERROR, message);
		for (StackTraceElement el : e.getStackTrace())
			addLine(LogLevel.ERROR, el.toString());
	}

	@Override
	public String print(LogLevel level) {
		return logs.get(level).toString();
	}

	private void newLog(LogLevel level, String message) {
		addLine(level, level.name() + ": " + message);
	}

	private void addLine(LogLevel level, String message) {
		logs.get(level).append("\n").append(message);
	}
	

}
