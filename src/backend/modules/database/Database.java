package backend.modules.database;
import java.util.ArrayList;
import backend.modules.shared.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database{
    private Connection conn = null;
    /*
     * option 1: connect/disconnect for one query --> connect/disconnect = private --> better because
     * users of the class does not have to manually connect/disconnect for every query and can just call the method 
     * for the query they need
     * option 2: programmer responsible for connecting, querying, and disconnecting --> connect/disconnect = public
     */
    public Database(){
        //set variables here if necessary
    }
    /*
     * Connect to the mysql database using Connector/J
     */
    private void connect(){
        try{
            conn = DriverManager.getConnection(null); //change this
        }catch (SQLException sqle) {
			System.out.println ("Fail to connect to db: " + sqle.getMessage());
		}
    }

    /* 
     * Disconnect from mysql database after finishing with queries
     */
    private void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
            
        } catch (SQLException sqle) {
            System.out.println("Fail to disconnect from db: " + sqle.getMessage());
        }
    }

    /*
     * Add user and their info to the database 
     */
    public void addUser(String email, String password, String displayName, ArrayList<String> preferences){
        //insert to Preferences table
		PreparedStatement ps = null;
        PreparedStatement ps2 = null;
		try {
            connect();
            //insert to Users table
			ps = conn.prepareStatement("INSERT INTO users(email, password, displayName) VALUES (?, ?, ?)");
			ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, displayName);
            ps.execute();
            
            //insert to Preferences table
            ps2 = conn.prepareStatement("INSERT INTO preferences(userId, preferenceId) SELECT user.userId, pref.preferenceId FROM users user, preferenceTypes pref WHERE pref.preferenceName = ? AND user.displayName = ?");
            for(String pref : preferences){
                ps2.setString(1, pref);
                ps2.setString(2, displayName);
            }


		}catch (SQLException sqle) {
			System.out.println ("Exception when adding new user: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizing new user's insertion: " + sqle.getMessage());
			}
		}
    }

    /*
     * Modify the preferences of a user. If preference exists for this user, remove it when called. 
     * If not, add it for this user.
     * @param displayName: (need to make this name unique for all users if to use this param to look up users)
     */
    public void modifyPreferences(String displayName, String new_preference){
        //modify Preferences table
    }

    /*
     * Modify the preferences of a user. The default alert boolean for all preferences added is false.
     * If alert == false, change it to true when this method is called. If alert == true, change to false.
     * @param displayName: (need to make this name unique for all users if to use this param to look up users)
     * @param preference: the activity preference where user would like to receive/not receive notification
     */
    public void modifyNotification(String displName, String preference){

    }

    /*
     * Check if the email and password already exist in the database
     * @return true if they exist
     */
    public boolean authenticateUser(String email, String password){
        return true;
    }

    /*
     * Get the display name and list of activity/time/notification preferences.
     * Use this object to send to front end (as Json) in order to display user's information
     * on the User page
     * Note: preferences include activities and times (weekdays)
     */
    public UserInfo getUserInfo(){
        UserInfo user = new UserInfo();
        return user;
    }

    /*
     * Add to the RSVP table whenever a user RSVP to an event
     */
    public void addRSVP(int eventId, int userId){

    }

    /*
     * Delete an RSVP entry whenever a user un-RSVP from an event
     */
    public void deleteRSVP(int eventId, int userId){

    }

    /*
     * Add an event to the event table whenever a user creates a event.
     * The user is also auto-RSVPed to the event
     */
    public void addEvent(){

    }

    /* 
     * Modify an existing event's description, event date/time, activity type, etc.
     */
    public void modifyEvent(){

    }

    /*
     * Return all events that match certain preferences.
     * Used for the user's personal feed
     */
    public ArrayList<Event> getMatchingEvents(){
        return null;
    }

    /*
     * Return notifications of all events that will start in an hour or less.
     * To be sent to the front end for alert notifications display.
     */
    public ArrayList<Notification> getNotifications(String displayName){
        return null;
    }
}