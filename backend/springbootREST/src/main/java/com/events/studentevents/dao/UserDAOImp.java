package com.events.studentevents.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.events.studentevents.model.User;
import com.events.studentevents.model.Preference;
import com.events.studentevents.model.PreferenceUser;


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
	public int insertUser(PreferenceUser pUser) {
		// TODO Auto-generated method stub
		
		try {
			User u = this.getByEmail(pUser.getEmail());
			//If a user corresponding to the provided email already exists, return 0 and do nothing
			if (u != null) {
				return u.getId();
			}
			
			KeyHolder keys = new GeneratedKeyHolder();
			//Insert user and return 1
			template.update(connection -> {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Users (email, password) VALUES (?, ?)", new String[] {"id"});
				ps.setString(1, pUser.getEmail());
				ps.setString(2, pUser.getPassword());
				return ps;
			}, keys);
			
			Number n = keys.getKey();
			//template.update("INSERT INTO Users (email, password) VALUES (?, ?)", new Object[] {pUser.getEmail(), pUser.getPassword()});
			
			List<Preference> prefs = pUser.getPreferences();
			
			for (Preference p : prefs) {
				
				template.update("INSERT INTO Preferences (userId, preferenceId, alert) VALUES (?,?,?)", new Object[] {n.intValue(), p.getPid(), p.isAlert()});
				
			}
			return 1;
		} catch (Exception e) {
			//Error occurred, return -1
			return -1;
		}
	}

	
	
	

}
