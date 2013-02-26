package delicious.pos.business.logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import delicious.pos.App;
import delicious.pos.business.logic.view.PriceView;

public class PriceDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("id");
		columnNames.add("value");
		columnNames.add("size_value");
		columnNames.add("item_name");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<PriceView> priceViews = findAll();
		Object[][] prices = new Object[priceViews.size()][];
		
		for(int i = 0;i < priceViews.size();i++) 
		{
			List<Object> price = new ArrayList<Object>();
			price.add(priceViews.get(i).getId());
			price.add(priceViews.get(i).getValue());
			price.add(priceViews.get(i).getSizeValue());
			price.add(priceViews.get(i).getItemName());
			prices[i] = price.toArray();
		}
		
		return prices;
	}
	
	public ArrayList<PriceView> findAll()
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		Statement stmt = null;
	    
	    String query = "SELECT id, value, size_value, item_name ";
	    query += "FROM Prices";
	    
	    try 
	    {
	    	stmt = App.DBConnection.createStatement();
	    	ResultSet resultSet = stmt.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  PriceView price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
	          result.add(price);
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
	    		try 
	    		{
					stmt.close();
				} 
	    		catch (SQLException e) 
	    		{
	    			JDBCUtilities.printSQLException(e);
				} 
	    	}
	    }
	    return result;
	}
	
	public void persist(PriceView price)
	{
		if(rowCount(price.getId()) > 0) 
	    {
	    	this.update(price);
	    } else 
	    {
	    	this.insert(price);
	    } 
	}
	
	public void update(PriceView price)
	{
		PreparedStatement stmt = null;
		String query = "Update Prices " +
						"set value = ?" +
						", size_value = ?" +
						", item_name = ?" +
						" where id = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setFloat(1, price.getValue());
			stmt.setString(2, price.getSizeValue());
			stmt.setString(3, price.getItemName());
			stmt.setInt(4, price.getId());
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
	
	public void insert(PriceView price)
	{
		Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Prices");

	      uprs.moveToInsertRow();

	      uprs.updateInt("id", price.getId());
	      uprs.updateFloat("value", price.getValue());
	      uprs.updateString("size_value", price.getSizeValue());
	      uprs.updateString("item_name", price.getItemName());

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
	    	  try 
	    	  {
				stmt.close();
	    	  } 
	    	  catch (SQLException e) 
	    	  {
	    		  JDBCUtilities.printSQLException(e);
	    	  } 
	      }
	    }
	}
	
	public void remove(PriceView price)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Prices ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setInt(1, price.getId());
	      
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
	    	  try 
	    	  {
				stmt.close();
	    	  } 
	    	  catch (SQLException e) 
	    	  {
	    		  JDBCUtilities.printSQLException(e);
	    	  } 
	      }
	    }
	}
	
	public ArrayList<PriceView> findByItemName(String itemName)
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		PreparedStatement stmt = null;
	    
	    String query = "SELECT * ";
	    query += " FROM Prices ";
	    query += "WHERE item_name = ?";
	    
	    try 
	    {
	    	stmt = App.DBConnection.prepareStatement(query);
	    	stmt.setString(1, itemName);
	    	ResultSet resultSet = stmt.executeQuery();

	      while (resultSet.next()) 
	      {
	    	  PriceView price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
	          result.add(price);
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
	
	public PriceView findById(Integer id)
	{
		PreparedStatement stmt = null;
	    PriceView price = null;
	    
	    String query = "SELECT * ";
	    query += " FROM Prices ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	    	stmt = App.DBConnection.prepareStatement(query);
	    	stmt.setInt(1, id);
	    	ResultSet resultSet = stmt.executeQuery();


	      while (resultSet.next()) 
	      {
	    	  price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
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
	    		try 
	    		{
					stmt.close();
				} 
	    		catch (SQLException e) 
	    		{
	    			JDBCUtilities.printSQLException(e);
				} 
	    	}
	    }
	    return price;
	}
	
	private int rowCount(Integer primaryKey)
	{
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String query = "SELECT COUNT(*) FROM Prices WHERE id = ?";
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