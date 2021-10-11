package racinggame.model.car;

import java.util.Collection;
import java.util.Collections;

public final class Names {

	private final Collection<Name> names;

	private Names(Collection<Name> names) {
		validate(names);
		this.names = names;
	}

	public static Names from(Collection<Name> names) {
		return new Names(names);
	}

	public Collection<Name> collection() {
		return Collections.unmodifiableCollection(names);
	}

	private void validate(Collection<Name> names) {
		if (names == null || names.isEmpty()) {
			throw new IllegalArgumentException("names must not be empty");
		}
	}
}
