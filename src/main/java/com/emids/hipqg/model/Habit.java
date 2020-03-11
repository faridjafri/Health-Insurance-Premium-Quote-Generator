package com.emids.hipqg.model;

public enum Habit {
	DAILY_EXERCISE(HabitType.valueOf("GOOD")), SMOKING(HabitType.valueOf("BAD")), ALCOHOL_CONSUMPTION(
			HabitType.valueOf("BAD")), DRUGS(HabitType.valueOf("BAD"));
	private HabitType habitType;

	Habit(HabitType habitType) {
		this.habitType = habitType;
	}

	public HabitType getHabitType() {
		return this.habitType;
	}

	public enum HabitType {
		GOOD, BAD;
	}
}