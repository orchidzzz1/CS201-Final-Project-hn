package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * Classes here represent the structure of the data stored in the database
 * For e.g. the user table should store id, email, password, and we create associated fields
 * The type and name of the fields in this class must match EXACTLY to the type and name of 
 * fields in the table. 
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


	private int id;
	private String email;
	private String password;
	private String displayName;
	
//	public User(int id, String email, String password) {
//		this.id = id;
//		this.email = email;
//		this.password = password;
//	}
//	
//	
//	public User() {
//		super();
//	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
