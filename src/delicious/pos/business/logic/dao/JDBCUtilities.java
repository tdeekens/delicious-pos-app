package delicious.pos.business.logic.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class JDBCUtilities 
{
	public String dbms;
	
	public String jarFile;
	
	public String dbName; 
	
	public String userName;
	
	public String password;
	
	public String urlString;
	  
	private String driver;
	
	private String serverName;
	
	private int portNumber;
	
	private Properties prop;
	
	private static String propertiesFileName = "..\\..\\..\\..\\..\\properties\\javadb-pos-properties.xml";
	
	public JDBCUtilities() throws FileNotFoundException, IOException, InvalidPropertiesFormatException 
	{
		this.setProperties(propertiesFileName);
	}
	  
	private void setProperties(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException 
	{
		this.prop = new Properties();
		FileInputStream fis = new FileInputStream(fileName);
		prop.loadFromXML(fis);
		
		this.dbms = this.prop.getProperty("dbms");
		this.jarFile = this.prop.getProperty("jar_file");
		this.driver = this.prop.getProperty("driver");
		this.dbName = this.prop.getProperty("database_name");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer.parseInt(this.prop.getProperty("port_number"));
	}
	
	public Connection getConnection() throws SQLException
	{ 
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		if (this.dbms.equals("derby")) 
		{
			this.urlString = "jdbc:" + this.dbms + ":" + this.dbName;
			conn = DriverManager.getConnection(this.urlString + ";create=true", connectionProps);    
		}
		return conn;
	}
}
