package com.events.studentevents.dao;

import java.util.List;

import com.events.studentevents.model.PreferenceUser;
import com.events.studentevents.model.User;


//Interface that provides all methods that can be called on this databse
public interface UserDAO {
	
	int save(User user);
	
	User getByEmail(String email);
	
	List<User> getAll();
	
	boolean authenticate(String email, String password);
	
	int insertUser(PreferenceUser user);
	
}
