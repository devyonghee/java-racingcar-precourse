package racinggame.model.car;

public enum MoveType {
	GO(1), STOP(0);

	private final int distance;

	MoveType(int distance) {
		this.distance = distance;
	}

	public int distance() {
		return distance;
	}
}
