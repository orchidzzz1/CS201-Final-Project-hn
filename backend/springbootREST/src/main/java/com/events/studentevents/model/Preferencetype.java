package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preferencetype {
	
	private int preferenceId; 
	private String preferenceName;
	
	
	public String getPreferenceName() {
		return preferenceName;
	}
	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}
	public int getPreferenceId() {
		return preferenceId;
	}
	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	} 

}
