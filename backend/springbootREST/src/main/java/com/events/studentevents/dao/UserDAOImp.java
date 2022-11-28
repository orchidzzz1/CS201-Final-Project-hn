package com.events.studentevents.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

	
	// //Returns every user in the Users table.
	// //Note the schema of the Users table MUST match the User class (name and type, BOTH) 
	// @Override
	// public List<User> getAll() {
	// 	List<User> res = template.query("SELECT * FROM Users", new BeanPropertyRowMapper<User>(User.class));
	// 	return res;
	// }


	@Override
	public int authenticateUser(String email, String password) {
		int userId = -1;
		String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
		Integer id = template.queryForObject(sql, new Object[] {email, password}, Integer.class);
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
		int count = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
	
		return count > 0;
	}

	@Override
	public int registerUser(UserInfo user) {
		int userId = -1;
		try {
			if(userExists(user.email)){
				return userId;
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
			userId = keys.getKey();
			
			//insert into preferences table
			List<Preference> prefs = user.preferences;
			for (Preference p : prefs) {
				String sqlString = "INSERT INTO Preferences(userId, preferenceId, alert)"
                            + " ?, (SELECT preferenceId FROM preferenceTypes pref WHERE pref.preferenceName = ?), FALSE";
				template.update(sqlString, new Object[] {userId, p.pname});
			}
		} catch (Exception e) {
			//Error occurred, return -1
			return -1;
		}
		return userId;
	}
	
	@Override
	public UserInfo getUser(int userId) {
		//adapted from
		//https://stackoverflow.com/questions/24221187/jdbctemplate-queryformap-for-retrieving-multiple-rows
		try {
			String sqlString = "SELECT user.email, user.displayName, types.preferenceName, pref.alert"
			+" FROM users user" 
			+" INNER JOIN preferences pref ON user.userId = pref.userId" 
			+" INNER JOIN preferencetypes types ON pref.preferenceId = types.preferenceId" 
			+" WHERE userId = ?";
			UserInfo user = template.query(sqlString, new ResultSetExtractor<UserInfo>(){
				@Override
				public UserInfo extractData(ResultSet rs) throws SQLException,DataAccessException {
					if(rs.next()){
						user = new UserInfo();
						//get user's display name and email
						user.displayName = rs.getString("displayName");
						user.email = rs.getString("email");
						
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
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void removePreferenceFromUser(int userId, String preference){
		template.update(connection -> {
			String sqlString = "DELETE FROM preferences pref" 
			+" WHERE pref.userId = ?"
			+" AND pref.preferenceId = (SELECT preferenceId FROM preferencetypes types WHERE types.preferenceName = ?)";			PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
			ps.setInt(1, userId);
			ps.setString(2, preference);
		});
	}

	@Override
	public void addPreferenceToUser(int userId, String preference){
		template.update(connection -> {
			String sqlString = "INSERT INTO preferences(userId, preferenceID, alert)"
			+ " VALUES (?, (SELECT types.preferenceId FROM preferencetypes types WHERE types.preferenceName = ?), FALSE)";			
			ps.setInt(1, userId);
			ps.setString(2, preference);
		});
	}

	@Override
	public void modifyNotificationSetting(int userId, String preference){
		template.update(connection -> {
			String sqlString = "UPDATE preferences pref" 
			+ " SET pref.alert = NOT pref.alert" 
			+ " WHERE pref.userId = ? AND pref.preferenceId = (SELECT types.preferenceId FROM preferencetypes types WHERE types.preferenceName = ?)";
			ps.setInt(1, userId);
			ps.setString(2, preference);
		});
	}
	
	@Override
	public List<String> getPreferenceTypes(){
		String sqlString = "SELECT preferenceName FROM preferencetypes";
		List<String>preferenceTypes = template.queryForList(sqlString, String.class);
		return preferenceTypes;
	}

}
