package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.OrderView;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	    
	    String query = "SELECT id, orderType_name, employee_userName,Customer_id,Table_id ";
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
	
	public void persist(OrderView order) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Orders");

	      uprs.moveToInsertRow();

	      uprs.updateInt("id", order.getId());
	      uprs.updateString("orderType_name", order.getOrderTypeName());
	      uprs.updateString("employee_userName", order.getEmployeeUserName());

	      uprs.insertRow();
	      uprs.beforeFirst();

	    } catch (SQLException e) 
	    {
	      JDBCUtilities.printSQLException(e);
	    } 
	    finally 
	    {
	      if (stmt != null) 
	      { 
	    	  stmt.close(); 
	      }
	    }
	}
	
	public void remove(OrderView order) throws SQLException
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Orders ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setInt(1, order.getId());
	      
	      ResultSet uprs = stmt.executeQuery();

	      while (uprs.next()) 
	      {
	    	  uprs.deleteRow();
	      }

	    } catch (SQLException e) 
	    {
	      JDBCUtilities.printSQLException(e);
	    } 
	    finally 
	    {
	      if (stmt != null) 
	      { 
	    	  stmt.close(); 
	      }
	    }
	}
}
