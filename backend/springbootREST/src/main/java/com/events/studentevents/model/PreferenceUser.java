package com.events.studentevents.model;

import java.util.List;

public class PreferenceUser {
	
	private int id;
	private String email;
	private String password;
	private List<Preference> preferences;
	
	public List<Preference> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
