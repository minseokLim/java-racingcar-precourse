package racinggame.model;

import java.util.Objects;

import racinggame.model.enums.DiceResult;

public class Car implements Comparable<Car> {
	private static final int INITIAL_POSITION_VALUE = 0;
	private static final String NAME_POSITION_SEPARATOR = " : ";

	private final Name name; // Car 클래스의 식별자. 즉 이름이 같은 Car 객체는 서로 동등하다.
	private Position position;

	public Car(final String name) {
		Objects.requireNonNull(name);
		this.name = new Name(name);
		this.position = new Position(INITIAL_POSITION_VALUE);
	}

	public void race(final DiceResult diceResult) {
		if (diceResult.isMovingForward()) {
			position = position.getMovingForwardPosition();
		}
	}

	public boolean hasSamePositionWith(final Car car) {
		return car.hasSamePositionWith(position);
	}

	private boolean hasSamePositionWith(final Position position) {
		return this.position.equals(position);
	}

	public String toStatusString() {
		return name + NAME_POSITION_SEPARATOR + position.toStatusBar();
	}

	public String getName() {
		return name.toString();
	}

	@Override
	public int compareTo(final Car car) {
		return this.position.compareTo(car.position);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Car car = (Car)obj;
		return name.equals(car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
