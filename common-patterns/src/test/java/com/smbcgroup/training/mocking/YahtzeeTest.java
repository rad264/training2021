package com.smbcgroup.training.mocking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class YahtzeeTest {

	private static final MockDie die = new MockDie();
	private Yahtzee game = new Yahtzee(die);
	
	@Test
	public void testYahtzee() {
		die.setRolls(6);
		assertEquals(Yahtzee.Result.YAHTZEE, game.roll());
	}
	
	
	@Test
	public void testChance() {
		die.setRolls(1, 2, 3, 5, 6);
		assertEquals(Yahtzee.Result.CHANCE, game.roll());
	}

}
