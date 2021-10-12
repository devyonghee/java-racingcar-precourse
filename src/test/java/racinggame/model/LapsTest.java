package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("바퀴 수")
class LapsTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Laps.from(1));
	}

	@Test
	@DisplayName("음수 객체화")
	void instance_negativeCount_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Laps.from(-1))
			.withMessageContaining("laps count must be greater than zero");
	}

	@ParameterizedTest
	@DisplayName("더 큰지 여부")
	@CsvSource({"4,true", "5,false", "6,false"})
	void overThan(int count, boolean expected) {
		assertThat(Laps.from(5)
			.overThan(Laps.from(count)))
			.isEqualTo(expected);
	}
}
