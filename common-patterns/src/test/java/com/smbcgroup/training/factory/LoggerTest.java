package com.smbcgroup.training.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoggerTest {
	
	@Test
	public void testFooAndBar() {
		new Foo().isFoo("foo");
		new Bar().isBar("bar");
		String logContents = LoggerFactory.getLogger().print(LogLevel.DEBUG);
		assertTrue(logContents.contains("Entering method Foo.isFoo"));
		assertTrue(logContents.contains("Entering method Bar.isBar"));
	}

}
