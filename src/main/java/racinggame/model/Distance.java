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

	/**
	 * <p>주어진 거리를 더해서 반환</p>
	 * @param distance 더해질 거리
	 * @return 더해진 거리
	 */
	public Distance add(Distance distance) {
		return new Distance(value + distance.value);
	}

	/**
	 * <p>현재 객체가 주어진 거리와 동일하거나 더 큰지 여부</p>
	 * @param distance 비교할 거리
	 * @return 같거나 큰 여부
	 */
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
