package delicious.pos.business.logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import delicious.pos.App;
import delicious.pos.business.logic.view.SizeView;

public class SizeDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("value");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<SizeView> sizeViews = findAll();
		Object[][] sizes = new Object[sizeViews.size()][];
		
		for(int i = 0;i < sizeViews.size();i++) 
		{
			List<Object> size = new ArrayList<Object>();
			size.add(sizeViews.get(i).getValue());
			sizes[i] = size.toArray();
		}
		
		return sizes;
	}
	
	public ArrayList<SizeView> findAll()
	{
		ArrayList<SizeView> result = new ArrayList<SizeView>();
		Statement stmt = null;
	    
	    String query = "SELECT value ";
	    query += "FROM Sizes";
	    
	    try 
	    {
	    	stmt = App.DBConnection.createStatement();
	    	ResultSet resultSet = stmt.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  SizeView size  = new SizeView(resultSet.getString("value"));
	          result.add(size);
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
	
	public void persist(SizeView size)
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}
	
	public void remove(SizeView size)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Sizes ";
	    query += "WHERE value = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    		try {
					stmt.close();
				} catch (SQLException e) {
					JDBCUtilities.printSQLException(e);
				}
	      }
	    }
	}
}