package racinggame.view.dto;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.Locations;
import racinggame.model.LocationsHistory;

public final class Results {

	private final Collection<LocationsResponse> histories;

	private Results(Collection<LocationsResponse> histories) {
		this.histories = histories;
	}

	public static Results from(LocationsHistory history) {
		ArrayList<LocationsResponse> histories = new ArrayList<>();
		for (Locations locations : history.collection()) {
			histories.add(LocationsResponse.from(locations.collection()));
		}
		return new Results(histories);
	}

	public Collection<LocationsResponse> getHistories() {
		return histories;
	}
}
