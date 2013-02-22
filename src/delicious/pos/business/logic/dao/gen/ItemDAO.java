package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.ItemView;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("name");
		columnNames.add("description");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<ItemView> itemViews = findAll();
		Object[][] items = new Object[itemViews.size()][];
		
		for(int i = 0;i < itemViews.size();i++) 
		{
			List<Object> item = new ArrayList<Object>();
			item.add(itemViews.get(i).getName());
			item.add(itemViews.get(i).getDescription());
			items[i] = item.toArray();
		}
		
		return items;
	}
	
	public ArrayList<ItemView> findAll()
	{
		ArrayList<ItemView> result = new ArrayList<ItemView>();
		Statement statement = null;
	    
	    String query = "SELECT name, description ";
	    query += "FROM Items";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  ItemView item  = new ItemView(resultSet.getString("name"), resultSet.getString("description"));
	          result.add(item);
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
	
	public void persist(ItemView item)
	{
		if(rowCount(item.getName()) > 0) 
	    {
	    	this.update(item);
	    } else 
	    {
	    	this.insert(item);
	    }
	}
	
	public void update(ItemView item) {
		PreparedStatement stmt = null;
		String query = "Update Items " +
						"set name = ?" +
						", description = ?" +
						" where name = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, item.getName());
			stmt.setString(2, item.getDescription());
			stmt.setString(1, item.getName());
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
	
	public void insert(ItemView item) {
		Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Items");

	      uprs.moveToInsertRow();

	      uprs.updateString("name", item.getName());
	      uprs.updateString("description", item.getDescription());

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
	
	public void remove(ItemView item)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Items ";
	    query += "WHERE name = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setString(1, item.getName());
	      
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
	
	private int rowCount(String primaryKey)
	{
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String query = "SELECT COUNT(*) FROM Items WHERE name = ?";
	    int rowCount = -1;
	    
	    try 
	    {
	    	stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    stmt.setString(1, primaryKey);
		    
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
