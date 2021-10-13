package racinggame.model;

import racinggame.model.car.Car;

public final class Location {

	private final Car car;

	private final Distance distance;

	private Location(Car car, Distance distance) {
		validate(car);
		validate(distance);
		this.car = car;
		this.distance = distance;
	}

	public static Location of(Car car, Distance distance) {
		return new Location(car, distance);
	}

	public Distance distance() {
		return distance;
	}

	public Car car() {
		return car;
	}

	/**
	 * <p>다음 이동되는 위치 반환</p>
	 * @return 위치
	 */
	Location nextMove() {
		return new Location(car, this.distance.add(Distance.from(car.move().distance())));
	}

	/**
	 * <p>주어진 대상과 비교하여 더 먼 거리의 위치를 반환</p>
	 * @param target 비교할 대상
	 * @return 먼 거리의 위치
	 */
	Location fartherDistance(Location target) {
		if (distance.equalOrMoreThan(target.distance)) {
			return this;
		}
		return target;
	}

	/**
	 * <p>거리 동일 여부</p>
	 * @param target 비교할 대상
	 * @return 동일 여부
	 */
	boolean equalDistance(Location target) {
		return distance.equals(target.distance);
	}

	private void validate(Distance distance) {
		if (distance == null) {
			throw new IllegalArgumentException("distance must not be null");
		}
	}

	private void validate(Car car) {
		if (car == null) {
			throw new IllegalArgumentException("car must not be null");
		}
	}

}
