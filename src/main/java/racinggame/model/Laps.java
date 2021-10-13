package racinggame.model;

public final class Laps {

	private final int count;

	private Laps(int count) {
		validate(count);
		this.count = count;
	}

	public static Laps from(int count) {
		return new Laps(count);
	}

	/**
	 * <p>현재 객체가 주어진 대상보다 더 큰지 여부</p>
	 * @param laps 비교할 대상
	 * @return 큰 여부
	 */
	boolean overThan(Laps laps) {
		return laps.count < count;
	}

	private void validate(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(
				String.format("laps count must be positive, but provided %d", count));
		}
	}
}
