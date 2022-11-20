package com.events.studentevents.dao;

import java.util.List;

import com.events.studentevents.model.User;


//Interface that provides all methods that can be called on this databse
public interface UserDAO {
	
	int save(User user);
	
	User getByid(int id);
	
	List<User> getAll();

}
