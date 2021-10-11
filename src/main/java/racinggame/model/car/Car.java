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

	public Name name() {
		return name;
	}

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
