package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager{
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
	Properties properties=null;
	properties=loadPropertiesFile();
	
	String driver=properties.getProperty("driver");
	String url=properties.getProperty("url");
	String username=properties.getProperty("username");
	String pass=properties.getProperty("password");
	
	Connection con=null;
	
	Class.forName(driver);
	con=DriverManager.getConnection(url,username,pass);
	
	return con;
	}
	public static Properties loadPropertiesFile() throws IOException {
		Properties prop=new Properties();
		InputStream input=ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(input);
		input.close();
		return prop;
		
	}
}




