package com.slepetit.utc.schedule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Schedule {
	private static final String DATA_PATH = "data/edt";

	public static void main(String[] args) throws IOException {
		
		Files.createDirectories(Paths.get("data/edt"));
		
		Scanner s = new Scanner(System.in);
		System.out.println("Login ?");
		String login = s.next();
		System.out.println("Password ?");
		String password = s.next();
		
		s.close();
		
		new ScheduleNetworkClient(login, password, DATA_PATH);
		
		ScheduleParser parser = new ScheduleParser(DATA_PATH);
		parser.parseAllUsers();
		parser.writeJsonData();
	}
}
