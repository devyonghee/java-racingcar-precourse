package racinggame.model.car.engine;

public interface MovementCondition<T> {

	/**
	 * <p>주어진 객체가 조건이 충족한지 판단한다.</p>
	 * @param target 조건이 충족한지 판단할 대상
	 * @return 조건 충족 여부
	 */
	boolean enough(T target);
}
