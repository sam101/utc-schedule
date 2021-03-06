package com.slepetit.utc.schedule.data;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DTO containing the data for a given room
 * @author Samuel Lepetit
 *
 */
public class RoomData {

	public String name;
	public int people;
	public int uniquePeople;
	@JsonIgnore
	public Set<String> students = new TreeSet<String>();

	public Set<String> uvs = new TreeSet<String>();
	public int uvCount; 

	public int[][] slots = new int[6][48];
	public int usedSlots;
	
	public String[][] uvSlots = new String[6][48];	
	
	public RoomData(String name) {
		this.name = name;
	}

	public void add(UserData userData) {
		people++;
		if (! students.contains(userData.login)) {
			uniquePeople++;
			students.add(userData.login);
		}
		for (SlotData s : userData.slots) {
			if (s.room.equals(name)) {
				if (! uvs.contains(s.uv)) {
					uvCount++; // TODO: a solution based on uvs.size() would be smarter.
					uvs.add(s.uv);
				}
				int beginSlot = Slot.getSlot(s.bh, s.bm);
				int endSlot = Slot.getSlot(s.eh, s.em);
				for (int i = beginSlot; i <= endSlot; i++) {
					if (slots[s.day][i] == 0) {
						usedSlots++;
					}
					uvSlots[s.day][i] = s.uv;
					slots[s.day][i]++;
				}
			}

		}
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[][] getSlots() {
		return slots;
	}

	public void setSlots(int[][] slots) {
		this.slots = slots;
	}
	
		

}
