package com.slepetit.utc.schedule.data;

public class Slot {

	public static int getSlot(int h, int m) {
		return (h - 8) * 4 + m / 15;
	}
}
