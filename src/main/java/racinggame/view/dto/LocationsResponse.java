package racinggame.view.dto;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.Location;

public final class LocationsResponse {

	private final Collection<LocationResponse> locations;

	private LocationsResponse(Collection<LocationResponse> locations) {
		this.locations = locations;
	}

	public static LocationsResponse from(Collection<Location> locations) {
		ArrayList<LocationResponse> responses = new ArrayList<>();
		for (Location location : locations) {
			responses.add(LocationResponse.from(location.car().name().string(), location.distance().value()));
		}
		return new LocationsResponse(responses);
	}

	public Collection<LocationResponse> getLocations() {
		return locations;
	}
}
