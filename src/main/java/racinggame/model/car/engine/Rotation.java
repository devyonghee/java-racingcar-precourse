package racinggame.model.car.engine;

public final class Rotation {

	private final int count;

	private Rotation(int count) {
		validate(count);
		this.count = count;
	}

	public static Rotation from(int count) {
		return new Rotation(count);
	}

	boolean equalOrLessThan(Rotation rotation) {
		return count <= rotation.count;
	}

	private void validate(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(
				String.format("rotation count must be positive, but provided %d", count));
		}
	}
}
