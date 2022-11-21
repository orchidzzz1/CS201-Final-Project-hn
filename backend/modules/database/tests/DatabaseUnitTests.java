package backend.modules.database.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import backend.modules.shared.*;


class DatabaseUnitTests{
	private static TestDatabase db;
	
	//set up before ALL tests which is create database tables
	@BeforeAll
	public static void setup() {
		System.out.println("Setting up database schema");
		db = new TestDatabase();
	}

	//tear down after ALL tests which is delete all created tables in database
	@AfterAll
	public static void teardown() {
		System.out.println("Dropping database schema");
		db.deleteSchema();
	}
	
	@Test
	void registerAndAuthenticateUserTest() {
		ArrayList<String> prefs = new ArrayList<String>();
		prefs.add("Sports");
		int newUserId = db.registerUser("new@usc.edu", "12345", "New1", prefs);
		assertTrue(newUserId > 0, "Register a new user");
		
		int authenticatedUserId = db.authenticateUser("new@usc.edu", "12345");
		assertTrue(authenticatedUserId == newUserId, "Authenticate a user who is already registered and provides correct email and password is incorrect");
		
		int wrongEmailId = db.authenticateUser("new1@usc.edu", "12345");
		assertTrue(wrongEmailId == -1, "Authenticate a user but the provided email is incorrect");
		
		int wrongPwId = db.authenticateUser("new@usc.edu", "123456");
		assertTrue(wrongPwId == -1, "Authenticate a user but the provided password is incorrect");
		
		int duplicateUser = db.registerUser("new@usc.edu", "123456", "New2", prefs);
		assertTrue(duplicateUser == -1, "Register a user whose email already exists is incorrect");
	}
	
	@Test
	void getUserInfoTest() {
		//register new user
		ArrayList<String> prefs = new ArrayList<String>();
		prefs.add("Sports");
		ArrayList<Boolean> notifs = new ArrayList<Boolean>();
		notifs.add(false);
		int newUserId = db.registerUser("newUser@usc.edu", "123456789", "NewNew", prefs);
		
		//get the new user's info
		UserInfo user = db.getUserInfo(newUserId);
		
		//compare the return value to the actual values
		assertTrue(user.displayName.equals("NewNew"), "User's retrieved display name is incorrect");
		assertTrue(user.email.equals("newUser@usc.edu"), "User's retrieved email is incorrect");
		assertTrue(user.preferences.equals(prefs), "User's retrieved preferences is incorrect");
		assertTrue(user.notifications.equals(notifs), "User's retrieved notification settings for corresponding prefernce is incorrect");
	}
	
	@Test
	void getPreferenceTypes() {
		ArrayList<String> prefTypes = db.getPreferenceTypes();
		ArrayList<String> actualPrefTypes = new ArrayList<String>();
		//note: update this array whenever the preferencetypes table is updated to match the table's records
		String[] prefArray = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Sports", "Club activity", "Study group"};
		actualPrefTypes.addAll(Arrays.asList(prefArray));
		
		assertTrue(prefTypes.equals(actualPrefTypes), "Get all the preference types available for user to choose is incorrect");
	}
	
	@Test
	void addAndRemovePreferenceFromUser() {
		//register new user
		ArrayList<String> prefs = new ArrayList<String>();
		prefs.add("Sports");
		ArrayList<Boolean> notifs = new ArrayList<Boolean>();
		notifs.add(false);
		int newUserId = db.registerUser("newUserforPref@usc.edu", "123456789", "NewNew", prefs);
		prefs.add("Monday");
		
		//add preference to user
		db.addPreferenceToUser(newUserId, "Monday");
		UserInfo user = db.getUserInfo(newUserId);
		boolean equal = true;
		for(int i=0; i<user.preferences.size();++i) {
			if(!prefs.contains(user.preferences.get(i))) {
				equal = false;
				break;
			}
		}
		assertTrue(equal == true, "Adding a preference to user is incorrect");
		
		//remove preference from user
		prefs.remove("Monday");
		db.removePreferenceFromUser(newUserId, "Monday");
		user = db.getUserInfo(newUserId);
		equal = true;
		for(int i=0; i<user.preferences.size();++i) {
			if(!prefs.contains(user.preferences.get(i))) {
				equal = false;
				break;
			}
		}
		assertTrue(equal == true, "Removing a preference from user is incorrect");
	}
	
	@Test
	void modifyUserNotificationSettings(){
		//register new user
		ArrayList<String> prefs = new ArrayList<String>();
		prefs.add("Sports");
		//notification is set to False as default for newly added preference
		int newUserId = db.registerUser("newUserforPrefMod@usc.edu", "123456789", "NewNew", prefs);
		db.modifyNotificationSetting(newUserId, "Sports");
		UserInfo user = db.getUserInfo(newUserId);
		assertTrue(user.notifications.get(0) == true, "Changing notifcation for Sports from false to true is incorrect");
		db.modifyNotificationSetting(newUserId, "Sports");
		user = db.getUserInfo(newUserId);
		assertTrue(user.notifications.get(0) == false, "Changing notifcation for Sports from true to false is incorrect");
	}

}
