package com.smbcgroup.training.mocking;

public class DieServiceImplControlled implements DieService {
	
	private int dieValue = 6;
	
	public void setDieValue(int newValue) {
		this.dieValue = newValue;
	}

	@Override
	public int roll() {
		return dieValue;
	}

}
