package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.gen.ItemView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAO extends BaseDAO
{
	public ItemDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<ItemView> findAll()throws SQLException
	{
		ArrayList<ItemView> result = new ArrayList<ItemView>();
		Statement statement = null;
	    
	    String query = "SELECT name, price_id, description";
	    query += "FROM Items";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  ItemView item  = new ItemView(resultSet.getString("name"), resultSet.getInt("price_id"), resultSet.getString("description"));
	          result.add(item);
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
	
	public void persist(ItemView item)
	{
		
	}
	
	public void remove(ItemView item)
	{
		
	}
}
