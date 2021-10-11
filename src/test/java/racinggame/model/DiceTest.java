package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;
import racinggame.model.enums.DiceResult;

public class DiceTest {
	private static final int MOVING_FORWARD_NUMBER = Dice.MOVING_THRESHOLD;
	private static final int STOP_NUMBER = Dice.MOVING_THRESHOLD - 1;

	@Test
	@DisplayName("Randoms.pickNumberInRange()의 값이 MOVING_THRESHOLD보다 크거나 같으면 전진, 작으면 멈춤을 반환하는지 테스트")
	void roll() {
		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(MOVING_FORWARD_NUMBER, STOP_NUMBER);

			assertThat(Dice.roll()).isEqualTo(DiceResult.MOVING_FORWARD);
			assertThat(Dice.roll()).isEqualTo(DiceResult.STOP);
		}
	}
}
