package com.events.studentevents.dao;

import java.util.List;
import com.events.studentevents.model.*;


//Interface that provides all methods that can be called on this databse
public interface UserDAO {
	 /*
     * Check if the email and password already exist in the database
     * @return userId (a positive int) if they exist in order to be stored in session;
	 *  -1 if user does not exist
     */
	int authenticateUser(String email, String password);
	
	/*
     * Add user and their info to the database 
     * @return userId if registered successfully in order to be stored in session;
	 *  -1 if failed to add user
     */
	String registerUser(UserInfo user);
	

	/*
	 * Change password
	 * Note: password requirements should be checked by front end
	 * @param userId: should be retrieved from session in order to identify the user
	 */
	public void changePassword(int userId, String newPassword);


	/*
     * Remove a user's preference
     * @param userId: should be retrieved from session in order to identify the user
     * @param preference: preference to be removed
     */
	public void removePreferenceFromUser(int userId, String preference);

	/*
     * Add a user's preference
     * @param userId: should be retrieved from session in order to identify the user
     * @param preference: preference to be added
     */
	public void addPreferenceToUser(int userId, String preference);

	 /*
     * Modify the notification setting of a user for a preference. 
     * The default alert boolean for all preferences added is false.
     * If alert == false, change it to true when this method is called. 
     * If alert == true, change to false.
     * @param userId: should be retrieved from session in order to identify the user
     * @param preference: the activity preference where user would like to receive/not receive notification
     */
    public void modifyNotificationSetting(int userId, String preference);

	/*
     * Get the display name and list of activity/time/notification preferences.
     * Use this object to send to front end (as Json) in order to display user's information
     * on the User page
     * @param userId: should be retrieved from session in order to identify the user 
     * @return UserInfo if the user exists; null if the user does not exist
     * Note: preferences include activities and times (weekdays)
     */
    public UserInfo getUser(int userId);

	/*
     * Get all preference types available for users to choose
	 * Only get preference names because prefId SHOULD NOT be sent to the front end
     * Purpose: can be used for front end to present their list of preferences to users to choose
     */
    public List<String> getPreferenceTypes();

	
}
