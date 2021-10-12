package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racinggame.model.car.Car;
import racinggame.model.car.MoveType;

@DisplayName("위치")
class LocationTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Location.of(mock(Car.class), mock(Distance.class)));
	}

	@Test
	@DisplayName("자동차가 없는 객체화")
	void instance_nullCar_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Location.of(null, mock(Distance.class)))
			.withMessageContaining("car must not be null");
	}

	@Test
	@DisplayName("거리가 없는 객체화")
	void instance_nullDistance_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Location.of(mock(Car.class), null))
			.withMessageContaining("distance must not be null");
	}

	@ParameterizedTest
	@DisplayName("다음 위치")
	@CsvSource({"GO,6", "STOP,5"})
	void nextMove(MoveType moveType, int expectedDistance) {
		//given
		Car mockCar = mock(Car.class);
		when(mockCar.move())
			.thenReturn(moveType);

		//when, then
		assertThat(Location.of(mockCar, Distance.from(5))
			.nextMove())
			.extracting(Location::distance)
			.isEqualTo(Distance.from(expectedDistance));
	}

	@Test
	@DisplayName("거리 반환")
	void distance() {
		//given
		Distance mockDistance = mock(Distance.class);

		//when, then
		assertThat(Location.of(mock(Car.class), mockDistance))
			.extracting(Location::distance)
			.isEqualTo(mockDistance);
	}

	@Test
	@DisplayName("자동차 반환")
	void car() {
		//given
		Car mockCar = mock(Car.class);

		//when, then
		assertThat(Location.of(mockCar, mock(Distance.class)))
			.extracting(Location::car)
			.isEqualTo(mockCar);
	}

	@Test
	@DisplayName("더 멀리간 거리 위치")
	void fartherDistance() {
		//given
		Car mockCar = mock(Car.class);
		Location fartherDistance = Location.of(mockCar, Distance.from(10));

		//when, then
		assertThat(Location.of(mockCar, Distance.from(1))
			.fartherDistance(fartherDistance))
			.isEqualTo(fartherDistance);
	}

	@ParameterizedTest
	@DisplayName("거리 동일 여부")
	@CsvSource({"1,false", "2,false", "5,true"})
	void equalDistance(int distance, boolean expected) {
		assertThat(Location.of(mock(Car.class), Distance.from(5))
			.equalDistance(Location.of(mock(Car.class), Distance.from(distance))))
			.isEqualTo(expected);
	}
}
