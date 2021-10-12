package racinggame.model;

import java.util.Collection;
import java.util.Collections;

public final class LocationsHistory {

	private final Collection<Locations> locations;

	private LocationsHistory(Collection<Locations> locations) {
		validate(locations);
		this.locations = locations;
	}

	public static LocationsHistory from(Collection<Locations> locations) {
		return new LocationsHistory(locations);
	}

	public void add(Locations locations) {
		this.locations.add(locations);
	}

	public int length() {
		return locations.size();
	}

	public Collection<Locations> collection() {
		return Collections.unmodifiableCollection(locations);
	}

	private void validate(Collection<Locations> locations) {
		if (locations == null) {
			throw new IllegalArgumentException("collection must not be null");
		}
	}
}
