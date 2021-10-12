package racinggame.view;

import java.io.PrintStream;

import racinggame.view.dto.LocationResponse;
import racinggame.view.dto.LocationsResponse;
import racinggame.view.dto.Results;
import racinggame.view.dto.Winners;

public final class Billboard {

	private static final String RESULTS_GUIDE = "실행 결과";
	private static final String LOCATION_FORMAT = "%s : %s";
	private static final Character DISTANCE_CHARACTER = '-';
	private static final String WINNER_FORMAT = "최종 우승자는 %s 입니다.";
	private static final String WINNERS_DELIMITER = ",";

	private final PrintStream printer;

	public Billboard(PrintStream printer) {
		this.printer = printer;
	}

	public void exposure(Results history) {
		printer.println();
		this.printer.println(RESULTS_GUIDE);
		for (LocationsResponse locations : history.getHistories()) {
			exposure(locations);
			this.printer.println();
		}
	}

	public void exposure(Winners winners) {
		printer.printf(WINNER_FORMAT, String.join(WINNERS_DELIMITER, winners.getNames()));
		printer.println();
	}

	private void exposure(LocationsResponse locations) {
		for (LocationResponse location : locations.getLocations()) {
			exposure(location);
		}
	}

	private void exposure(LocationResponse location) {
		this.printer.printf(LOCATION_FORMAT, location.getName(), repeatDistanceCharacter(location.getPoint()));
		printer.println();
	}

	private String repeatDistanceCharacter(int point) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < point; i++) {
			builder.append(DISTANCE_CHARACTER);
		}
		return builder.toString();
	}

}
