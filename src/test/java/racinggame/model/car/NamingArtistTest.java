package racinggame.model.car;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racinggame.model.common.StringLengthLimit;
import racinggame.model.common.StringsProvider;

@DisplayName("작명가")
class NamingArtistTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> NamingArtist.of(mock(StringsProvider.class), mock(StringLengthLimit.class)));
	}

	@Test
	@DisplayName("문자열 제공자가 비어있는 객체화")
	void instance_emptyStringProvider_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> NamingArtist.of(null, mock(StringLengthLimit.class)))
			.withMessageContaining("strings provider must not be null");
	}

	@Test
	@DisplayName("문자열 길이 제한이 없는 객체화")
	void instance_emptyStringLengthLimit_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> NamingArtist.of(mock(StringsProvider.class), null))
			.withMessageContaining("string length limit must not be null");
	}

	@Test
	@DisplayName("이름 모음")
	void nameCollection() {
		//given
		String string = "string";
		StringsProvider mockProvider = mock(StringsProvider.class);
		when(mockProvider.provide())
			.thenReturn(Collections.singleton(string));

		//when, then
		assertThat(NamingArtist.of(mockProvider, StringLengthLimit.from(10))
			.nameCollection())
			.extracting("string")
			.containsExactly(string);
	}

	@ParameterizedTest
	@DisplayName("비어있거나 제한보다 넘은 이름 모음")
	@CsvSource({",must not be empty", "TooLongName,too long"})
	void nameCollection_emptyOrLongLength_illegalArgumentExceptionThrown(String string, String expectedMessage) {
		//given
		StringsProvider mockProvider = mock(StringsProvider.class);
		when(mockProvider.provide())
			.thenReturn(Collections.singleton(string));

		assertThatIllegalArgumentException()
			.isThrownBy(() -> NamingArtist.of(mockProvider, StringLengthLimit.from(5))
				.nameCollection())
			.withMessageContaining(expectedMessage);
	}
}
