package com.events.studentevents.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.events.studentevents.model.*;


//Implementation of UserDAO method
@Repository
public class UserDAOImp implements UserDAO {
	
	//URL and username + password are set in application.properties
	//This creates a JdbcTemplate object we can use to run queries
	@Autowired
	JdbcTemplate template;

	@Override
	public int authenticateUser(String email, String password) {
		int userId = -1;
		String sql = "SELECT userId FROM Users WHERE email = ? AND password = ?";
		Integer id = template.queryForObject(sql, Integer.class, new Object[] {email, password});
		if(id != null){
			userId = id; 
		}
		return userId;
	}
	
	/*
	 * Check if user exists already
	 */
	protected boolean userExists(String email) {
		String sql = "SELECT count(*) FROM Users WHERE email = ?";
		int count = template.queryForObject(sql, Integer.class, new Object[] { email });
	
		return count > 0;
	}
	
	//TODO: Weird error, returns -1?

	@Override
	public String registerUser(UserInfo user) {
		int userId = -1;
		try {
			if(userExists(user.email)){
				return String.valueOf(userId);
			}
			
			KeyHolder keys = new GeneratedKeyHolder();
			//Insert user and return userId
			template.update(connection -> {
				String sql = "INSERT INTO Users(email, password, displayName) VALUES (?, ?, ?)";
				PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
				ps.setString(1, user.email);
				ps.setString(2, user.password);
				ps.setString(3, user.displayName);
				return ps;
			}, keys);
			//update userId with the auto-generated id of the newly added user
			
			Number n = keys.getKey();
			
			userId = n.intValue();
			
			//insert into preferences table
			List<Preference> prefs = user.preferences;
			for (Preference p : prefs) {
				String sqlString = "INSERT INTO Preferences(userId, preferenceId, alert)"
                            + " VALUES (?, (SELECT preferenceId FROM Preferencetypes pref WHERE pref.preferenceName = ?), FALSE)";
				template.update(sqlString, new Object[] {userId, p.preferenceName});
			}
		} catch (Exception e) {
			//Error occurred, return -1
			return "ERROR: " + e.getLocalizedMessage() + " " + e.getStackTrace().toString();
		}
		return "Status: " + String.valueOf(userId);
	}
	
	@Override
	public void changePassword(int userId, String newPassword){
		String sqlString = "UPDATE Users user" 
				+ " SET user.password = ?" 
				+ " WHERE user.userId = ?";	
		template.update(sqlString, new Object[] {newPassword, userId} );
	}

	@Override
	public UserInfo getUser(int userId) {
		//adapted from
		//https://stackoverflow.com/questions/24221187/jdbctemplate-queryformap-for-retrieving-multiple-rows
		try {
			String sqlString = "SELECT user.email, user.displayName, user.userId, types.preferenceName, pref.alert"
			+" FROM Users user" 
			+" LEFT JOIN Preferences pref ON user.userId = pref.userId" 
			+" LEFT JOIN Preferencetypes types ON pref.preferenceId = types.preferenceId" 
			+" WHERE user.userId = ?";
			UserInfo user = template.query(sqlString, new ResultSetExtractor<UserInfo>(){
				@Override
				public UserInfo extractData(ResultSet rs) throws SQLException,DataAccessException {
					
					if(rs.next()){
						UserInfo user = new UserInfo();
						//get user's display name and email
						user.displayName = rs.getString("displayName");
						user.email = rs.getString("email");
						user.preferences = new ArrayList<Preference>();
						user.userId = rs.getInt("userId");
						//get user's preferences and notification settings
						do{
							Preference pref = new Preference();
							pref.preferenceName = rs.getString("preferenceName");
							pref.alert = rs.getBoolean("alert");
							user.preferences.add(pref);
						}while(rs.next());
						return user;
					}
					return null;
				}
			}, new Object[] {userId});
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void removePreferenceFromUser(int userId, String preference){
		String sqlString = "DELETE FROM Preferences pref" 
				+" WHERE pref.userId = ?"
				+" AND pref.preferenceId = (SELECT preferenceId FROM Preferencetypes types WHERE types.preferenceName = ?)";
		template.update(sqlString, new Object[] {userId, preference} );
	}

	@Override
	public void addPreferenceToUser(int userId, String preference){
		
		Logger l = LoggerFactory.getLogger(getClass());
		l.info(preference);
		String sqlString = "INSERT INTO Preferences(userId, preferenceID, alert)"
		+ " VALUES (?, (SELECT preferenceId FROM Preferencetypes types WHERE preferenceName = ?), FALSE)";
		template.update(sqlString, new Object[] {userId, preference} );
	}

	@Override
	public void modifyNotificationSetting(int userId, String preference){
		String sqlString = "UPDATE Preferences pref" 
				+ " SET pref.alert = NOT pref.alert" 
				+ " WHERE pref.userId = ? AND pref.preferenceId = (SELECT types.preferenceId FROM Preferencetypes types WHERE types.preferenceName = ?)";
		template.update(sqlString, new Object[] {userId, preference} );
	}
	
	
	//Returns only a single preferencetype currently TODO: Debug so all are returned
	
	@Override
	public List<String> getPreferenceTypes(){
		String sqlString = "SELECT preferenceName FROM Preferencetypes";
		List<String> preferenceTypes =template.queryForList(sqlString, String.class);
		return preferenceTypes;
	}

}
