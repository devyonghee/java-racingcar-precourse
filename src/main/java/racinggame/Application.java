package racinggame;

import java.util.function.Supplier;

import racinggame.controller.RacingStadium;
import racinggame.model.Laps;
import racinggame.model.LapsLimit;
import racinggame.model.car.CarFactory;
import racinggame.model.car.Cars;
import racinggame.model.car.NamingArtist;
import racinggame.model.car.engine.Rotation;
import racinggame.model.car.engine.RotationMovementCondition;
import racinggame.model.common.Limit;
import racinggame.model.common.StringLengthLimit;
import racinggame.model.common.StringSeparator;
import racinggame.view.Billboard;

public class Application {

	private static final String NAME_DELIMITER = ",";
	private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s";
	private static final String INPUT_CAR_GUIDE_FORMAT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(%s) 기준으로 구분)";
	private static final String INPUT_LAPS_COUNT_GUIDE = "시도할 회수는 몇회인가요?";
	private static final int MINIMUM_FOR_MOVE_FORWARD = 4;
	private static final int CAR_NAME_LENGTH_LIMIT = 5;

	public static void main(String[] args) {
		RacingStadium.of(
				tryUntilValid(cars()),
				tryUntilValid(lapsLimit()),
				Billboard.from(System.out))
			.play();
	}

	private static <T> T tryUntilValid(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (IllegalArgumentException e) {
			System.out.println(String.format(ERROR_MESSAGE_FORMAT, e.getMessage()));
			return tryUntilValid(supplier);
		}

	}

	private static Supplier<Limit<Laps>> lapsLimit() {
		return () -> LapsLimit.from(Laps.from(Integer.parseInt(inputString(INPUT_LAPS_COUNT_GUIDE))));
	}

	private static Supplier<Cars> cars() {
		return () -> CarFactory.of(
				NamingArtist.of(
					StringSeparator.of(
						inputString(String.format(INPUT_CAR_GUIDE_FORMAT, NAME_DELIMITER)),
						NAME_DELIMITER),
					StringLengthLimit.from(CAR_NAME_LENGTH_LIMIT)),
				RotationMovementCondition.from(Rotation.from(MINIMUM_FOR_MOVE_FORWARD)))
			.cars();
	}

	private static String inputString(String guide) {
		return UserInputProvider.from(
				GuidePrinter.of(System.out, guide))
			.provide();
	}
}
