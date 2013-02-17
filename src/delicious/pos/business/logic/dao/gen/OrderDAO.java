package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.gen.OrderView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO extends BaseDAO
{
	public OrderDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<OrderView> findAll()throws SQLException
	{
		ArrayList<OrderView> result = new ArrayList<OrderView>();
		Statement statement = null;
	    
	    String query = "SELECT id, orderType_name, employee_userName,Customer_id,Table_id";
	    query += "FROM Orders";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderView order  = new OrderView(resultSet.getInt("id"), resultSet.getString("orderType_name"), resultSet.getString("employee_userName"), resultSet.getInt("id"), resultSet.getInt("id"));
	          result.add(order);
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
	
	public void persist(OrderView order)
	{
		
	}
	
	public void remove(OrderView order)
	{
		
	}
}
