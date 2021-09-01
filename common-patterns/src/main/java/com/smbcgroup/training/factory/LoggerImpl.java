package com.smbcgroup.training.factory;

import java.util.EnumMap;

public class LoggerImpl implements Logger {
	
	private StringBuilder debugLogs = new StringBuilder("DEBUG LOGS:");
	private StringBuilder infoLogs = new StringBuilder("INFO LOGS:");
	private StringBuilder errorLogs = new StringBuilder("ERROR LOGS:");
	private EnumMap<LogLevel, StringBuilder> logs = new EnumMap<>(LogLevel.class);
	
	private static LoggerImpl instance;
	
	public static LoggerImpl getInstance() {
		if (instance == null)
			instance = new LoggerImpl();
		return instance;
	}
	
	private LoggerImpl() {
		logs.put(LogLevel.DEBUG, debugLogs);
		logs.put(LogLevel.INFO, infoLogs);
		logs.put(LogLevel.ERROR, errorLogs);
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
