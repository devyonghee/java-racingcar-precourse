package racinggame.model.car;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.car.engine.Engine;
import racinggame.model.car.engine.MovementCondition;
import racinggame.model.car.engine.Rotation;

public final class CarFactory {

	private final NamingArtist artist;
	private final MovementCondition<Rotation> engineMovementCondition;

	private CarFactory(NamingArtist artist, MovementCondition<Rotation> engineMovementCondition) {
		validate(artist);
		validate(engineMovementCondition);
		this.artist = artist;
		this.engineMovementCondition = engineMovementCondition;
	}

	public static CarFactory of(NamingArtist artist, MovementCondition<Rotation> engineMovementCondition) {
		return new CarFactory(artist, engineMovementCondition);
	}

	public Cars cars() {
		Collection<Car> cars = new ArrayList<>();
		for (Name name : artist.nameCollection()) {
			cars.add(Car.of(name, Engine.from(engineMovementCondition)));
		}
		return Cars.from(cars);
	}

	private void validate(MovementCondition<Rotation> engineMovementCondition) {
		if (engineMovementCondition == null) {
			throw new IllegalArgumentException("engine movement condition must not be null");
		}
	}

	private void validate(NamingArtist artist) {
		if (artist == null) {
			throw new IllegalArgumentException("naming artist must not be null");
		}
	}
}
