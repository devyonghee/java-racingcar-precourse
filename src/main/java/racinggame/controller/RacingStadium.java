package racinggame.controller;

import racinggame.model.CarPreparer;
import racinggame.model.Distance;
import racinggame.model.Laps;
import racinggame.model.Track;
import racinggame.model.car.Cars;
import racinggame.model.common.Limit;
import racinggame.view.Billboard;
import racinggame.view.dto.Results;
import racinggame.view.dto.Winners;

public final class RacingStadium {

	private static final Distance STARTING_LINE = Distance.from(0);
	private final Cars cars;
	private final Limit<Laps> lapsLimit;
	private final Billboard billboard;

	private RacingStadium(Cars cars, Limit<Laps> lapsCount, Billboard billboard) {
		this.cars = cars;
		this.lapsLimit = lapsCount;
		this.billboard = billboard;
	}

	public static RacingStadium of(Cars cars, Limit<Laps> lapsCount, Billboard billboard) {
		return new RacingStadium(cars, lapsCount, billboard);
	}

	public void play() {
		Track track = Track.of(CarPreparer.from(STARTING_LINE).prepare(cars), lapsLimit);
		while (track.isGreenLight()) {
			track.go();
		}

		billboard.exposure(Results.from(track.history()));
		billboard.exposure(Winners.from(track.mostMovedCars()));
	}

}
