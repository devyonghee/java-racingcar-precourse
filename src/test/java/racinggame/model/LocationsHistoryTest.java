package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("위치들 기록")
class LocationsHistoryTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> LocationsHistory.from(new ArrayList<>()));
	}

	@Test
	@DisplayName("콜렉션이 없는 없는 객체화")
	void instance_nullCollection_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> LocationsHistory.from(null))
			.withMessageContaining("collection must not be null");
	}

	@Test
	@DisplayName("추가")
	void add() {
		//given
		Locations mockLocations1 = mock(Locations.class);
		Locations mockLocations2 = mock(Locations.class);
		LocationsHistory history = LocationsHistory.from(new ArrayList<>());

		//when
		history.add(mockLocations1);
		history.add(mockLocations2);

		//then
		assertThat(history)
			.extracting(LocationsHistory::collection, ITERABLE)
			.containsExactly(mockLocations1, mockLocations2);
	}

	@Test
	@DisplayName("기록 수")
	void size() {
		assertThat(LocationsHistory.from(Arrays.asList(mock(Locations.class), mock(Locations.class)))
			.size())
			.isEqualTo(2);
	}

	@Test
	@DisplayName("콜렉션 반환")
	void collection() {
		//given
		Locations mockLocations = mock(Locations.class);

		//when, then
		assertThat(LocationsHistory.from(Collections.singleton(mockLocations))
			.collection())
			.containsExactly(mockLocations);
	}
}
