package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("바퀴 수 제한")
class LapsLimitTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> LapsLimit.from(Laps.from(1)));
	}

	@Test
	@DisplayName("기준 바퀴 수가 없는 객체화")
	void instance_nullLaps_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> LapsLimit.from(null))
			.withMessageContaining("laps standard must not be null");
	}

	@ParameterizedTest
	@DisplayName("제한 확인")
	@CsvSource({"4,true", "5,false", "6,false"})
	void overThan(int count, boolean expected) {
		assertThat(LapsLimit.from(Laps.from(5))
			.isOk(Laps.from(count)))
			.isEqualTo(expected);
	}
}
