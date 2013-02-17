package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.gen.OrderTypeView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderTypeDAO extends BaseDAO
{
	public OrderTypeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<OrderTypeView> findAll()throws SQLException
	{
		ArrayList<OrderTypeView> result = new ArrayList<OrderTypeView>();
		Statement statement = null;
	    
	    String query = "SELECT name, price_id ";
	    query += "FROM Items";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderTypeView orderType  = new OrderTypeView(resultSet.getString("name"), resultSet.getInt("price_id"));
	          result.add(orderType);
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
	
	public void persist(OrderTypeView orderType)
	{
		
	}
	
	public void remove(OrderTypeView orderType)
	{
		
	}
}
