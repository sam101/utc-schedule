package com.slepetit.utc.schedule.requests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.slepetit.utc.tools.ReadAllBufferedReader;
/**
 * Request, to retrieve a user list from the UTC server 
 * given a valid CAS ticket
 * @author Samuel Lepetit
 *
 */
public class ScheduleListRequest {
	private List<String> users = new ArrayList<String>();
	/**
	 * CAS Cookie, only set if retrieving the schedule list
	 * using a ticket
	 */
	private String modCasIdCookie;

	public ScheduleListRequest(URL edtListUrl) throws IOException {
        HttpURLConnection edtListConnection = (HttpURLConnection)edtListUrl.openConnection();
        ReadAllBufferedReader inEdtList = new ReadAllBufferedReader(new InputStreamReader(edtListConnection.getInputStream()));                
        String edtListData = inEdtList.readAll();
        inEdtList.close();
        modCasIdCookie = edtListConnection.getHeaderField("Set-Cookie");
        modCasIdCookie = modCasIdCookie.substring(0, modCasIdCookie.indexOf(";"));
        
		Pattern usernamePattern = Pattern.compile("<a href=\"([^\"]+?).edt\">");
		Matcher usernameMatcher = usernamePattern.matcher(edtListData);      
		
		while (usernameMatcher.find()) {
			users.add(usernameMatcher.group(1));
		}		
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public String getModCasIdCookie() {
		return modCasIdCookie;
	}

	public void setModCasIdCookie(String modCasIdCookie) {
		this.modCasIdCookie = modCasIdCookie;
	}
	
	
	
}
