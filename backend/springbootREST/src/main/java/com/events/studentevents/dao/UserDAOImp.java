package com.events.studentevents.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.events.studentevents.model.User;


//Implementation of UserDAO method
@Repository
public class UserDAOImp implements UserDAO {
	
	//URL and username + password are set in application.properties
	//This creates a JdbcTemplate object we can use to run queries
	@Autowired
	JdbcTemplate template;

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	//Still a work-in-progress, will need to change some aspects.
	//In the future: will take a string (email address), and then return info about the user
	@Override
	public User getByEmail(String email) {
		try {
			User res = template.queryForObject("SELECT * FROM Users WHERE email=?", new BeanPropertyRowMapper<User>(User.class), email);
			return res;
		} catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	
	//Returns every user in the Users table.
	//Note the schema of the Users table MUST match the User class (name and type, BOTH) 
	@Override
	public List<User> getAll() {
		List<User> res = template.query("SELECT * FROM Users", new BeanPropertyRowMapper<User>(User.class));
		return res;
	}

	@Override
	public boolean authenticate(String email, String password) {
		// TODO Auto-generated method stub
		
		User res = template.queryForObject("SELECT * FROM Users WHERE email=? AND password=?", new BeanPropertyRowMapper<User>(User.class), new Object[] {email, password});
		return res != null;
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		
		try {
			if (this.getByEmail(user.getEmail()) != null) {
				return 0;
			}
			template.update("INSERT INTO Users (email, password) VALUES (?, ?)", new Object[] {user.getEmail(), user.getPassword()});
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	

}
