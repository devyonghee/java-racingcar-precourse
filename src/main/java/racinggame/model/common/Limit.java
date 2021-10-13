package racinggame.model.common;

public interface Limit<T> {

	/**
	 * <p>주어진 타입이 제한 조건에 괜찮은지 판단</p>
	 * @return 제한에 문제 없는지 여부
	 */
	boolean isOk(T number);

}
