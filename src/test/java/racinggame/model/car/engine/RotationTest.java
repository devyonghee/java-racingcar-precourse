package racinggame.model.car.engine;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("회전")
class RotationTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException().isThrownBy(() -> Rotation.from(1));
	}

	@Test
	@DisplayName("음수일 경우 객체화")
	void instance_negativeInit_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException().isThrownBy(() -> Rotation.from(-1))
			.withMessageContaining("rotation count must be positive");
	}

	@ParameterizedTest
	@DisplayName("같거나 큰지 확인")
	@CsvSource({"4,false", "5,true", "6,true"})
	void equalOrLessThan(int count, boolean expected) {
		assertThat(Rotation.from(5)
			.equalOrLessThan(Rotation.from(count)))
			.isEqualTo(expected);
	}
}
