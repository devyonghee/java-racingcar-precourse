package racinggame.model.common;

public final class StringLengthLimit implements Limit<String> {

	private final int length;

	private StringLengthLimit(int length) {
		validate(length);
		this.length = length;
	}

	public static StringLengthLimit from(int length) {
		return new StringLengthLimit(length);
	}

	public int length() {
		return length;
	}

	@Override
	public boolean isOk(String target) {
		return target.length() <= length;
	}

	private void validate(int length) {
		if (length < 0) {
			throw new IllegalArgumentException(
				String.format("length limit must be positive, but provided %d", length));
		}
	}
}
