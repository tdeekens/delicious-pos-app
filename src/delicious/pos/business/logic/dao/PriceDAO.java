package delicious.pos.business.logic.dao;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.PriceView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PriceDAO extends BaseDAO
{
	public PriceDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<PriceView> findAll()throws SQLException
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		Statement statement = null;
	    
	    String query = "SELECT id, value, size_value, item_name ";
	    query += "FROM Prices";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  PriceView price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
	          result.add(price);
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
	
	public void persist(PriceView price)
	{
		
	}
	
	public void remove(PriceView price)
	{
		
	}
}