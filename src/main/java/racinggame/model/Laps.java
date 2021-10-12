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
