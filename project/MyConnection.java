package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
static Connection con;
	
	public static Connection getMyConnection() throws FileNotFoundException, IOException,SQLException
	{
		if(con==null) {
			Properties property = new Properties();
			property.load(new FileInputStream(new File("C:\\Users\\91893\\eclipse-workspace\\corejava pro1\\src\\project\\dp.properties")));
	        // how to load the properties file
			// by using myconnection - properties - copy path - paste it here.
			String url = property.getProperty("url");
	        String username = property.getProperty("username");
	        String password = property.getProperty("password");
	        
	        con = DriverManager.getConnection(url,username,password);
	       return con; 
		}
		else {
			return con;
		}
	}

}
 //dp.properties - myconnection - statement
//extablish sql to java connector is completed


