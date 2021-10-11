package racinggame.model.car.engine;

public interface MovementCondition<T> {

	boolean enough(T number);
}
