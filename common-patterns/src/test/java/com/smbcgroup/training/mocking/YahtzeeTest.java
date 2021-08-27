package com.smbcgroup.training.mocking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class YahtzeeTest {
	
	private Yahtzee game = new Yahtzee();
	
	@Test
	public void testYahtzee() {
		assertEquals(Yahtzee.Result.YAHTZEE, game.roll());
	}
	
	@Test
	public void testChance() {
		assertEquals(Yahtzee.Result.CHANCE, game.roll());
	}

}
