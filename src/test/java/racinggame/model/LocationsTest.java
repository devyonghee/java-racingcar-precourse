package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racinggame.model.car.Car;
import racinggame.model.car.Cars;
import racinggame.model.car.MoveType;

@DisplayName("위치들")
class LocationsTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Locations.from(Collections.singleton(mock(Location.class))));
	}

	@Test
	@DisplayName("비어있는 객체화")
	void instance_negativeCount_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Locations.from(Collections.emptyList()))
			.withMessageContaining("locations must not be empty");
	}

	@ParameterizedTest
	@DisplayName("움직인 다음 위치들")
	@CsvSource({"GO,4", "STOP,3"})
	void nextMoves(MoveType moveType, int expectedDistance) {
		//given
		Car mockCar = mock(Car.class);
		when(mockCar.move())
			.thenReturn(moveType);

		//when, then
		assertThat(Locations.from(Collections.singleton(Location.of(mockCar, Distance.from(3))))
			.nextMoves())
			.extracting(Locations::collection, ITERABLE)
			.extracting("distance")
			.containsExactly(Distance.from(expectedDistance));

	}

	@Test
	@DisplayName("가장 멀리 간 자동차들")
	void mostMovedCars() {
		//given
		Car mockCar1 = mock(Car.class);
		Car mockCar2 = mock(Car.class);
		Car mockCar3 = mock(Car.class);
		Car mockCar4 = mock(Car.class);

		//when, then
		assertThat(Locations.from(Arrays.asList(
				location(mockCar1, 1),
				location(mockCar2, 2),
				location(mockCar3, 3),
				location(mockCar4, 3)))
			.mostMovedCars())
			.extracting(Cars::collection, ITERABLE)
			.containsExactly(mockCar3, mockCar4);
	}

	@Test
	@DisplayName("콜렉션 반환")
	void collection() {
		//given
		Location mockLocation = mock(Location.class);

		//when, then
		assertThat(Locations.from(Collections.singleton(mockLocation))
			.collection())
			.containsExactly(mockLocation);
	}

	private Location location(Car car, int distance) {
		return Location.of(car, Distance.from(distance));
	}
}
