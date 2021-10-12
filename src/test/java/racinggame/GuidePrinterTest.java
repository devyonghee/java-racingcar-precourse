package racinggame;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("안내 문구 프린터")
class GuidePrinterTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> GuidePrinter.of(mock(PrintStream.class), "guide"));
	}

	@Test
	@DisplayName("프린터가 없는 객체화")
	void instance_nullPrinter_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> GuidePrinter.of(null, "guide"))
			.withMessageContaining("printer must not be null");
	}

	@Test
	@DisplayName("안내 문구가 비어있는 객체화")
	void instance_nullLaps_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> GuidePrinter.of(mock(PrintStream.class), ""))
			.withMessageContaining("guide must not be empty");
	}

	@Test
	@DisplayName("출력")
	void print() {
		//given
		PrintStream mockPrintStream = mock(PrintStream.class);
		String guide = "guide";

		//when
		GuidePrinter.of(mockPrintStream, guide).print();

		//then
		verify(mockPrintStream, only()).println(eq(guide));
	}
}
