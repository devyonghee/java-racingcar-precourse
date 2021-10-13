package racinggame.model.car;

import java.util.ArrayList;
import java.util.Collection;

import racinggame.model.common.StringLengthLimit;
import racinggame.model.common.StringsProvider;

public final class NamingArtist {

	private final StringsProvider provider;
	private final StringLengthLimit lengthLimit;
	private Names cacheNames;

	private NamingArtist(StringsProvider provider, StringLengthLimit lengthLimit) {
		validate(provider);
		validate(lengthLimit);
		this.provider = provider;
		this.lengthLimit = lengthLimit;
	}

	public static NamingArtist of(StringsProvider provider, StringLengthLimit lengthLimit) {
		return new NamingArtist(provider, lengthLimit);
	}

	/**
	 * {@link Names#collection()} 결과를 반환하는 메서드
	 * <p>이름 컬렉션을 반환한다.</p>
	 * @return {@link Names#collection()}
	 */
	public Collection<Name> nameCollection() {
		if (hasCache()) {
			return cacheNames.collection();
		}
		cacheNames = names();
		return cacheNames.collection();
	}

	private void validate(StringsProvider provider) {
		if (provider == null) {
			throw new IllegalArgumentException("strings provider must not be null");
		}
	}

	private void validate(StringLengthLimit lengthLimit) {
		if (lengthLimit == null) {
			throw new IllegalArgumentException("string length limit must not be null");
		}
	}

	private boolean hasCache() {
		return cacheNames != null;
	}

	private Names names() {
		Collection<Name> names = new ArrayList<>();
		for (String string : provider.provide()) {
			validate(string);
			names.add(Name.from(string));
		}
		return Names.from(names);
	}

	private void validate(String string) {
		if (string == null || "".equals(string.trim())) {
			throw new IllegalArgumentException("name string must not be empty");
		}

		if (!lengthLimit.isOk(string)) {
			throw new IllegalArgumentException(
				String.format("must be provided up to %d length, '%s' is too long", lengthLimit.length(), string));
		}
	}
}
