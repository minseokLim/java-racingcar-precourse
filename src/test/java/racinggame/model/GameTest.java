package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
	private static final CarCollection CARS =
		new CarCollection(Arrays.asList(new Car("mason"), new Car("jerry")));
	private static final Round ROUND = new Round("5");

	private Game game;

	@BeforeEach
	void setUp() {
		game = new Game(CARS, ROUND);
	}

	@Test
	void play() {
		game.play();
		assertThatIllegalStateException().isThrownBy(() ->
			game.play()
		);
	}

	@Test
	void getTotalStatus() {
		assertThatIllegalStateException().isThrownBy(() ->
			game.getTotalStatus()
		);
		game.play();
		assertThat(game.getTotalStatus()).isNotEmpty();
	}

	@Test
	void getWinnerNames() {
		assertThatIllegalStateException().isThrownBy(() ->
			game.getWinnerNames()
		);
		game.play();
		assertThat(game.getWinnerNames()).isNotEmpty();
	}
}
