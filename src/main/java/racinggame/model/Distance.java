package racinggame.model;

import java.util.Objects;

public final class Distance {

	private final int value;

	private Distance(int value) {
		validate(value);
		this.value = value;
	}

	public static Distance from(int value) {
		return new Distance(value);
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Distance distance = (Distance)o;
		return value == distance.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	private void validate(int value) {
		if (value < 0) {
			throw new IllegalArgumentException(String.format("distance must be positive, but provided %d", value));
		}
	}
}
