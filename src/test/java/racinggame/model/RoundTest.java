package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RoundTest {
	@ParameterizedTest
	@ValueSource(strings = {"abc", "-1", "2147483648"})
	void 객체_생성_시_유효성_검사(String input) {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Round(input)
		);
	}
}
