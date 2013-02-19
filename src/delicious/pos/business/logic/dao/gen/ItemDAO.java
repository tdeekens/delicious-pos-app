package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.ItemView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAO extends BaseDAO
{
	public ItemDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<ItemView> findAll()throws SQLException
	{
		ArrayList<ItemView> result = new ArrayList<ItemView>();
		Statement statement = null;
	    
	    String query = "SELECT name, description ";
	    query += "FROM Items";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  ItemView item  = new ItemView(resultSet.getString("name"), resultSet.getString("description"));
	          result.add(item);
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
	
	public void persist(ItemView item) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    	  stmt.close(); 
	      }
	    }
	}
	
	public void remove(ItemView item) throws SQLException
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Items ";
	    query += "WHERE name = ?";
	    
	    try 
	    {
	      stmt = getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    	  stmt.close(); 
	      }
	    }
	}
}
