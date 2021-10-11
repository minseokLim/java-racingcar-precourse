package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static racinggame.model.enums.DiceResult.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {
	private static final String WINNING_DRIVER_NAME = "mason";
	private static final String LOSING_DRIVER_NAME = "tom";
	private static final int NUMBER_OF_RACE = 10;
	private static final String WINNING_DRIVER_CAR_MOVING_10_TIMES_TO_STRING = "mason : ----------";

	private Car winningCar;
	private Car losingCar;

	@BeforeEach
	void setUp() {
		winningCar = new Car(WINNING_DRIVER_NAME);
		losingCar = new Car(LOSING_DRIVER_NAME);
	}

	@Test
	@DisplayName("이를 통해 race()와 compareTo() 메서드 모두를 테스트할 수 있다.")
	void race() {
		winningCar.race(MOVING_FORWARD);
		losingCar.race(STOP);

		assertThat(winningCar).isGreaterThan(losingCar);
	}

	@Test
	void hasSamePositionWith() {
		assertThat(winningCar.hasSamePositionWith(losingCar)).isTrue();
	}

	@Test
	void toStatusString() {
		for (int i = 0; i < NUMBER_OF_RACE; i++) {
			winningCar.race(MOVING_FORWARD);
		}

		assertThat(winningCar.toStatusString()).isEqualTo(WINNING_DRIVER_CAR_MOVING_10_TIMES_TO_STRING);
	}

	@Test
	void equals() {
		assertThat(winningCar).isEqualTo(new Car(WINNING_DRIVER_NAME));
	}
}
