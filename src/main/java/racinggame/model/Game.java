package racinggame.model;

import java.util.List;
import java.util.StringJoiner;

import racinggame.model.enums.DiceResult;

public class Game {
	private static final String ALREADY_FINISHED_ERR_MSG = "이미 완료된 게임입니다.";
	private static final String NOT_FINISHED_YET_ERR_MSG = "게임이 아직 진행되지 않았습니다.";

	private final CarCollection cars;
	private final Round round;
	private final StringJoiner totalStatus = new StringJoiner(System.lineSeparator());
	private boolean finished = false;

	public Game(final CarCollection cars, final Round round) {
		this.cars = cars;
		this.round = round;
	}

	public void play() {
		checkIfAlreadyFinished();

		for (int i = 0; i < round.getRound(); i++) {
			List<DiceResult> diceResults = cars.generateDiceResults();
			cars.race(diceResults);
			totalStatus.add(cars.toStatusString());
		}

		finished = true;
	}

	public String getTotalStatus() {
		checkIfNotFinishedYet();
		return totalStatus.toString();
	}

	public String getWinnerNames() {
		checkIfNotFinishedYet();
		return cars.getWinningCarsAsString();
	}

	private void checkIfAlreadyFinished() {
		if (finished) {
			throw new IllegalStateException(ALREADY_FINISHED_ERR_MSG);
		}
	}

	private void checkIfNotFinishedYet() {
		if (!finished) {
			throw new IllegalStateException(NOT_FINISHED_YET_ERR_MSG);
		}
	}
}
