package racinggame.model;

import racinggame.model.common.Limit;

public final class LapsLimit implements Limit<Laps> {
	private final Laps standard;

	private LapsLimit(Laps standard) {
		validate(standard);
		this.standard = standard;
	}

	public static LapsLimit from(Laps laps) {
		return new LapsLimit(laps);
	}

	/**
	 * <p>주어진 바퀴수가 기준 제한에 문제 없는지 판단</p>
	 * @param laps 판단할 대상
	 * @return 제한 문제 여부
	 */
	@Override
	public boolean isOk(Laps laps) {
		return standard.overThan(laps);

	}

	private void validate(Laps standard) {
		if (standard == null) {
			throw new IllegalArgumentException("laps standard must not be null");
		}
	}
}
