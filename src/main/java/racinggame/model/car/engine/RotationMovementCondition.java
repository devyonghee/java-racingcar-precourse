package racinggame.model.car.engine;

public final class RotationMovementCondition implements MovementCondition<Rotation> {

	private final Rotation standard;

	private RotationMovementCondition(Rotation standard) {
		validate(standard);
		this.standard = standard;
	}

	public static RotationMovementCondition from(Rotation rotation) {
		return new RotationMovementCondition(rotation);
	}

	/**
	 * {@link Rotation#equalOrLessThan(Rotation)} ()} 결과를 반환하는 메소드.
	 * <p>주어진 회전이 기준 회전보다 더 큰지 판단한다</p>
	 * @param rotation 판단할 대상
	 * @return {@link Rotation#equalOrLessThan(Rotation)} ()}
	 */
	public boolean enough(Rotation rotation) {
		return standard.equalOrLessThan(rotation);
	}

	private void validate(Rotation standard) {
		if (standard == null) {
			throw new IllegalArgumentException("standard rotation must not be null");
		}
	}
}
