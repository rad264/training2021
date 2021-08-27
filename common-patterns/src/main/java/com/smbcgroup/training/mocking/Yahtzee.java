package com.smbcgroup.training.mocking;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Yahtzee {

	private Die die = new Die();

	public Result roll() {
		Integer[] dice = Stream.generate(die::roll).limit(5).toArray(Integer[]::new);
		return Result.evaluate(dice);
	}

	public enum Result {
		YAHTZEE {
			@Override
			boolean matches(Integer[] dice) {
				Set<Integer> set = new HashSet<>();
				Collections.addAll(set, dice);
				return set.size() == 5;
			}
		},
		CHANCE {
			@Override
			boolean matches(Integer[] dice) {
				return true;
			}
		};

		public static Result evaluate(Integer[] dice) {
			for (Result result : values()) {
				if (result.matches(dice))
					return result;
			}
			return CHANCE;
		}

		abstract boolean matches(Integer[] dice);

	}

}
