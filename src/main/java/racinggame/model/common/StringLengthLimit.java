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

	/**
	 * <p>주어진 문자가 길이에 문제가 없는지 판단</p>
	 * @param target 길이 제한을 확인할 문자
	 * @return 길이 제한에 문제 없는지 여부
	 */
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
