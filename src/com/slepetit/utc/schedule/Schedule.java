package com.slepetit.utc.schedule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Schedule {
	private static final String DATA_PATH = "data/edt";

	public static void main(String[] args) throws IOException {
		
		Files.createDirectories(Paths.get("data/edt"));
		
		new ScheduleNetworkClient("login", "passwrd", DATA_PATH);
		
		ScheduleParser parser = new ScheduleParser(DATA_PATH);
		parser.parseAllUsers();
		parser.writeJsonData();
	}
}
