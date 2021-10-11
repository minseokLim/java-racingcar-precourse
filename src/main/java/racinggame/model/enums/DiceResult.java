package racinggame.model.enums;

public enum DiceResult {
	MOVING_FORWARD, STOP;

	public boolean isMovingForward() {
		return this == MOVING_FORWARD;
	}
}
