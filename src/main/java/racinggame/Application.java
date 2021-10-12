package racinggame;

import java.util.function.Supplier;

import nextstep.utils.Console;
import racinggame.controller.RacingGameController;
import racinggame.controller.response.RacingGameResponse;
import racinggame.exception.BadRequestException;

public class Application {
	private static final RacingGameController CONTROLLER = new RacingGameController();
	private static final String QUERY_FOR_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final String QUERY_FOR_ROUND = "시도할 회수는 몇회인가요?";
	private static final String RACE_RESULT_TITLE = "실행 결과";
	private static final String WINNER_ANNOUNCEMENT_FORMAT = "최종 우승자는 %s 입니다.";

	public static void main(String[] args) {
		startGame();
	}

	private static void startGame() {
		handleException(() -> {
			String carId = getCarId();
			String roundId = getRoundId();
			RacingGameResponse response = CONTROLLER.startGame(carId, roundId);
			printRacingResult(response);
			deleteCache(carId, roundId);
		});
	}

	private static String getCarId() {
		return handleException(() -> {
			System.out.println(QUERY_FOR_CAR_NAMES);
			String names = Console.readLine().trim();
			return CONTROLLER.saveCars(names);
		});
	}

	private static String getRoundId() {
		return handleException(() -> {
			System.out.println(QUERY_FOR_ROUND);
			String round = Console.readLine().trim();

			return CONTROLLER.saveRound(round);
		});
	}

	private static void printRacingResult(final RacingGameResponse response) {
		System.out.println();
		System.out.println(RACE_RESULT_TITLE);
		System.out.println(response.totalStatus);
		System.out.printf(WINNER_ANNOUNCEMENT_FORMAT, response.winnerNames);
	}

	private static void deleteCache(final String carId, final String roundId) {
		CONTROLLER.deleteCars(carId);
		CONTROLLER.deleteRound(roundId);
	}

	private static void handleException(final Runnable runnable) {
		handleException(() -> {
			runnable.run();
			return null;
		});
	}

	private static <O> O handleException(final Supplier<O> supplier) {
		try {
			return supplier.get();
		} catch (BadRequestException e) {
			System.out.println(e.getMessage());
			return handleException(supplier);
		}
	}
}
