package com.smbcgroup.training.roman;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanNumeralsConverterTest {
	
	RomanNumeralsConverter converter = new RomanNumeralsConverter();

	@Test
	public void test1ToI() {
		assertEquals("I", converter.toRomanNumeral(1));
	}

}
