package backend.modules.database;
import java.util.ArrayList;
import backend.modules.shared.*;

public class Database{
    /*
     * 
     */
    public Database(){

    }
    /*
     * Connect to the mysql database using Connector/J
     */
    public void connect(){

    }

    /* 
     * Disconnect from mysql database after finishing with queries
     */
    public void disconnect(){

    }

    /*
     * Add user to the database 
     */
    public void addUser(String email, String password, String displayName, ArrayList<String> preferences){

    }

    /*
     * Modify the preferences of a user. If preference exists for this user, remove it when called. 
     * If not, add it for this user.
     * @param displayName: (need to make this name unique for all users if to use this param to look up users)
     */
    public void modifyPreferences(String displayName, String new_preference){

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