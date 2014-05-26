package com.slepetit.utc.schedule.data;

import java.util.Set;
import java.util.TreeSet;

public class DaysData {
	public int day;
	public int uvCount;
	public Set<String> uvs = new TreeSet<>();
	
	public int[] slots = new int[48];
	
	public DaysData(int day) {
		this.day = day;
	}
	
	public void add(UserData userData) {
		for (SlotData s : userData.slots) {
			if (s.day == day) {
				if (! uvs.contains(s.uv)) {
					uvCount++;
					uvs.add(s.uv);
				}
				int beginSlot = Slot.getSlot(s.bh, s.bm);
				int endSlot = Slot.getSlot(s.eh, s.em);
				for (int i = beginSlot; i <= endSlot; i++) {
					slots[i]++;
				}
			}

		}
	}
}
