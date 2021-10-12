package racinggame.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Round {
	private static final Pattern NUMBER_PATTERN = Pattern.compile("-*\\d+");
	private static final String NON_NUMBER_ERR_MSG = "숫자만 입력 가능합니다.";
	private static final int MIN_VALUE = 1;
	private static final String TOO_SMALL_NUMBER_ERR_MSG = "라운드는 " + MIN_VALUE + " 이상이여야 합니다.";
	private static final String TOO_BIG_NUMBER_ERR_MSG = "입력한 숫자가 너무 큽니다. 최댓값은 2147483647 입니다.";

	private final int round;

	public Round(final String round) {
		validateRound(round);
		this.round = Integer.parseInt(round);
	}

	private void validateRound(final String round) {
		Objects.requireNonNull(round);
		validateNumberPattern(round);
		validateSizeOfRound(round);
	}

	private void validateNumberPattern(String round) {
		if (!NUMBER_PATTERN.matcher(round).matches()) {
			throw new IllegalArgumentException(NON_NUMBER_ERR_MSG);
		}
	}

	private void validateSizeOfRound(String round) {
		long temp = Long.parseLong(round);

		if (temp < MIN_VALUE) {
			throw new IllegalArgumentException(TOO_SMALL_NUMBER_ERR_MSG);
		}

		if (temp > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(TOO_BIG_NUMBER_ERR_MSG);
		}
	}

	public int getRound() {
		return round;
	}
}
