package com.smbcgroup.training.builder;

public class ImmutableDate {

	private int year = 1979;
	private int month;
	private int day = 1;
	private int hour;
	private int minute;
	private int second;
	private int millisecond;

	public ImmutableDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}

	@Override
	public String toString() {
		return String.format("%d-%d-%d %d:%d:%d:%d", year, month, day, hour, minute, second, millisecond);
	}

}
