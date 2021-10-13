package racinggame.model.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class StringSeparator implements StringsProvider {

	private final String target;
	private final String delimiter;

	private StringSeparator(String target, String delimiter) {
		validateTarget(target);
		validateDelimiter(delimiter);
		this.target = target;
		this.delimiter = delimiter;
	}

	public static StringSeparator of(String target, String delimiter) {
		return new StringSeparator(target, delimiter);
	}

	/**
	 * <p>주어진 문자를 구분자를 통해 나눠서 제공</p>
	 * @return 나눠진 문자열
	 */
	@Override
	public Collection<String> provide() {
		return Collections.unmodifiableList(Arrays.asList(target.split(this.delimiter)));
	}

	private void validateDelimiter(String delimiter) {
		if (delimiter == null) {
			throw new IllegalArgumentException("delimiter must not be null");
		}
	}

	private void validateTarget(String target) {
		if (target == null || "".equals(target.trim())) {
			throw new IllegalArgumentException("string to be split must not be empty");
		}
	}
}
