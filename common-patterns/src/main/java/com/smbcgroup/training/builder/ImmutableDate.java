 package com.smbcgroup.training.builder;

public class ImmutableDate {

	private int year;
	private int month;
	private int day;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private int millisecond = 0;

	public ImmutableDate(DateBuilder builder) {
		this.year = builder.year;
		this.month = builder.month;
		this.day = builder.day;
		this.hour = builder.hour;
		this.minute = builder.minute;
		this.second = builder.second;
		this.millisecond = builder.millisecond;
	}

	@Override
	public String toString() {
		return String.format("%d-%d-%d %d:%d:%d:%d", year, month, day, hour, minute, second, millisecond);
	}
	
	public static class DateBuilder {
		private int year;
		private int month;
		private int day;
		private int hour;
		private int minute;
		private int second;
		private int millisecond;
		
		public DateBuilder(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
		
		public DateBuilder hour(int hour) {
			this.hour = hour;
			return this;
		}
		
		public DateBuilder minute(int minute) {
			this.minute = minute;
			return this;
		}
		
		public DateBuilder second(int second) {
			this.second = second;
			return this;
		}
		
		public DateBuilder millisecond(int millisecond) {
			this.millisecond = millisecond;
			return this;
		}
		
		public ImmutableDate build() {
			ImmutableDate date = new ImmutableDate(this);
			return date;
		}
		
	}

}
