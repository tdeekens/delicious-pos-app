package delicious.pos.business.logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import delicious.pos.App;
import delicious.pos.business.logic.view.OrderedItemView;

public class OrderedItemDAO extends BaseDAO 
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("id");
		columnNames.add("order_id");
		columnNames.add("item_name");
		columnNames.add("price_id");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<OrderedItemView> orderedItemViews = findAll();
		Object[][] orderedItems = new Object[orderedItemViews.size()][];
		
		for(int i = 0;i < orderedItemViews.size();i++) 
		{
			List<Object> orderedItem = new ArrayList<Object>();
			orderedItem.add(orderedItemViews.get(i).getId());
			orderedItem.add(orderedItemViews.get(i).getOrderId());
			orderedItem.add(orderedItemViews.get(i).getItemName());
			orderedItem.add(orderedItemViews.get(i).getPriceId());
			orderedItems[i] = orderedItem.toArray();
		}
		
		return orderedItems;
	}
	
	public ArrayList<OrderedItemView> findAll()
	{
		ArrayList<OrderedItemView> result = new ArrayList<OrderedItemView>();
		Statement stmt = null;
	    
	    String query = "SELECT id, order_id, item_name, price_id ";
	    query += "FROM OrderedItems";
	    
	    try 
	    {
	    	stmt = App.DBConnection.createStatement();
	    	ResultSet resultSet = stmt.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderedItemView orderedItem  = new OrderedItemView(resultSet.getInt("id"),resultSet.getInt("order_id"),resultSet.getString("item_name"),resultSet.getInt("price_id"));
	          result.add(orderedItem);
	      }
	    } 
	    catch (SQLException e) 
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
	    return result;
	}
	
	public void persist(OrderedItemView orderedItem)
	{
	   
	}
	
	public void update(OrderedItemView orderedItem)
	{
		PreparedStatement stmt = null;
		String query = "Update OrderedItems " +
						"set order_id = ?" +
						", item_name = ?" +
						", price_id = ?" +
						" where id = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, orderedItem.getOrderId());
			stmt.setString(2, orderedItem.getItemName());
			stmt.setInt(1, orderedItem.getPriceId());
			stmt.setInt(1, orderedItem.getId());
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
	
	public void insert(OrderedItemView orderedItem)
	{
		 Statement stmt = null;
		    try 
		    {
		      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		      ResultSet uprs = stmt.executeQuery("SELECT * FROM OrderedItems");

		      uprs.moveToInsertRow();

		      uprs.updateInt("id", orderedItem.getId());
		      uprs.updateInt("order_id", orderedItem.getOrderId());
		      uprs.updateString("item_name", orderedItem.getItemName());
		      uprs.updateInt("price_id", orderedItem.getPriceId());

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
	
	public void remove(OrderedItemView orderedItem)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM OrderedItems ";
	    query += "WHERE value = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setInt(1, orderedItem.getId());
	      
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
