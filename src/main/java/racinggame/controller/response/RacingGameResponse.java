package racinggame.controller.response;

public class RacingGameResponse {
	public final String totalStatus;
	public final String winnerNames;

	public RacingGameResponse(final String totalStatus, final String winnerNames) {
		this.totalStatus = totalStatus;
		this.winnerNames = winnerNames;
	}
}
