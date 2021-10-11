package racinggame.model;

import static racinggame.model.enums.DiceResult.*;

import nextstep.utils.Randoms;
import racinggame.model.enums.DiceResult;

public class Dice {
	static final int MOVING_THRESHOLD = 4;
	private static final int START_INCLUSIVE = 0;
	private static final int END_INCLUSIVE = 9;

	private Dice() {
	}

	public static DiceResult roll() {
		int randomNumber = Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE);

		if (randomNumber >= MOVING_THRESHOLD) {
			return MOVING_FORWARD;
		}

		return STOP;
	}
}
