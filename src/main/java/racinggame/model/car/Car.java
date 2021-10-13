package racinggame.model.car;

import racinggame.model.car.engine.Engine;

public final class Car {

	private final Name name;
	private final Engine engine;

	private Car(Name name, Engine engine) {
		validate(name);
		validate(engine);
		this.name = name;
		this.engine = engine;
	}

	public static Car of(Name name, Engine engine) {
		return new Car(name, engine);
	}

	/**
	 * <p>자동차 이름을 반환한다.</p>
	 * @return 이름
	 */
	public Name name() {
		return name;
	}

	/**
	 * <p>엔진에 따라 자동차 움직임 유형을 반환한다.</p>
	 * @return 움직임 유형{@link MoveType}
	 */
	public MoveType move() {
		if (engine.worked()) {
			return MoveType.GO;
		}
		return MoveType.STOP;
	}

	private void validate(Name name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
	}

	private void validate(Engine engine) {
		if (engine == null) {
			throw new IllegalArgumentException("engine must not be null");
		}

	}
}
