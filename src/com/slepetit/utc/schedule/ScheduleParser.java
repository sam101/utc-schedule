package com.slepetit.utc.schedule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slepetit.utc.schedule.data.ScheduleData;
import com.slepetit.utc.schedule.data.UserData;

/**
 * Main class for parsing schedule data
 * and returning it as a data object
 * @author Samuel Lepetit
 *
 */
public class ScheduleParser {
	
	private String path;
	private ScheduleData scheduleData;
	
	public ScheduleParser(String path) {
		this.path = path;
	}
	
	public ScheduleData parseAllUsers() throws IOException {
		ScheduleData scheduleData = new ScheduleData();
		
		File directory = new File(path);
		File[] files = directory.listFiles();
		
		for (File file : files) {
			System.out.println("Parsing " + file.getCanonicalPath());
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);				
			scheduleData.add(parseUserData(lines));
		}
		this.scheduleData = scheduleData;
		
		return scheduleData;
	}
	
	public UserData parseUserData(List<String> lines) {
		
		UserData data = new UserData();

		removeUselessLines(lines);
		/* 
		 * First, we need to extract the useful information from the first line
		 * which contains the login and the UV list.
		 */
		parseFirstLineData(lines, data);
		
		// Parse the time slots
		for (int i = 1; i < lines.size(); i++) {
			data.addTimeSlot(lines.get(i));
		}		
		
		return data;
	}

	private void parseFirstLineData(List<String> lines, UserData data) {
		String firstLine = lines.get(0);
		firstLine = firstLine.replaceAll("\\s+", " ");
		firstLine = firstLine.trim();
		String[] firstLineData = firstLine.split(" ");
		
		data.login = firstLineData[0];
		data.setSemester(firstLineData[1]);
		// The uvs are contained after the number of UV
		int nUvs = Integer.parseInt(firstLineData[2]);
		for (int i = 0; i < nUvs; i++) {
			data.uvs.add(firstLineData[3 + i]);
		}
		
	}

	private void removeUselessLines(List<String> lines) {
		// Remove all the blank lines
		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			String val = it.next();
			if (val.trim().length() == 0) {
				it.remove();
			}
		}
		// The first and the last line are useless, we don't need them
		lines.remove(0);
		lines.remove(lines.size() - 1);
	}
	
	public void writeJsonData() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Writing days...");
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/days.json"), scheduleData.getDays());
		System.out.println("Writing " + scheduleData.getUsers().size() + " users...");
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/users.json"), scheduleData.getUsers());
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/semesters.json"), scheduleData.getSemesters());
		System.out.println("Writing " + scheduleData.getSemesters().size() + " rooms...");
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/rooms.json"), scheduleData.getRooms());
		System.out.println("Writing " + scheduleData.getUvs().size() + " uvs...");
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/uvs.json"), scheduleData.getUvs());
		System.out.println("Done.");		
	}
	
	public static void main(String[] args) throws IOException {	
		ScheduleParser parser = new ScheduleParser("data/edt/");
		parser.parseAllUsers();
		parser.writeJsonData();
	}
}
