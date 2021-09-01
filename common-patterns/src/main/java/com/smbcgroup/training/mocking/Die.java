package com.smbcgroup.training.mocking;

import java.util.Random;

public class Die implements DieService {
	
	private Random random = new Random();
	private int sides;
	
	public Die() {
		this(6);
	}
	
	public Die(int sides) {
		this.sides = sides;
	}
	
	@Override
	public int roll() {
		return random.nextInt(sides) + 1;
	}
	

}
