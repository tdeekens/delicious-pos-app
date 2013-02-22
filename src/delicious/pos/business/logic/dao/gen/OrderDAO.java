package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.OrderView;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("id");
		columnNames.add("orderType_name");
		columnNames.add("employee_userName");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<OrderView> orderViews = findAll();
		Object[][] orders = new Object[orderViews.size()][];
		
		for(int i = 0;i < orderViews.size();i++) 
		{
			List<Object> order = new ArrayList<Object>();
			order.add(orderViews.get(i).getId());
			order.add(orderViews.get(i).getOrderTypeName());
			order.add(orderViews.get(i).getEmployeeUserName());
			orders[i] = order.toArray();
		}
		
		return orders;
	}
	
	public ArrayList<OrderView> findAll()
	{
		ArrayList<OrderView> result = new ArrayList<OrderView>();
		Statement statement = null;
	    
	    String query = "SELECT id, orderType_name, employee_userName";
	    query += "FROM Orders";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderView order  = new OrderView(resultSet.getInt("id"), resultSet.getString("orderType_name"), resultSet.getString("employee_userName"), resultSet.getInt("id"), resultSet.getInt("id"));
	          result.add(order);
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
	
	public void persist(OrderView order) 
	{
		if(rowCount(order.getId()) > 0) 
	    {
	    	this.update(order);
	    } else 
	    {
	    	this.insert(order);
	    }
	}

	public void update(OrderView order)
	{
		PreparedStatement stmt = null;
		String query = "Update Orders " +
						"set order_type_name = ?" +
						", employee_userName = ?" +
						" where id = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, order.getOrderTypeName());
			stmt.setString(2, order.getEmployeeUserName());
			stmt.setInt(1, order.getId());
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

	public void insert(OrderView order) 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}
	
	public void remove(OrderView order)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Orders ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}

	private int rowCount(Integer primaryKey)
	{
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String query = "SELECT COUNT(*) FROM Orders WHERE id = ?";
	    int rowCount = -1;
	    
	    try 
	    {
	    	stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    stmt.setInt(1, primaryKey);
		    
		    rs = stmt.executeQuery();

	    	rs.next();
	    	rowCount = rs.getInt(1);
	    } catch (SQLException e) {
	    	JDBCUtilities.printSQLException(e);
		} finally 
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
	    
	    return rowCount;		
	}
}
