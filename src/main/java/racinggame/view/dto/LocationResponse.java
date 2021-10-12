package racinggame.view.dto;

public final class LocationResponse {
	private final String name;
	private final int point;

	private LocationResponse(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public static LocationResponse from(String name, int point) {
		return new LocationResponse(name, point);
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}
}
