package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
	private static final int BACKWARD_POSITION_VALUE = Position.MIN_VALUE;
	private static final int FORWARD_POSITION_VALUE = Position.MIN_VALUE + 1;
	private static final int INVALID_POSITION_VALUE = Position.MIN_VALUE - 1;
	private static final int BIG_POSITION_VALUE = 10;
	private static final String BIG_POSITION_STRING = "----------";

	private Position backwardPosition;
	private Position forwardPosition;

	@BeforeEach
	void setUp() {
		backwardPosition = new Position(BACKWARD_POSITION_VALUE);
		forwardPosition = new Position(FORWARD_POSITION_VALUE);
	}

	@Test
	void 객체_생성_시_유효성_검사() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Position(INVALID_POSITION_VALUE)
		);
	}

	@Test
	void getMovingForwardPosition() {
		assertThat(backwardPosition.getMovingForwardPosition()).isEqualTo(forwardPosition);
	}

	@Test
	void toStatusBar() {
		assertThat(new Position(BIG_POSITION_VALUE).toStatusBar()).isEqualTo(BIG_POSITION_STRING);
	}

	@Test
	void compareTo() {
		assertThat(backwardPosition).isLessThan(forwardPosition);
	}

	@Test
	void equals() {
		assertThat(backwardPosition).isEqualTo(new Position(BACKWARD_POSITION_VALUE));
	}
}
