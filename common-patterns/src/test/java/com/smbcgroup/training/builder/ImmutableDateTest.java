package com.smbcgroup.training.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImmutableDateTest {

	@Test
	public void test2021_8_26_16_45_59_999() {
	ImmutableDate date = new ImmutableDate.Builder()
			.withYear(2021)
			.withMonth(8)
			.withDay(26)
			.withHour(16)
			.withMinute(45)
			.withSecond(59)
			.withMillisecond(999)
			.build();
	assertEquals("2021-8-26 16:45:59:999", date.toString());
	}

	@Test
	public void test2021_8_26_00_00_00_000() {
		ImmutableDate date = new ImmutableDate.Builder()
				.withYear(2021)
				.withMonth(8)
				.withDay(26)
				.build();
	assertEquals("2021-8-26 0:0:0:0", date.toString());
	}

}
