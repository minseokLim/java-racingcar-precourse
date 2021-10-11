package racinggame.model;

import java.util.Objects;

public class Position implements Comparable<Position> {
	static final int MIN_VALUE = 0;
	private static final String SIZE_ERR_MSG = "위치값은 " + MIN_VALUE + " 이상이여야 합니다.";
	private static final char MARKER = '-';

	private final int position;

	public Position(final int position) {
		this.position = position;
		validateField();
	}

	private void validateField() {
		if (position < MIN_VALUE) {
			throw new IllegalArgumentException(SIZE_ERR_MSG);
		}
	}

	public Position getMovingForwardPosition() {
		return new Position(position + 1);
	}

	public String toStatusBar() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < position; i++) {
			result.append(MARKER);
		}

		return result.toString();
	}

	@Override
	public int compareTo(final Position other) {
		return Integer.compare(this.position, other.position);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Position other = (Position)obj;
		return this.position == other.position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
