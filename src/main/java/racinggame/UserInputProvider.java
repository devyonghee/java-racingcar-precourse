package racinggame;

import nextstep.utils.Console;

public final class UserInputProvider {

	private final GuidePrinter guidePrinter;

	private UserInputProvider(GuidePrinter guidePrinter) {
		validate(guidePrinter);
		this.guidePrinter = guidePrinter;
	}

	public static UserInputProvider from(GuidePrinter guidePrinter) {
		return new UserInputProvider(guidePrinter);
	}

	public String provide() {
		guidePrinter.print();
		return Console.readLine();
	}

	private void validate(GuidePrinter guidePrinter) {
		if (guidePrinter == null) {
			throw new IllegalArgumentException("guide printer must not be null");
		}
	}
}
