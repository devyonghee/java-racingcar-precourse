package racinggame.model;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.car.Car;
import racinggame.model.car.Cars;

public final class CarPreparer {

	private final Distance startingLine;

	private CarPreparer(Distance startingLine) {
		validate(startingLine);
		this.startingLine = startingLine;
	}

	public static CarPreparer from(Distance startingLine) {
		return new CarPreparer(startingLine);
	}

	/**
	 * <p>자동차들을 시작 지점의 위치들 생성</p>
	 * @param cars 세팅하기 위한 자동차들
	 * @return 시작지점의 위치들
	 */
	public Locations prepare(Cars cars) {
		Collection<Location> locations = new ArrayList<>();
		for (Car car : cars.collection()) {
			locations.add(Location.of(car, startingLine));
		}
		return Locations.from(locations);
	}

	private void validate(Distance startingLine) {
		if (startingLine == null) {
			throw new IllegalArgumentException("starting line must not be null");
		}
	}
}
