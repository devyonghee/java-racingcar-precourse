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

	/**
	 * <p>위치들 기록 추가</p>
	 * @param locations 기록을 남길 위치들
	 */
	public void add(Locations locations) {
		this.locations.add(locations);
	}

	/**
	 * <p>현재 기록의 크기</p>
	 * @return 기록 갯수
	 */
	public int size() {
		return locations.size();
	}

	/**
	 * {@link Collections#unmodifiableCollection(Collection)} 결과를 반환하는 메서드
	 * <p>위치들 기록 컬렉션을 반환</p>
	 * <p>외부에서 리스트를 수정할 수 없도록 unmodifiable 반환</p>
	 * @return {@link Collections#unmodifiableCollection(Collection)}
	 */
	public Collection<Locations> collection() {
		return Collections.unmodifiableCollection(locations);
	}

	private void validate(Collection<Locations> locations) {
		if (locations == null) {
			throw new IllegalArgumentException("collection must not be null");
		}
	}
}
