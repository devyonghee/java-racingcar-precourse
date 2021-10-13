package racinggame.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

import racinggame.model.car.Car;
import racinggame.model.car.Cars;

public final class Locations {

	private final Collection<Location> locations;

	private Locations(Collection<Location> locations) {
		validate(locations);
		this.locations = locations;
	}

	public static Locations from(Collection<Location> locations) {
		return new Locations(locations);
	}

	/**
	 * <p>움직인 다음 위치들을 반환</p>
	 * @return 움직인 위치들
	 */
	public Locations nextMoves() {
		Collection<Location> newLocations = new ArrayList<>();
		for (Location location : locations) {
			newLocations.add(location.nextMove());
		}
		return new Locations(newLocations);
	}

	/**
	 * <p>가장 멀리간 자동차들 반환</p>
	 * @return 멀리간 자동차들
	 */
	public Cars mostMovedCars() {
		return farthestLocations()
			.cars();
	}

	/**
	 * {@link Collections#unmodifiableCollection(Collection)} 결과를 반환하는 메서드
	 * <p>위치 컬렉션을 반환</p>
	 * <p>외부에서 리스트를 수정할 수 없도록 unmodifiable 반환</p>
	 * @return {@link Collections#unmodifiableCollection(Collection)}
	 */
	public Collection<Location> collection() {
		return Collections.unmodifiableCollection(locations);
	}

	private Locations farthestLocations() {
		LinkedHashSet<Location> locations = new LinkedHashSet<>();
		locations.add(farthestLocation());

		Locations farthestLocations = new Locations(locations);
		for (Location location : this.locations) {
			farthestLocations.addOnlySameDistance(location);
		}
		return farthestLocations;
	}

	private Cars cars() {
		Collection<Car> cars = new ArrayList<>();
		for (Location location : this.locations) {
			cars.add(location.car());
		}
		return Cars.from(cars);
	}

	private void addOnlySameDistance(Location location) {
		if (locations.iterator().next().equalDistance(location)) {
			locations.add(location);
		}
	}

	private void validate(Collection<Location> locations) {
		if (locations == null || locations.isEmpty()) {
			throw new IllegalArgumentException("locations must not be empty");
		}
	}

	private Location farthestLocation() {
		Iterator<Location> iterator = locations.iterator();
		Location farthestLocation = iterator.next();
		while (iterator.hasNext()) {
			farthestLocation = farthestLocation.fartherDistance(iterator.next());
		}
		return farthestLocation;
	}
}
