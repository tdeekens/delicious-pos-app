package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.OrderTypeView;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderTypeDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("name");
		columnNames.add("price_id");
		columnNames.add("target");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<OrderTypeView> orderTypeViews = findAll();
		Object[][] orderTypes = new Object[orderTypeViews.size()][];
		
		for(int i = 0;i < orderTypeViews.size();i++) 
		{
			List<Object> orderType = new ArrayList<Object>();
			orderType.add(orderTypeViews.get(i).getName());
			orderType.add(orderTypeViews.get(i).getPriceId());
			orderType.add(orderTypeViews.get(i).getTarget());
			orderTypes[i] = orderType.toArray();
		}
		
		return orderTypes;
	}
	
	public ArrayList<OrderTypeView> findAll()
	{
		ArrayList<OrderTypeView> result = new ArrayList<OrderTypeView>();
		Statement statement = null;
	    
	    String query = "SELECT name, price_id, target ";
	    query += "FROM OrderTypes";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderTypeView orderType  = new OrderTypeView(resultSet.getString("name"), resultSet.getInt("price_id"), resultSet.getString("target"));
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
	    
	}
	
	public void insert(OrderTypeView orderType)
	{
		Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM OrderTypes");

	      uprs.moveToInsertRow();

	      uprs.updateString("name", orderType.getName());
	      uprs.updateInt("price_id", orderType.getPriceId());
	      uprs.updateString("target", orderType.getTarget());

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
	
	public void update(OrderTypeView orderType)
	{
		PreparedStatement stmt = null;
		String query = "Update OrderTypes " +
						"set name = ?" +
						", target = ?" +
						", price_id = ?" +
						" where name = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, orderType.getName());
			stmt.setString(2, orderType.getTarget());
			stmt.setInt(1, orderType.getPriceId());
			stmt.setString(1, orderType.getName());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) 
		{ 
			JDBCUtilities.printSQLException(e); 
		} 
		
		finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { JDBCUtilities.printSQLException(e); }
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
