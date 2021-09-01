package com.smbcgroup.training.mocking;

public class MockDie extends Die {
	
	private int[] rolls;
	private int counter = 0;

	@Override
	public int roll() {
		if (counter == rolls.length) {
			counter = 0;
		}
		return rolls[counter++];
	}
	
	public void setRolls(int... rolls) { 
		this.rolls = rolls;
	}
}
