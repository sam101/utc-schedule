package com.slepetit.utc.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slepetit.utc.schedule.requests.CASLoginRequest;
import com.slepetit.utc.schedule.requests.ScheduleListRequest;
import com.slepetit.utc.schedule.requests.ScheduleRequest;

public class ScheduleNetworkClient {
	private String EDT_URL = "http://wwwetu.utc.fr/sme/EDT/";

	private String modCasIdCookie = "";
		
	private List<String> users = new ArrayList<>();
	private Map<String, String> edtData = new HashMap<>();
	
	public ScheduleNetworkClient(String username, String password, String path) throws IOException {		
		HttpURLConnection.setFollowRedirects(true);

		retrieveUserData(username, password, path);
	}

	private void retrieveUserData(String username, String password, String path)
			throws IOException {			
		System.out.println("Login as " + username +  "...");
		CASLoginRequest casLoginRequest = new CASLoginRequest(username, password, EDT_URL);		
		System.out.println("Retrieving user list...");
		ScheduleListRequest listRequest = new ScheduleListRequest(casLoginRequest.getRedirectUrl());

		modCasIdCookie = listRequest.getModCasIdCookie();
		users = listRequest.getUsers();

		for (String user : users) {
			System.out.println("Retrieving " + user + "...");
			edtData.put(user, new ScheduleRequest(user, modCasIdCookie).getData());
			System.out.println("Writing " + user + "...");
			PrintWriter out = new PrintWriter(path + "/" + user + ".edt");
			out.println(edtData.get(user));
			out.close();
		}
	}

	public static void main(String[] args) {
		try {
			Files.createDirectories(Paths.get("data/edt"));
			new ScheduleNetworkClient("test", "thisisnotmypassword", "data/edt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
