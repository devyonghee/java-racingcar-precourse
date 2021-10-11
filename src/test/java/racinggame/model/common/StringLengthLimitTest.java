package racinggame.model.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("문자열 길이 제한")
class StringLengthLimitTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> StringLengthLimit.from(1));
	}

	@Test
	@DisplayName("음수일 경우 객체화")
	void instance_negativeInit_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException().isThrownBy(() -> StringLengthLimit.from(-1))
			.withMessageContaining("length limit must be positive");
	}

	@Test
	@DisplayName("길이 제한 반환")
	void length() {
		//given
		int lengthLimit = 10;

		//when, then
		assertThat(StringLengthLimit.from(lengthLimit).length())
			.isEqualTo(lengthLimit);
	}

	@ParameterizedTest
	@DisplayName("제한 확인")
	@CsvSource({"name,true", "longName,false"})
	void isOk(String target, boolean expected) {
		assertThat(StringLengthLimit.from(5).isOk(target))
			.isEqualTo(expected);
	}
}
