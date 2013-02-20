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
	public static String dbms;
	
	public static String dbName; 
	
	public static String userName;
	
	public static String password;
	
	private static String propertiesFileName = "./././././properties/javadb-pos-properties.xml";
	
	public JDBCUtilities()
	{
		this.setProperties(propertiesFileName);
	}
	  
	private void setProperties(String fileName)
	{
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.loadFromXML(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dbms = prop.getProperty("dbms");
		dbName = prop.getProperty("database_name");
		userName = prop.getProperty("user_name");
		password = prop.getProperty("password");
	}
	
	public static Connection getConnection() throws SQLException
	{ 
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		if (dbms.equals("derby")) 
		{
			String urlString = "jdbc:" + dbms + ":" + dbName;
			conn = DriverManager.getConnection(urlString + ";create=true", connectionProps);    
		}
		return conn;
	}
	
	public static void closeConnection(Connection connArg) 
	{
		try 
		{
			if (connArg != null) 
			{
				connArg.close();
		        connArg = null;
		    }
		} 
		catch (SQLException sqle)
		{
		      printSQLException(sqle);
		   
		} 
	}
	
	public static void printSQLException(SQLException ex) 
	{
		for (Throwable e : ex) 
		{
			if (e instanceof SQLException) 
			{
				if (ignoreSQLException(((SQLException)e).getSQLState()) == false) 
				{
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException)e).getSQLState());
					System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) 
					{
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
		        }
			}
		}
	}
	
	  public static boolean ignoreSQLException(String sqlState) 
	  {
		  if (sqlState == null) 
		  {
		      System.out.println("The SQL state is not defined!");
		      return false;
		  }
		  // X0Y32: Jar file already exists in schema
		  if (sqlState.equalsIgnoreCase("X0Y32"))
		      return true;
		    // 42Y55: Table already exists in schema
		  if (sqlState.equalsIgnoreCase("42Y55"))
			  return true;
		  return false;
	}
}
