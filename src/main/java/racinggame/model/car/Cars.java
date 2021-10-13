package racinggame.model.car;

import java.util.Collection;
import java.util.Collections;

public final class Cars {

	private final Collection<Car> cars;

	private Cars(Collection<Car> cars) {
		validate(cars);
		this.cars = cars;
	}

	public static Cars from(Collection<Car> cars) {
		return new Cars(cars);
	}

	/**
	 * {@link Collections#unmodifiableCollection(Collection)} 결과를 반환하는 메서드
	 * <p>자동차 컬렉션을 반환</p>
	 * <p>외부에서 리스트를 수정할 수 없도록 unmodifiable 반환</p>
	 * @return {@link Collections#unmodifiableCollection(Collection)}
	 */
	public Collection<Car> collection() {
		return Collections.unmodifiableCollection(cars);
	}

	private void validate(Collection<Car> cars) {
		if (cars == null || cars.isEmpty()) {
			throw new IllegalArgumentException("cars must not be empty");
		}
	}
}
