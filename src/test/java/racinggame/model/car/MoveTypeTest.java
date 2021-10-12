package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoveTypeTest {

	@ParameterizedTest
	@DisplayName("거리 반환")
	@CsvSource({"GO,1", "STOP,0"})
	void distance(MoveType type, int expected) {
		assertThat(type.distance())
			.isEqualTo(expected);
	}
}
