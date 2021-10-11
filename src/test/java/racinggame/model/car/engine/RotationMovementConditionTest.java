package racinggame.model.car.engine;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("회전 움직임 조건")
class RotationMovementConditionTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> RotationMovementCondition.from(Rotation.from(3)));
	}

	@Test
	@DisplayName("회전 기준이 없는 객체화")
	void instance_nullRotation_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> RotationMovementCondition.from(null))
			.withMessageContaining("standard rotation must not be null");
	}

	@ParameterizedTest
	@DisplayName("충분한지 판단")
	@CsvSource({"4,false", "5,true", "6,true"})
	void enough(int rotationCount, boolean expected) {
		assertThat(RotationMovementCondition.from(Rotation.from(5))
			.enough(Rotation.from(rotationCount)))
			.isEqualTo(expected);
	}
}
