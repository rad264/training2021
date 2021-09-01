package com.smbcgroup.training.builder;

public class ImmutableDate {
	
	public static class Builder { //static bc Builder is not belonging to an instance of Immutable date
		private int year;
		private int month;
		private int day;
		private int hour;
		private int minute;
		private int second;
		private int millisecond;
		
		public Builder withYear(int year) {
			this.year = year;
			return this; //return the builder
		}
		
		
		public Builder withMonth(int month) {
			this.month = month;
			return this; 
		}
		
		public Builder withDay(int day) {
			this.day = day;
			return this;
		}
		
		public Builder withHour(int hour) {
			this.hour = hour;
			return this;
		}
		
		public Builder withMinute(int minute) {
			this.minute = minute;
			return this;
		}
		
		public Builder withSecond(int second) {
			this.second = second;
			return this;
		}
		
		public Builder withMillisecond(int millisecond) {
			this.millisecond = millisecond;
			return this;
		}
		
		public ImmutableDate build() {
			ImmutableDate date = new ImmutableDate();
			date.year = this.year;
			date.month = this.month;
			date.day = this.day;
			date.hour = this.hour;
			date.minute = this.minute;
			date.second = this.second;
			date.millisecond = this.millisecond;
			
			return date;
			
		}
		
		
	}
	
	private int year = 1979;
	private int month;
	private int day = 1;
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	

	/*
	 * public ImmutableDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}
	 */
	private ImmutableDate() {
	
	}

	@Override
	public String toString() {
		return String.format("%d-%d-%d %d:%d:%d:%d", year, month, day, hour, minute, second, millisecond);
	}

	public static void main(String[] args) {
		ImmutableDate date1 = new ImmutableDate();
		System.out.println(date1);
		
		ImmutableDate date2 = new ImmutableDate.Builder()
				.withYear(2021)
				.withMonth(8)
				.withDay(27)
				.withHour(5)
				.withMinute(29)
				.withSecond(47)
				.withMillisecond(10)
				.build();
		
		System.out.println(date2);
		
		ImmutableDate date3 = new ImmutableDate.Builder()
				.withYear(2021)
				.withMonth(8)
				.withHour(5)
				.build();
		
		System.out.println(date3);
		
	}
	
}
