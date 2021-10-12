package racinggame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import racinggame.model.enums.DiceResult;

public class CarCollection {
	private static final String EMPTY_CARS_ERR_MSG = "1개 이상의 자동차가 필요합니다.";
	private static final String DICE_RESULTS_SIZE_ERR_MSG = "주사위의 결과와 자동차의 수는 일치해야합니다.";
	private static final String DUPLICATE_CAR_NAME_ERR_MSG = "중복된 이름의 자동차는 허용되지 않습니다.";
	private static final String CAR_NAME_DELIMITER = ",";

	private final List<Car> cars;

	public CarCollection(final List<Car> cars) {
		Objects.requireNonNull(cars);
		this.cars = Collections.unmodifiableList(cars);
		validateField();
	}

	private void validateField() {
		if (cars.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_CARS_ERR_MSG);
		}

		Set<Car> distinctCars = new HashSet<>(cars);

		if (cars.size() != distinctCars.size()) {
			throw new IllegalArgumentException(DUPLICATE_CAR_NAME_ERR_MSG);
		}
	}

	public void race(final List<DiceResult> diceResults) {
		if (diceResults.size() != cars.size()) {
			throw new IllegalArgumentException(DICE_RESULTS_SIZE_ERR_MSG);
		}

		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).race(diceResults.get(i));
		}
	}

	public String getWinningCarsAsString() {
		Iterator<Car> iterator = getSortedCars().iterator();
		Car winner = iterator.next();
		Car nextWinner;
		StringJoiner result = new StringJoiner(CAR_NAME_DELIMITER).add(winner.getName());

		while (iterator.hasNext() && (nextWinner = iterator.next()).hasSamePositionWith(winner)) {
			result.add(nextWinner.getName());
		}

		return result.toString();
	}

	private List<Car> getSortedCars() {
		List<Car> sortedCars = new ArrayList<>(cars);
		sortedCars.sort(Comparator.reverseOrder());
		return sortedCars;
	}

	public String toStatusString() {
		StringBuilder result = new StringBuilder();

		for (Car car : cars) {
			result.append(car.toStatusString()).append(System.lineSeparator());
		}

		return result.toString();
	}
}
