package com.slepetit.utc.schedule.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserData {
	public String login;
	public String fullSemester;
	public String branch;
	public int semester;
	public Set<String> uvs = new HashSet<String>();
	
	public List<SlotData> slots = new ArrayList<>();
	
	
	public UserData() {
	}
	
	public void setSemester(String fullSemester) {
		this.fullSemester = fullSemester;
		branch = fullSemester.substring(0, 2);
		semester = Integer.parseInt(fullSemester.substring(2,4));
	}



	public void addTimeSlot(String line) {
		
		// Format the data to be exploitable
		// TODO: do something better with this.
		line = line.replaceAll(",F1,", " ");
		line = line.replaceAll(",F2,", " ");
		line = line.replaceAll(",", " ");
		line = line.replaceAll("S=", "");
		line = line.replaceAll("/", "");
		line = line.replaceAll("-", " ");
		line = line.replaceAll("\\.", "");
		line = line.replaceAll(" D1", " D 1"); // TODO : This is a dirty hack
		line = line.replaceAll(" D2", " D 2"); // TODO : This is a dirty hack
		line = line.replaceAll(" D3", " D 3"); // TODO : This is a dirty hack		
		line = line.replaceAll(" T1", " T 1");
		line = line.replaceAll(" T2", " T 2");
		// Removing multiples spaces
		line = line.replaceAll("\\s+", " ");
		line = line.trim();		
		
		String[] data = line.split(" ");
		
		int counter = 0;
		String uv = data[counter++];
		String type = data[counter++];
		int group = 0;
		
		if (uv.equals("N8")) {
			System.out.println(line);
		}
		
		// Handle people who are not yet in a group
		if (data[counter].equals("NON")) {
			return;
		}

		if (! type.equals("C")) {
			group = Integer.parseInt(data[counter++]);
		}
		
		String day = data[counter++];
				
		String beginDate = data[counter++];
		String endDate = data[counter++];
		String room = data[counter++];
				
		SlotData slotData = new SlotData(uv, room, type, group);
		
		int dayNumber = getDayNumber(day);

		int beginHour = Integer.parseInt(beginDate.substring(0,beginDate.indexOf(":")));
		int beginMinutes = Integer.parseInt(beginDate.substring(beginDate.indexOf(":") + 1,beginDate.length()));
		int endHour = Integer.parseInt(endDate.substring(0, endDate.indexOf(":")));
		int endMinutes = Integer.parseInt(beginDate.substring(endDate.indexOf(":") + 1));
		
		slotData.day = dayNumber;
		slotData.bh = beginHour;
		slotData.bm = beginMinutes;
		slotData.eh = endHour;
		slotData.em = endMinutes;
		
		slots.add(slotData);
	}

	private int getDayNumber(String day) {
		switch (day) {
			case "LUNDI":
				return 0;
			case "MARDI":
				return 1;
			case "MERCREDI":
				return 2;
			case "JEUDI":
				return 3;
			case "VENDREDI":
				return 4;
			case "SAMEDI":
				return 5;
			case "DIMANCHE": // Why not ?
				return 6;			
		}
		return -1;
	}

	@Override
	public String toString() {
		return "UserData [login=" + login + ", fullSemester=" + fullSemester
				+ ", branch=" + branch + ", semester=" + semester + ", uvs="
				+ uvs + ", slots=" + slots + "]";
	}
		
}

