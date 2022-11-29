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
import com.events.studentevents.model.*;


@RestController
public class UserController {
	
	
	//Creates a new instance of the UserDAO interface, and uses this to return appropriate values
	@Autowired
	private UserDAO uDao;
	
	
	/*
	 * Get all preference types available for user to choose 
	 */
	@GetMapping("/api/getPreferenceTypes")
	@ResponseBody
	public List<String> getPreferenceTypes(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getPreferenceTypes();
	}
	
	//modify later
	@GetMapping("/api/addPreferenceToUser/{preferenceName}/{id}")
	@ResponseBody
	public void addPreferenceToUser(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		//get userId from session
		uDao.addPreferenceToUser(id, preferenceName);
		//redirect to api/user to show updated changes?
	}
	
	// modify later
	@GetMapping("/api/removePreferenceFromUser/{preferenceName}/{id}")
	@ResponseBody
	public void removePreferenceFromUser(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// get userId from session
		uDao.removePreferenceFromUser(id, preferenceName);
		// redirect to api/user to show updated changes?
	}
	
	//modify later
	@GetMapping("/api/modifyNotificationSetting/{preferenceName}/{id}")
	@ResponseBody
	public void modifyNotificationSetting(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// get userId from session
		uDao.modifyNotificationSetting(id, preferenceName);
		// redirect to api/user to show updated changes?
	}
	
	//not safe -- should store id in session whenever user logs in or registers and not send id to front end
	//This annotation means any GET requests to url-of-server/users/id will cause this 
	//code to execute
	/*
	 * Get user's information including email, displayName, and list of {preferenceName, alert}
	 */
	@GetMapping("/api/user/{id}")
	@ResponseBody
	public UserInfo getUser(@PathVariable("id") int id, HttpServletResponse response) {
		//should get id from session
		response.addHeader("Access-Control-Allow-Origin", "*");
		UserInfo user =  uDao.getUser(id);
		return user;
	}
	
	/*
	 * Send back 1 to front end if authentication is successful; -1 if not
	 */
	@GetMapping("/api/authenticate/{email}/{password}")
	@ResponseBody
	public int authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			//should store user id returned in session
			int userId = uDao.authenticateUser(email, password);
			if (userId > 0) {
				return 1;
			}
			
			return -1;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	//PostMapping is another annotation option for any POST requests
	/*
	 * Send back 1 to front end if registration is successful; -1 if not
	 */
	@PostMapping("/api/registerUser")
	@ResponseBody
	public String insertUser(@RequestBody UserInfo user, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			//should store user id returned in session
			return uDao.registerUser(user);
//			if (userId > 0) {
//				return "1";
//			}
			
			//return "ERROR";
		} catch (EmptyResultDataAccessException e) {
			return "ERRORRR: " + e.getLocalizedMessage();
		}
	}

}
