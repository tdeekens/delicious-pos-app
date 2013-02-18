package delicious.pos.business.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import delicious.pos.business.logic.view.SizeView;

public class SizeDAO extends BaseDAO
{
	public SizeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<SizeView> findAll()throws SQLException
	{
		ArrayList<SizeView> result = new ArrayList<SizeView>();
		Statement stmt = null;
	    
	    String query = "SELECT value ";
	    query += "FROM Sizes";
	    
	    try 
	    {
	    	stmt = getCon().createStatement();
	    	ResultSet resultSet = stmt.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  SizeView size  = new SizeView(resultSet.getString("value"));
	          result.add(size);
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
	
	public void persist(SizeView size) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Sizes");

	      uprs.moveToInsertRow();

	      uprs.updateString("value", size.getValue());

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
	
	public void remove(SizeView size) throws SQLException
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Sizes ";
	    query += "WHERE value = ?";
	    
	    try 
	    {
	      stmt = getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setString(1, size.getValue());
	      
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