package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("자동차들")
class CarsTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Cars.from(Collections.singleton(mock(Car.class))));
	}

	@Test
	@DisplayName("리스트가 비어있는 객체화")
	void instance_emptyCars_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Cars.from(Collections.emptyList()))
			.withMessageContaining("cars must not be empty");
	}

	@Test
	@DisplayName("콜렉션 반환")
	void collection() {
		//given
		Car mockCar = mock(Car.class);

		//when, then
		assertThat(Cars.from(Collections.singleton(mockCar))
			.collection())
			.containsExactly(mockCar);
	}
}
