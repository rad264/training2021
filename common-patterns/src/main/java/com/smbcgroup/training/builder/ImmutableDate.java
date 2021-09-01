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

	public static class Builder {
		private int year = 1979;
		private int month;
		private int day = 1;
		private int hour;
		private int minute;
		private int second;
		private int millisecond;

		public Builder year(int year) {
			this.year = year;
			return this;
		}

		public Builder month(int month) {
			this.month = month;
			return this;
		}

		public Builder day(int day) {
			this.day = day;
			return this;
		}

		public Builder hour(int hour) {
			this.hour = hour;
			return this;
		}

		public Builder minute(int minute) {
			this.minute = minute;
			return this;
		}

		public Builder second(int second) {
			this.second = second;
			return this;
		}

		public Builder millisecond(int millisecond) {
			this.millisecond = millisecond;
			return this;
		}
		
		public ImmutableDate build() {
			return new ImmutableDate(year, month, day, hour, minute, second, millisecond);
		}

	}

}
