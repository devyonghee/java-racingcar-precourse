package racinggame.model.car;

import java.util.Objects;

public final class Name {

	private final String string;

	private Name(String string) {
		validate(string);
		this.string = string;
	}

	public static Name from(String string) {
		return new Name(string);
	}

	public String string() {
		return string;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Name name = (Name)o;
		return Objects.equals(string, name.string);
	}

	@Override
	public int hashCode() {
		return Objects.hash(string);
	}

	private void validate(String string) {
		if (string == null || "".equals(string.trim())) {
			throw new IllegalArgumentException("name string must not be empty");
		}
	}
}
