package racinggame.model.car.engine;

import nextstep.utils.Randoms;

public final class Engine {

	private static final int MIN_ROTATION_COUNT = 0;
	private static final int MAX_ROTATION_COUNT = 9;

	private final MovementCondition<Rotation> condition;

	private Engine(MovementCondition<Rotation> condition) {
		validate(condition);
		this.condition = condition;
	}

	public static Engine from(MovementCondition<Rotation> condition) {
		return new Engine(condition);
	}

	/**
	 * {@link MovementCondition#enough(Object)} 결과를 반환하는 메소드.
	 * <p>엔진을 작동하기 위한 조건 충족 여부를 반환한다.</p>
	 *
	 * @return {@link MovementCondition#enough(Object)}
	 */
	public boolean worked() {
		return condition.enough(randomRotation());
	}

	private void validate(MovementCondition<Rotation> condition) {
		if (condition == null) {
			throw new IllegalArgumentException("movement condition must not be null");
		}
	}

	private Rotation randomRotation() {
		return Rotation.from(Randoms.pickNumberInRange(MIN_ROTATION_COUNT, MAX_ROTATION_COUNT));
	}
}
