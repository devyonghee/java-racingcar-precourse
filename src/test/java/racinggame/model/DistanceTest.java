package racinggame.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("거리")
class DistanceTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException().isThrownBy(() -> Distance.from(0));
	}

	@Test
	@DisplayName("음수일 경우 객체화")
	void instance_negativeInit_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException().isThrownBy(() -> Distance.from(-1))
			.withMessageContaining("distance must be positive");
	}

	@Test
	@DisplayName("값 반환")
	void value() {
		int init = 1;
		assertThat(Distance.from(init).value()).isEqualTo(init);
	}

	@Test
	@DisplayName("추가")
	void add() {
		assertThat(Distance.from(5).add(Distance.from(5)))
			.extracting(Distance::value)
			.isEqualTo(10);
	}

	@ParameterizedTest
	@DisplayName("같거나 큰지 확인")
	@CsvSource({"4,true", "5,true", "6,false"})
	void equalOrMoreThan(int distance, boolean expected) {
		assertThat(Distance.from(5)
			.equalOrMoreThan(Distance.from(distance)))
			.isEqualTo(expected);
	}
}
