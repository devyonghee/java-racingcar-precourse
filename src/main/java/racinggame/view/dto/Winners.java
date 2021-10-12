package racinggame.view.dto;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.car.Car;
import racinggame.model.car.Cars;

public final class Winners {

	Collection<String> names;

	private Winners(Collection<String> names) {
		this.names = names;
	}

	public static Winners from(Cars cars) {
		ArrayList<String> names = new ArrayList<>();
		for (Car car : cars.collection()) {
			names.add(car.name().string());
		}
		return new Winners(names);
	}

	public Collection<String> getNames() {
		return names;
	}
}
