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

	/**
	 * <p>현재 객체가 대상보다 같거나 작은지 판단한다.</p>
	 * @param rotation 비교할 대상
	 * @return 대상보다 같거나 작은지 여부
	 */
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
