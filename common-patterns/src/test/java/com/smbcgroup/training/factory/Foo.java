package com.smbcgroup.training.factory;

public class Foo {
	private static final Logger logger = LoggerFactory.getLogger();
	
	public boolean isFoo(String str) {
		logger.debug("Entering method Foo.isFoo");
		boolean isFoo = false;
		try {
			isFoo = str.equalsIgnoreCase("foo");
		} catch(Exception e) {
			logger.error("Error comparing String: " + str, e);
		}
		logger.info(str + (isFoo ? " is" : " is not") + " foo");
		return isFoo;
	}

}
