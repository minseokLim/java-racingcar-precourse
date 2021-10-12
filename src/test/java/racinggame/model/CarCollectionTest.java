package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static racinggame.model.enums.DiceResult.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racinggame.model.enums.DiceResult;

public class CarCollectionTest {
	private static final String MASON = "mason";
	private static final String TOM = "tom";
	private static final String JERRY = "jerry";
	private static final List<DiceResult> DICE_RESULTS = Arrays.asList(STOP, MOVING_FORWARD, MOVING_FORWARD);
	private static final List<DiceResult> DIFFERENT_SIZE_DICE_RESULTS = Arrays.asList(STOP, MOVING_FORWARD);
	private static final String WINNING_CARS_STRING = String.join(",", TOM, JERRY);
	private static final String CARS_STATUS_STRING =
		"mason : " + System.lineSeparator() + "tom : -" + System.lineSeparator() + "jerry : -" + System.lineSeparator();
	private static final int EXPECTED_DICE_RESULTS_SIZE = 3;

	private CarCollection cars;

	@BeforeEach
	void setUp() {
		cars = new CarCollection(Arrays.asList(new Car(MASON), new Car(TOM), new Car(JERRY)));
		cars.race(DICE_RESULTS);
	}

	@Test
	void 빈리스트를_인수로_객체_생성_시_유효성_검사() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new CarCollection(Collections.emptyList())
		);
	}

	@Test
	void 중복된_이름의_자동차들을_인수로_객체_생성_시_유효성_검사() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new CarCollection(Arrays.asList(new Car(MASON), new Car(MASON)))
		);
	}

	@Test
	void race() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			cars.race(DIFFERENT_SIZE_DICE_RESULTS)
		);
	}

	@Test
	void getWinningCarsAsString() {
		assertThat(cars.getWinningCarsAsString()).isEqualTo(WINNING_CARS_STRING);
	}

	@Test
	void toStatusString() {
		assertThat(cars.toStatusString()).isEqualTo(CARS_STATUS_STRING);
	}

	@Test
	void generateDiceResults() {
		assertThat(cars.generateDiceResults()).hasSize(EXPECTED_DICE_RESULTS_SIZE);
	}
}
