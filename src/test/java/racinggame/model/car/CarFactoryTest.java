package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racinggame.model.car.engine.MovementCondition;
import racinggame.model.car.engine.Rotation;
import racinggame.model.car.engine.RotationMovementCondition;

@DisplayName("자동차 공장")
class CarFactoryTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> CarFactory.of(mock(NamingArtist.class), mock(RotationMovementCondition.class)));
	}

	@Test
	@DisplayName("작명가가 없는 객체화")
	void instance_nullNamingArtist_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> CarFactory.of(null, mock(RotationMovementCondition.class)))
			.withMessageContaining("naming artist must not be null");
	}

	@Test
	@DisplayName("리스트가 비어있는 객체화")
	void instance_nullEngineMovementCondition_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> CarFactory.of(mock(NamingArtist.class), null))
			.withMessageContaining("engine movement condition must not be null");
	}

	@Test
	void cars() {
		//given
		Name name = Name.from("name");
		NamingArtist mockArtist = mock(NamingArtist.class);
		when(mockArtist.nameCollection())
			.thenReturn(Collections.singleton(name));

		MovementCondition<Rotation> mockMovementCondition = mock(RotationMovementCondition.class);

		//when, then
		assertThat(CarFactory.of(mockArtist, mockMovementCondition)
			.cars())
			.extracting(Cars::collection, InstanceOfAssertFactories.ITERABLE)
			.extracting("name", "engine.condition")
			.containsExactly(tuple(name, mockMovementCondition));
	}
}
