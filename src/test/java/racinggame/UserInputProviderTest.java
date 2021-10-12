package racinggame;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import nextstep.utils.Console;

@DisplayName("사용자 입력")
class UserInputProviderTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> UserInputProvider.from(mock(GuidePrinter.class)));
	}

	@Test
	@DisplayName("안내 프린터가 없는 객체화")
	void instance_nullGuidePrinter_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> UserInputProvider.from(null))
			.withMessageContaining("guide printer must not be null");
	}

	@Test
	@DisplayName("입력 제공")
	void provide() {
		try (MockedStatic<Console> mockConsole = mockStatic(Console.class)) {
			//given
			String input = "input";

			//when
			mockConsole.when(Console::readLine)
				.thenReturn(input);

			//then
			assertThat(UserInputProvider.from(mock(GuidePrinter.class)).provide()).isEqualTo(input);
		}

	}
}
