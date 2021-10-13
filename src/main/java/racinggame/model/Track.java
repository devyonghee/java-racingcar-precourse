package racinggame.model;

import java.util.ArrayList;

import racinggame.model.car.Cars;
import racinggame.model.common.Limit;

public final class Track {

	private final LocationsHistory history = LocationsHistory.from(new ArrayList<>());
	private final Limit<Laps> lapsLimit;
	private Locations locations;

	public Track(Locations locations, Limit<Laps> lapsLimit) {
		validate(locations);
		validate(lapsLimit);
		this.locations = locations;
		this.lapsLimit = lapsLimit;
	}

	public static Track of(Locations locations, Limit<Laps> lapsLimit) {
		return new Track(locations, lapsLimit);
	}

	/**
	 * <p>위치들를 이동시키고 기록</p>
	 */
	public void go() {
		validateTrafficLight();
		locations = locations.nextMoves();
		history.add(locations);
	}

	/**
	 * <p>현재 계속 진행해도 되는지 여부</p>
	 * @return 진행 가능 여부
	 */
	public boolean isGreenLight() {
		return this.lapsLimit.isOk(currentLaps());
	}

	/**
	 * <p>가장 멀리 간 자동차들 반환</p>
	 * @return 멀리 간 자동차들
	 */
	public Cars mostMovedCars() {
		return locations.mostMoves();
	}

	public LocationsHistory history() {
		return history;
	}

	private void validateTrafficLight() {
		if (isRedLight()) {
			throw new IllegalStateException("can`t go any more");
		}
	}

	private boolean isRedLight() {
		return !isGreenLight();
	}

	private Laps currentLaps() {
		return Laps.from(history.size());
	}

	private void validate(Locations locations) {
		if (locations == null) {
			throw new IllegalArgumentException("locations must not be null");
		}
	}

	private void validate(Limit<Laps> lapsLimit) {
		if (lapsLimit == null) {
			throw new IllegalArgumentException("laps limit must not be null");
		}
	}
}
