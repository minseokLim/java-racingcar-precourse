package racinggame.model;

import java.util.Objects;

public class Name {
	private static final int LENGTH_LIMIT = 5;
	private static final String EMPTY_STRING_ERR_MSG = "이름은 빈 값일 수 없습니다.";
	private static final String OVER_LENGTH_ERR_MSG = "이름은 " + LENGTH_LIMIT + "자 이하여야만 합니다.";

	private final String name;

	public Name(final String name) {
		Objects.requireNonNull(name);
		this.name = name.trim();
		validateField();
	}

	private void validateField() {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_STRING_ERR_MSG);
		}

		if (name.length() > LENGTH_LIMIT) {
			throw new IllegalArgumentException(OVER_LENGTH_ERR_MSG);
		}
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Name other = (Name)obj;
		return name.equals(other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
