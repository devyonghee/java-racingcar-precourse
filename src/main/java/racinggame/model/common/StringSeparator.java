package racinggame.model.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class StringSeparator implements StringsProvider {

	private final String target;
	private final String delimiter;

	public StringSeparator(String target, String delimiter) {
		this.target = target;
		this.delimiter = delimiter;
	}

	@Override
	public Collection<String> provide() {
		return Collections.unmodifiableList(Arrays.asList(target.split(this.delimiter)));
	}
}
