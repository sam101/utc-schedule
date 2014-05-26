package com.slepetit.utc.schedule.data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVData {
	public String name;
	public int studentsCount;
	public Map<String, Integer> semesters = new TreeMap<>();
	public Set<String> students = new TreeSet<String>();

	public UVData() {
		
	}
	
	public UVData(String name) {
		this.name = name;
	}
	
	public void add(UserData userData) {
		if (! userData.uvs.contains(name)) {
			return;
		}
		studentsCount++;
		if (semesters.containsKey(userData.fullSemester)) {
			semesters.put(userData.fullSemester, semesters.get(userData.fullSemester) + 1);
		}
		else {
			semesters.put(userData.fullSemester, 1);
		}
		students.add(userData.login);
	}
}
