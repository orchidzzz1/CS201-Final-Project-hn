package com.events.studentevents.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.events.studentevents.dao.UserDAO;
import com.events.studentevents.model.PreferenceUser;
import com.events.studentevents.model.User;


@RestController
public class UserController {
	
	
	//Creates a new instance of the UserDAO interface, and uses this to return appropriate values
	@Autowired
	private UserDAO uDao;
	
	
	//This annotation means any GET requests at the address url-of-server/users will cause 
	//this function to execute
	@GetMapping("/api/users")
	@ResponseBody
	public List<User> getUsers(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getAll();
		
	}
	
	//This annotation means any GET requests to url-of-server/users/id will cause this 
	//code to execute
	@GetMapping("/api/users/{id}")
	@ResponseBody
	public User getUserById(@PathVariable("id") String email, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getByEmail(email);
	}
	
	@GetMapping("/api/authenticate/{email}/{password}")
	@ResponseBody
	public int authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			boolean res = uDao.authenticate(email, password);
			if (res) {
				return 1;
			}
			
			return -1;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	//PostMapping is another annotation option for any POST requests
	
	@PostMapping("/api/users")
	@ResponseBody
	public int insertUser(@RequestBody PreferenceUser user, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.insertUser(user);
	}

}
