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

	/**
	 * {@link Collections#unmodifiableCollection(Collection)} 결과를 반환하는 메서드
	 * <p>이름 컬렉션을 반환</p>
	 * <p>외부에서 리스트를 수정할 수 없도록 unmodifiable 반환</p>
	 * @return {@link Collections#unmodifiableCollection(Collection)}
	 */
	public Collection<Name> collection() {
		return Collections.unmodifiableCollection(names);
	}

	private void validate(Collection<Name> names) {
		if (names == null || names.isEmpty()) {
			throw new IllegalArgumentException("names must not be empty");
		}
	}
}
