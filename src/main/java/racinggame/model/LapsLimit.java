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
