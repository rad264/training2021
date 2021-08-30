package com.smbcgroup.training.mocking;

import java.util.Random;

public class DieServiceImpl implements DieService {
	
	private Random random = new Random();
	private int sides;
	
	public DieServiceImpl() {
		this(6);
	}
	
	public DieServiceImpl(int sides) {
		this.sides = sides;
	}
	
	@Override
	public int roll() {
		return random.nextInt(sides) + 1;
	}
	

}
