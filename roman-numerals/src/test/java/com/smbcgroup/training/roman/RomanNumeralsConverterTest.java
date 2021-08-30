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
	public void test2toII() {
		assertEquals("II", converter.toRomanNumeral(2));
	}
	
	@Test
	public void test3toIII() {
		assertEquals("III", converter.toRomanNumeral(3));
	}
	
	@Test
	public void test4toIV() {
		assertEquals("IV", converter.toRomanNumeral(4));
	}
	
	@Test
	public void test5toV() {
		assertEquals("V", converter.toRomanNumeral(5));
	}
	
	@Test
	public void test9toIX() {
		assertEquals("IX", converter.toRomanNumeral(9));
	}
	
	@Test
	public void test10toX() {
		assertEquals("X", converter.toRomanNumeral(10));
	}
	
	@Test
	public void test100toC() {
		assertEquals("C", converter.toRomanNumeral(100));
	}
	
	@Test
	public void test1053toMLIII() {
		assertEquals("MLIII", converter.toRomanNumeral(1053));
	}
	
	@Test
	public void test40toXL() {
		assertEquals("XL", converter.toRomanNumeral(40));
	}
	
	@Test
	public void test90toCX() {
		assertEquals("XC", converter.toRomanNumeral(90));
	}
	
	@Test
	public void test3999toMMMCMXCIX() {
		assertEquals("MMMCMXCIX", converter.toRomanNumeral(3999));
	}
	
	@Test
	public void test2757toMMDCCLVII() {
		assertEquals("MMDCCLVII", converter.toRomanNumeral(2757));
	}
	
	@Test
	public void test329toCCCXXIX() {
		assertEquals("CCCXXIX", converter.toRomanNumeral(329));
	}
	
	@Test
	public void test0toEmptyString() {
		assertEquals("", converter.toRomanNumeral(0));
	}

	//-----test toInteger-----//
	@Test
	public void testITo1() {
		assertEquals((Integer) 1, converter.toInteger("I"));
	}
	
	@Test
	public void testIITo2() {
		assertEquals((Integer) 2, converter.toInteger("II"));
	}
	
	@Test
	public void testXTo10() {
		assertEquals((Integer) 10, converter.toInteger("X"));
	}
	
	@Test
	public void testIVTo4() {
		assertEquals((Integer) 4, converter.toInteger("IV"));
	}
	
	@Test
	public void testIXTo9() {
		assertEquals((Integer) 9, converter.toInteger("IX"));
	}
	
	@Test
	public void testXLTo40() {
		assertEquals((Integer) 40, converter.toInteger("XL"));
	}
	
	
}
