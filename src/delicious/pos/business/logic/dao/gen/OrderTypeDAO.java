package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.OrderTypeView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderTypeDAO extends BaseDAO
{
	public ArrayList<OrderTypeView> findAll()
	{
		ArrayList<OrderTypeView> result = new ArrayList<OrderTypeView>();
		Statement statement = null;
	    
	    String query = "SELECT name, price_id ";
	    query += "FROM OrderTypes";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderTypeView orderType  = new OrderTypeView(resultSet.getString("name"), resultSet.getInt("price_id"));
	          result.add(orderType);
	      }
	    } 
	    catch (SQLException e) 
	    {
			JDBCUtilities.printSQLException(e);
	    } 
	    finally 
	    {
	    	if (statement != null) 
	    	{ 
	    		try {
					statement.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				} 
	    	}
	    }
	    return result;
	}
	
	public void persist(OrderTypeView orderType)
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM OrderTypes");

	      uprs.moveToInsertRow();

	      uprs.updateString("name", orderType.getName());
	      uprs.updateInt("price_id", orderType.getPriceId());

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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}
	
	public void remove(OrderTypeView orderType)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM OrderTypes ";
	    query += "WHERE name = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setString(1, orderType.getName());
	      
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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}
}
