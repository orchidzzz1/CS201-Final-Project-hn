package backend.modules.database.tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import backend.modules.database.Database;

/*
 * Test database class used for testing that re-creates the database schema and tables using in-mem database in order to test Database class's methods
 * Purpose: to perform unit testing while not having to interact with the actual database
 * so that we will not mess up the actual database with testing values
 * Note: HSQLDB does not support update-join-set statement and delete-join statement 
 * but mysql does so if the database's methods use the statement there is no need to test it against HSQLDB
 * Website to hsqldb supported statements: http://hsqldb.org/doc/2.0/guide/guide.html#dac_update_statement
 */
public class TestDatabase extends Database {
	//https://stackoverflow.com/questions/38415734/hsql-database-user-lacks-privilege-or-object-not-found-error
	//connect to in-mem database using these credentials 
	private static String jdbcURL = "jdbc:hsqldb:mem:201projectdb;sql.syntax_mys=true";
	private static String jdbcUsername = "SA";
	private static String jdbcPassword = "";
	Connection conn = null;
	public TestDatabase() {
		super();
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connect();
		System.out.println("connected");
		//run sql scripts to create schema and tables
		SQLExec(new File("backend\\modules\\database\\SQLscripts\\createTables.sql"));
		SQLExec(new File("backend\\modules\\database\\SQLscripts\\initPreferenceTypesTable.sql"));
		System.out.println("Executed all sql scripts");
		disconnect();
	}
	
	//execute sql scripts to create database tables
	private final void SQLExec(File file) {
		//parse the sql script file by semicolon
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String sqlString = "";
			while((line = reader.readLine()) != null) {
				sqlString += line;
				//if the line contains a semicolon, that marks the end of one query statement
				if(sqlString.contains(";")) {
					//execute line
					try {
			            Statement st = conn.createStatement();
			            //use of jdbc statements require lack of quotation marks in the string
			            sqlString = sqlString.replace("'", "");
			            sqlString = sqlString.replace("\"", "\'");
			            //hsqdb does not support UNSIGNED constraint, so delete it for the tests
			            sqlString = sqlString.replace(" UNSIGNED", "");
			            //hsqdb does not support getdate() function but cloudsql does so only delete it for the tests
			            sqlString = sqlString.replace(" DEFAULT GETDATE()", "");
			            
			            st.execute(sqlString);
					}catch (SQLException sqle) {
						System.out.println ("Exception when executing line: " + sqle.getMessage());
					}
					//clear line
					sqlString = "";
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void connect() {
		//connect to hsqldb database; override the parent's connect method which connects to cloudsql
		try {
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            super.conn = this.conn;
        } catch (SQLException sqle) {
        	System.out.println ("Fail to connect to db: " + sqle.getMessage());
        }
	}
	
	@Override
	protected void disconnect() {
		//disconnect from hsqldb database; overrides the parent's disconnect method
		try {
            if(conn!=null) {
            	conn.close();
                super.conn.close();
            }
        } catch (SQLException sqle) {
        	System.out.println ("Fail to disconnect from db: " + sqle.getMessage());
        }
	}
	public void deleteSchema() {
		//delete tables when done with tests
		try {
            connect();
            Statement st = conn.createStatement();
            st.execute("DROP TABLE IF EXISTS users");
            Statement st1 = conn.createStatement();
            st1.execute("DROP TABLE IF EXISTS preferences");
            Statement st2 = conn.createStatement();
            st2.execute("DROP TABLE IF EXISTS events");
            Statement st3 = conn.createStatement();
            st3.execute("DROP TABLE IF EXISTS rsvp");
            Statement st4 = conn.createStatement();
            st4.execute("DROP TABLE IF EXISTS preferencetypes");
		}catch (SQLException sqle) {
			System.out.println ("Exception when removing schema: " + sqle.getMessage());
		} finally {
			disconnect();
        }
	}
}
