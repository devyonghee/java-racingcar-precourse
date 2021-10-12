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

	public void go() {
		if (isRedLight()) {
			throw new IllegalStateException("can`t go any more");
		}
		locations = locations.nextMoves();
		history.add(locations);
	}

	public boolean isGreenLight() {
		return this.lapsLimit.isOk(currentLaps());
	}

	public LocationsHistory history() {
		return history;
	}

	public Cars mostMovedCars() {
		return locations.mostMoves();
	}

	private boolean isRedLight() {
		return !isGreenLight();
	}

	private Laps currentLaps() {
		return Laps.from(history.length());
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
