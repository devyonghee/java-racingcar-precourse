package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이름")
class NameTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Name.from("name"));
	}

	@Test
	@DisplayName("공백 객체화")
	void instance_emptyString_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Name.from(""))
			.withMessageContaining("name string must not be empty");
	}

	@Test
	@DisplayName("값 반환")
	void string() {
		//given
		String string = "name";

		//when, then
		assertThat(Name.from(string).string())
			.isEqualTo(string);
	}
}
