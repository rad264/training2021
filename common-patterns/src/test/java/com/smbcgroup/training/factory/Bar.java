package com.smbcgroup.training.factory;

public class Bar {
	private static final Logger logger = LoggerFactory.getLogger();
	
	public boolean isBar(String str) {
		logger.debug("Entering method Bar.isBar");
		boolean isBar = false;
		try {
			isBar = str.equalsIgnoreCase("bar");
		} catch(Exception e) {
			logger.error("Error comparing String: " + str, e);
		}
		logger.info(str + (isBar ? " is" : " is not") + " bar");
		return isBar;
	}

}
