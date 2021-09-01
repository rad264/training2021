package com.smbcgroup.training.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smbcgroup.training.builder.ImmutableDate.Builder;

public class ImmutableDateTest {

	@Test
	public void test2021_8_26_16_45_59_999() {
		assertEquals("2021-8-26 16:45:59:999", new ImmutableDate(2021, 8, 26, 16, 45, 59, 999).toString());
	}

	@Test
	public void test2021_8_26_00_00_00_000() {
		Builder builder = new ImmutableDate.Builder().year(2021).month(8).day(26);
		ImmutableDate aug26 = builder.build();
		assertEquals("2021-8-26 0:0:0:0", aug26.toString());
		ImmutableDate aug27 = builder.day(27).build();
		assertEquals("2021-8-27 0:0:0:0", aug27.toString());
	}

}
