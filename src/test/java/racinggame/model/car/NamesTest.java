package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이름들")
class NamesTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Names.from(Collections.singleton(mock(Name.class))));
	}

	@Test
	@DisplayName("리스트가 비어있는 객체화")
	void instance_emptyNames_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Names.from(Collections.emptyList()))
			.withMessageContaining("names must not be empty");
	}

	@Test
	@DisplayName("콜렉션 반환")
	void collection() {
		//given
		Name name = Name.from("name");

		//when, then
		assertThat(Names.from(Collections.singleton(name))
			.collection())
			.containsExactly(name);
	}
}
