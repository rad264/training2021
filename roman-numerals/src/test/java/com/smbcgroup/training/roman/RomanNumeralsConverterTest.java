package com.smbcgroup.training.roman;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanNumeralsConverterTest {
	
	RomanNumeralsConverter converter = new RomanNumeralsConverter();

	@Test
	public void test1ToI() {
		assertEquals("I", converter.toRomanNumeral(1));
	}
	
	@Test
	public void test2ToII() {
		assertEquals("II", converter.toRomanNumeral(2));
	}
	
	@Test
	public void test4ToIV() {
		assertEquals("IV", converter.toRomanNumeral(4));
	}
	@Test
	public void test9ToIX() {
		assertEquals("IX", converter.toRomanNumeral(9));
	}
	@Test
	public void test8ToVIII() {
		assertEquals("VIII", converter.toRomanNumeral(8));
	}
	
	@Test
	public void test246ToCCXLVI() {
		assertEquals("CCXLVI", converter.toRomanNumeral(246));
	}

	@Test
	public void test789ToDCCLXXXIX() {
		assertEquals("DCCLXXXIX", converter.toRomanNumeral(789));
	}
	
	@Test
	public void test1066TMLXVI() {
		assertEquals("MLXVI", converter.toRomanNumeral(1066));
	}
	
	@Test
	public void test1009ToMIX() {
		assertEquals("MIX", converter.toRomanNumeral(1009));
	}

	@Test
	public void test1776ToMDCCLXXVI() {
		assertEquals("MDCCLXXVI", converter.toRomanNumeral(1776));
	}
	
	@Test
	public void testITo1() {
		Integer answer = 1;
		assertEquals(answer, converter.toInteger("I"));
	}
	
	@Test
	public void testIITo2() {
		Integer answer = 2;
		assertEquals(answer, converter.toInteger("II"));
	}
	
	@Test
	public void testIVTo4() {
		Integer answer = 4;
		assertEquals(answer, converter.toInteger("IV"));
	}
	
	@Test
	public void testIXTo9() {
		Integer answer = 9;
		assertEquals(answer, converter.toInteger("IX"));
	}
	
	@Test
	public void testXTo10() {
		Integer answer = 10;
		assertEquals(answer, converter.toInteger("X"));
	}
	
	@Test
	public void testCCVIITo207() {
		Integer answer = 207;
		assertEquals(answer, converter.toInteger("CCVII"));
	}
	
	@Test
	public void testMMXIVTo2014() {
		Integer answer = 2014;
		assertEquals(answer, converter.toInteger("MMXIV"));
	}
	@Test
	public void testMCMXVIIITo1918() {
		Integer answer = 1918;
		assertEquals(answer, converter.toInteger("MCMXVIII"));
	}
	
//	
//	@Test
//	public void testInvalidNumber() throws Exception {
//		assertEquals("Number exceeds roman numeral limit!", converter.toRomanNumeral(4566));
//	}

}
