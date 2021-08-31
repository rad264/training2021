package com.smbcgroup.training.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImmutableDateTest {

	@Test
	public void test2021_8_26_16_45_59_999() {
		assertEquals("2021-8-26 16:45:59:999", new ImmutableDate.DateBuilder(2021, 8, 26).hour(16).minute(45).second(59).millisecond(999).build().toString());
	}

	@Test
	public void test2021_8_26_00_00_00_000() {
		assertEquals("2021-8-26 0:0:0:0", new ImmutableDate.DateBuilder(2021, 8, 26).build().toString());
	}

}
