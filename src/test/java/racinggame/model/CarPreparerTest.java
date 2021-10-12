package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racinggame.model.car.Car;
import racinggame.model.car.Cars;

@DisplayName("자동차 준비자")
class CarPreparerTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> CarPreparer.from(Distance.from(0)));
	}

	@Test
	@DisplayName("초기 위치가 없는 객체화")
	void instance_nullStartingLine_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> CarPreparer.from(null))
			.withMessageContaining("starting line must not be null");
	}

	@Test
	@DisplayName("자동차들 준비")
	void move() {
		Distance startingLine = Distance.from(0);
		assertThat(CarPreparer.from(startingLine)
			.prepare(Cars.from(Collections.singleton(mock(Car.class)))))
			.extracting(Locations::collection, InstanceOfAssertFactories.ITERABLE)
			.extracting("distance")
			.containsExactly(startingLine);
	}
}
