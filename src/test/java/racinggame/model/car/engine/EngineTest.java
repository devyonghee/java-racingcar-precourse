package racinggame.model.car.engine;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;

@DisplayName("엔진")
class EngineTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Engine.from(mock(RotationMovementCondition.class)));
	}

	@Test
	@DisplayName("움직임 조건이 없는 객체화")
	void instance_nullMovementCondition_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Engine.from(null))
			.withMessageContaining("movement condition must not be null");
	}

	@ParameterizedTest
	@DisplayName("작동 여부")
	@CsvSource({"4,false", "5,true", "6,true"})
	void worked(int randomNumber, boolean expected) {
		try (MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(randomNumber);

			assertThat(Engine.from(RotationMovementCondition.from(Rotation.from(5))).worked())
				.isEqualTo(expected);
		}

	}
}
