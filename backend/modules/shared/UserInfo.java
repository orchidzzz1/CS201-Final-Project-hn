package backend.modules.shared;

import java.util.ArrayList;

/*
 * Usage: serializable class to store user's info to be retrieved from database, 
 * processed by server, sent to frontend, etc
 */
public class UserInfo {
    public String displayName;
    public String email;
    public ArrayList<String> preferences = new ArrayList<String>();
    public ArrayList<Boolean> notifications = new ArrayList<Boolean>();
}
