package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racinggame.model.car.engine.Engine;

@DisplayName("자동차")
class CarTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Car.of(mock(Name.class), mock(Engine.class)));
	}

	@Test
	@DisplayName("이름이 없는 자동차")
	void instance_nullName_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Car.of(null, mock(Engine.class)))
			.withMessageContaining("name must not be null");
	}

	@Test
	@DisplayName("엔진이 없는 자동차")
	void instance_nullEngine_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Car.of(mock(Name.class), null))
			.withMessageContaining("engine must not be null");
	}

	@Test
	@DisplayName("이름 반환")
	void name() {
		String nameString = "name";
		assertThat(Car.of(Name.from(nameString), mock(Engine.class)).name())
			.isEqualTo(Name.from(nameString));
	}

	@ParameterizedTest
	@DisplayName("움직임 유형 반환")
	@CsvSource({"true,GO", "false,STOP"})
	void move(boolean worked, MoveType expected) {
		//given
		Engine mockEngine = mock(Engine.class);
		when(mockEngine.worked()).thenReturn(worked);

		//when, then
		assertThat(Car.of(mock(Name.class), mockEngine).move())
			.isEqualTo(expected);
	}
}
