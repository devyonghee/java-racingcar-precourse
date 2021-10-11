package racinggame.model.car;

import java.util.Collection;
import java.util.Collections;

public final class Cars {

	private final Collection<Car> cars;

	private Cars(Collection<Car> cars) {
		validate(cars);
		this.cars = cars;
	}

	public static Cars from(Collection<Car> cars) {
		return new Cars(cars);
	}

	public Collection<Car> collection() {
		return Collections.unmodifiableCollection(cars);
	}

	private void validate(Collection<Car> cars) {
		if (cars == null || cars.isEmpty()) {
			throw new IllegalArgumentException("cars must not be empty");
		}
	}
}
