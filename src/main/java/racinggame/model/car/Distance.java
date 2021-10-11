package racinggame.model.car;

public final class Distance {

	private final int value;

	private Distance(int value) {
		validate(value);
		this.value = value;
	}

	public static Distance from(int init) {
		return new Distance(init);
	}

	public int value() {
		return value;
	}

	public Distance add(Distance distance) {
		return new Distance(value + distance.value);
	}

	public boolean equalOrMoreThan(Distance distance) {
		return distance.value <= value;
	}

	private void validate(int value) {
		if (value < 0) {
			throw new IllegalArgumentException(String.format("distance must be positive, but provided %d", value));
		}
	}
}
