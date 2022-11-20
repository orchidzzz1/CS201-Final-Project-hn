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
	
	@Autowired
	private UserDAO uDao;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		
		return uDao.getAll();
		
	}
	
	@GetMapping("/Users/{id}")
	public User getUserById(@PathVariable int id) {
		return uDao.getByid(id);
		
	}

}
