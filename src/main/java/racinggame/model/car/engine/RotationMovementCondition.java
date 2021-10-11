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

	public boolean enough(Rotation rotation) {
		return standard.equalOrLessThan(rotation);
	}

	private void validate(Rotation standard) {
		if (standard == null) {
			throw new IllegalArgumentException("standard rotation must not be null");
		}
	}
}
