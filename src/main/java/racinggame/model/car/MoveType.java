package racinggame.model.car;

public enum MoveType {
	GO(1), STOP(0);

	private final int distance;

	MoveType(int distance) {
		this.distance = distance;
	}

	/**
	 * <p>유형에 따아 움직인 거리를 반환한다</p>
	 * @return 움직인 거리
	 */
	public int distance() {
		return distance;
	}
}
