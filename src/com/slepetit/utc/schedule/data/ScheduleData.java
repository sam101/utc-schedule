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
	private Set<String> semesters = new TreeSet<>();
	private DaysData[] days = new DaysData[6];

	public ScheduleData() {
		for (int i = 0; i < days.length; i++) {
			days[i] = new DaysData(i);
		}
	}
	
	
	public void add(UserData userData) {
		users.add(userData);
		semesters.add(userData.fullSemester);
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

	public Set<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(Set<String> semesters) {
		this.semesters = semesters;
	}

	public Map<String, UVData> getUvs() {
		return uvs;
	}

	public void setUvs(Map<String, UVData> uvs) {
		this.uvs = uvs;
	}
	
	
}
