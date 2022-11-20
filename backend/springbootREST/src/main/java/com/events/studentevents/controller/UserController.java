package com.events.studentevents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.events.studentevents.dao.UserDAO;
import com.events.studentevents.model.User;


@RestController
public class UserController {
	
	
	//Creates a new instance of the UserDAO interface, and uses this to return appropriate values
	@Autowired
	private UserDAO uDao;
	
	
	//This annotation means any GET requests at the address url-of-server/users will cause 
	//this function to execute
	@GetMapping("/users")
	public List<User> getUsers(){
		
		return uDao.getAll();
		
	}
	
	//This annotation means any GET requests to url-of-server/users/id will cause this 
	//code to execute
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		return uDao.getByid(id);
		
	}

	//PostMapping is another annotation option for any POST requests
}
