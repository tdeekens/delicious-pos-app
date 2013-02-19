package delicious.pos.business.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import delicious.pos.business.logic.view.PriceView;

public class PriceDAO extends BaseDAO
{
	public PriceDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<PriceView> findAll()throws SQLException
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		Statement stmt = null;
	    
	    String query = "SELECT id, value, size_value, item_name ";
	    query += "FROM Prices";
	    
	    try 
	    {
	    	stmt = getCon().createStatement();
	    	ResultSet resultSet = stmt.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  PriceView price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
	          result.add(price);
	      }
	    } 
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    } 
	    finally 
	    {
	    	if (stmt != null) 
	    	{ 
	    		stmt.close(); 
	    	}
	    }
	    return result;
	}
	
	public void persist(PriceView price) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    	  stmt.close(); 
	      }
	    }
	}
	
	public void remove(PriceView price) throws SQLException
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Prices ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    	  stmt.close(); 
	      }
	    }
	}
	
	public ArrayList<PriceView> findByItemName(String itemName) throws SQLException
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		PreparedStatement stmt = null;
	    
	    String query = "SELECT * ";
	    query += " FROM Prices ";
	    query += "WHERE item_name = ?";
	    
	    try 
	    {
	    	stmt = getCon().prepareStatement(query);
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
	    	e.printStackTrace();
	    } 
	    finally 
	    {
	    	if (stmt != null) 
	    	{ 
	    		stmt.close(); 
	    	}
	    }
	    return result;
	}
	
	public ArrayList<PriceView> findById(Integer id) throws SQLException
	{
		ArrayList<PriceView> result = new ArrayList<PriceView>();
		PreparedStatement stmt = null;
	    
	    String query = "SELECT * ";
	    query += " FROM Prices ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	    	stmt = getCon().prepareStatement(query);
	    	stmt.setInt(1, id);
	    	ResultSet resultSet = stmt.executeQuery();

	      while (resultSet.next()) 
	      {
	    	  PriceView price  = new PriceView(resultSet.getInt("id"), resultSet.getFloat("value"), resultSet.getString("size_value"), resultSet.getString("item_name"));
	          result.add(price);
	      }
	    } 
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    } 
	    finally 
	    {
	    	if (stmt != null) 
	    	{ 
	    		stmt.close(); 
	    	}
	    }
	    return result;
	}
}