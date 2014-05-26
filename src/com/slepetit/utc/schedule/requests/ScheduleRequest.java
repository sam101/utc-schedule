package com.slepetit.utc.schedule.requests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.slepetit.utc.tools.ReadAllBufferedReader;

/**
 * Request which will start an HTTP request for
 * a given user and retrieve its schedule data 
 * @author Samuel Lepetit
 *
 */
public class ScheduleRequest {
	private static final String BASE_URL = "http://wwwetu.utc.fr/sme/EDT/";
	private String data;

	public ScheduleRequest(String user, String modCasIdCookie) throws IOException {
		URL scheduleUrl = new URL(BASE_URL + user + ".edt");
        HttpURLConnection connection = (HttpURLConnection)scheduleUrl.openConnection();
        connection.setRequestProperty("Cookie", modCasIdCookie);
        
        ReadAllBufferedReader in = new ReadAllBufferedReader(new InputStreamReader(connection.getInputStream()));                
        data = in.readAll();
        in.close();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
