package racinggame.model.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("문자 구분자")
class StringSeparatorTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> StringSeparator.of("string", ","));
	}

	@Test
	@DisplayName("분할할 문자가 없는 객체화")
	void instance_emptyString_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> StringSeparator.of("", ","))
			.withMessageContaining("string to be split must not be empty");
	}

	@Test
	@DisplayName("구분자가 없는 객체화")
	void instance_nullDelimiter_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> StringSeparator.of("string", null))
			.withMessageContaining("delimiter must not be null");
	}

	@Test
	@DisplayName("분할된 문자 모음")
	void provide() {
		assertThat(StringSeparator.of("string", "").provide())
			.hasSize(6)
			.containsExactly("s", "t", "r", "i", "n", "g");
	}
}
