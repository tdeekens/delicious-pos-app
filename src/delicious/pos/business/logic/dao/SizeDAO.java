package delicious.pos.business.logic.dao;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.SizeView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SizeDAO extends BaseDAO
{
	public SizeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<SizeView> findAll()throws SQLException
	{
		ArrayList<SizeView> result = new ArrayList<SizeView>();
		Statement statement = null;
	    
	    String query = "SELECT value ";
	    query += "FROM Sizes";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  SizeView size  = new SizeView(resultSet.getString("value"));
	          result.add(size);
	      }
	    } 
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    } 
	    finally 
	    {
	    	if (statement != null) 
	    	{ 
	    		statement.close(); 
	    	}
	    }
	    return result;
	}
	
	public void persist(SizeView size)
	{
		
	}
	
	public void remove(SizeView size)
	{
		
	}
}