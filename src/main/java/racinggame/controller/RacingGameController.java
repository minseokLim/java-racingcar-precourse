package racinggame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import racinggame.controller.response.RacingGameResponse;
import racinggame.exception.BadRequestException;
import racinggame.model.Car;
import racinggame.model.CarCollection;
import racinggame.model.Game;
import racinggame.model.Round;
import racinggame.repository.CarCollectionRepository;
import racinggame.repository.RoundRepository;

public class RacingGameController {
	private static final String CAR_NAME_DELIMITER = ",";
	private static final CarCollectionRepository CAR_COLLECTION_REPOSITORY = new CarCollectionRepository();
	private static final RoundRepository ROUND_REPOSITORY = new RoundRepository();

	public String saveCars(final String names) {
		return handleException(() -> CAR_COLLECTION_REPOSITORY.save(getCarsWithNames(names.split(CAR_NAME_DELIMITER))));
	}

	public String saveRound(final String round) {
		return handleException(() -> ROUND_REPOSITORY.save(new Round(round)));
	}

	public RacingGameResponse startGame(final String carId, final String roundId) {
		return handleException(() -> {
			CarCollection cars = CAR_COLLECTION_REPOSITORY.getById(carId);
			Round round = ROUND_REPOSITORY.getById(roundId);
			Game game = new Game(cars, round);
			game.play();

			return new RacingGameResponse(game.getTotalStatus(), game.getWinnerNames());
		});
	}

	public void deleteCars(final String carId) {
		CAR_COLLECTION_REPOSITORY.deleteById(carId);
	}

	public void deleteRound(final String roundId) {
		ROUND_REPOSITORY.deleteById(roundId);
	}

	private <O> O handleException(final Supplier<O> supplier) {
		try {
			return supplier.get();
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	private CarCollection getCarsWithNames(final String[] names) {
		List<Car> cars = new ArrayList<>();

		for (String name : names) {
			cars.add(new Car(name));
		}

		return new CarCollection(cars);
	}
}
