package com.smbcgroup.training.mocking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class YahtzeeTest {
	
	DieServiceImpl fairDie = new DieServiceImpl();
	DieServiceImplControlled fixedDie = new DieServiceImplControlled();
	private Yahtzee gameFair = new Yahtzee(fairDie);
	private Yahtzee gameFixed = new Yahtzee(fixedDie);
	
	@Test
	public void testYahtzee() {
		fixedDie.setDieValue(5);
		assertEquals(5, fixedDie.roll());
		assertEquals(Yahtzee.Result.YAHTZEE, gameFixed.roll());
	}
	
	
	@Test
	public void testChance() {
		assertEquals(Yahtzee.Result.CHANCE, gameFair.roll());
	}

}
