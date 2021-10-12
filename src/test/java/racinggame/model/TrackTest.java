package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racinggame.model.car.Cars;

@DisplayName("트랙")
class TrackTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Track.of(mock(Locations.class), tenLapsLimit()));
	}

	@Test
	@DisplayName("위치가 비어있는 객체화")
	void instance_nullLocations_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Track.of(null, tenLapsLimit()))
			.withMessageContaining("locations must not be null");
	}

	@Test
	@DisplayName("바퀴 수 제한이 없는 객체화")
	void instance_nullLapsLimit_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Track.of(mock(Locations.class), null))
			.withMessageContaining("laps limit must not be null");
	}

	@Test
	@DisplayName("진행")
	void go() {
		//given
		Locations firstLocations = mock(Locations.class);
		Locations secondLocations = mock(Locations.class);
		when(firstLocations.nextMoves())
			.thenReturn(secondLocations);

		Track track = Track.of(firstLocations, tenLapsLimit());

		//when
		track.go();

		//then
		assertThat(track)
			.extracting("locations")
			.isEqualTo(secondLocations);
	}

	@Test
	@DisplayName("제한 수가 넘긴 진행")
	void go_exceededLimit_illegalStateExceptionThrown() {
		//given
		Locations mock = mock(Locations.class);
		when(mock.nextMoves())
			.thenReturn(mock);
		Track track = Track.of(mock, tenLapsLimit());

		//when, then
		assertThatIllegalStateException()
			.isThrownBy(() -> {
				for (int i = 0; i < 11; i++) {
					track.go();
				}
			}).withMessageContaining("can`t go any more");
	}

	@ParameterizedTest
	@CsvSource({"8,true", "9,true", "10,false"})
	@DisplayName("진행 가능 여부")
	void isGreenLight(int times, boolean expected) {
		//given
		Locations mock = mock(Locations.class);
		when(mock.nextMoves())
			.thenReturn(mock);
		Track track = Track.of(mock, tenLapsLimit());

		//when
		for (int i = 0; i < times; i++) {
			track.go();
		}

		//then
		assertThat(track.isGreenLight())
			.isEqualTo(expected);
	}

	@Test
	@DisplayName("기록")
	void history() {
		//given
		Locations firstLocations = mock(Locations.class);
		Locations secondLocations = mock(Locations.class);
		when(firstLocations.nextMoves())
			.thenReturn(secondLocations);

		Track track = Track.of(firstLocations, tenLapsLimit());

		//when
		track.go();

		//then
		assertThat(track)
			.extracting(Track::history)
			.extracting(LocationsHistory::collection, ITERABLE)
			.containsExactly(secondLocations);
	}

	@Test
	void mostMovedCars() {
		//given
		Cars cars = mock(Cars.class);
		Locations mockLocations = mock(Locations.class);
		when(mockLocations.mostMoves())
			.thenReturn(cars);

		//when, then
		assertThat(Track.of(mockLocations, tenLapsLimit())
			.mostMovedCars())
			.isEqualTo(cars);
	}

	private LapsLimit tenLapsLimit() {
		return LapsLimit.from(Laps.from(10));
	}
}
