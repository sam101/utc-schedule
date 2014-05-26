package com.slepetit.utc.schedule.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ScheduleData {

	private List<UserData> users = new ArrayList<UserData>();	
	private Map<String, UVData> uvs = new TreeMap<>();
	private Map<String, RoomData> rooms = new TreeMap<>();
	private Map<String, Integer> semesters = new TreeMap<>(); // TODO: We should use a Multiset here.
	private DaysData[] days = new DaysData[6];

	public ScheduleData() {
		for (int i = 0; i < days.length; i++) {
			days[i] = new DaysData(i);
		}
	}
	
	
	public void add(UserData userData) {
		users.add(userData);
		if (! semesters.containsKey(userData.fullSemester)) {
			semesters.put(userData.fullSemester, 1);
		}
		semesters.put(userData.fullSemester, semesters.get(userData.fullSemester) + 1);
		extractUVData(userData);
		extractRoomData(userData);
		
		for (int i = 0; i < days.length; i++) {
			days[i].add(userData);
		}
	}

	private void extractUVData(UserData userData) {
		// Handle UV data
		for (String uv : userData.uvs) {
			if (! uvs.containsKey(uv)) {
				uvs.put(uv,  new UVData(uv));
			}
			uvs.get(uv).add(userData);
		}
	}
	
	private void extractRoomData(UserData userData) {
		for (SlotData s : userData.slots) {
			if (! rooms.containsKey(s.room)) {
				rooms.put(s.room, new RoomData(s.room));
			}
			rooms.get(s.room).add(userData);
		}
	}
	
	

	public DaysData[] getDays() {
		return days;
	}

	public void setDays(DaysData[] days) {
		this.days = days;
	}

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}
	

	public Map<String, RoomData> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, RoomData> rooms) {
		this.rooms = rooms;
	}

	public Map<String, Integer> getSemesters() {
		return semesters;
	}


	public void setSemesters(Map<String, Integer> semesters) {
		this.semesters = semesters;
	}


	public Map<String, UVData> getUvs() {
		return uvs;
	}

	public void setUvs(Map<String, UVData> uvs) {
		this.uvs = uvs;
	}
	
	
}
