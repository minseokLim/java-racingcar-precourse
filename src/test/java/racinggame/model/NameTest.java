package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
	private static final String NORMAL_NAME = "mason";

	@ParameterizedTest
	@ValueSource(strings = {"christopher", " "})
	@DisplayName("이름의 길이가 LENGTH_LIMIT를 넘거나 빈 값일 때 익셉션을 발생시키는지를 테스트")
	void 객체_생성_시_유효성_검사(String value) {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new Name(value)
		);
	}

	@Test
	void toString_메서드() {
		assertThat(new Name(NORMAL_NAME)).hasToString(NORMAL_NAME);
	}

	@Test
	void equals() {
		assertThat(new Name(NORMAL_NAME)).isEqualTo(new Name(NORMAL_NAME));
	}
}
