package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.TableView;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("id");
		columnNames.add("number");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<TableView> tableViews = findAll();
		Object[][] tables = new Object[tableViews.size()][];
		
		for(int i = 0;i < tableViews.size();i++) 
		{
			List<Object> table = new ArrayList<Object>();
			table.add(tableViews.get(i).getId());
			table.add(tableViews.get(i).getNumber());
			tables[i] = table.toArray();
		}
		
		return tables;
	}
	
	public ArrayList<TableView> findAll()
	{
		ArrayList<TableView> result = new ArrayList<TableView>();
		Statement statement = null;
	    
	    String query = "SELECT id, number ";
	    query += "FROM Tables";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  TableView table  = new TableView(resultSet.getInt("id"), resultSet.getString("number"));
	          result.add(table);
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
	
	public void persist(TableView table)
	{
		if(rowCount(table.getId()) > 0) 
	    {
	    	this.update(table);
	    } else 
	    {
	    	this.insert(table);
	    }
	}
	
	public void update(TableView table)
	{
		PreparedStatement stmt = null;
		String query = "Update Tables " +
						"set number = ?" +
						" where id = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, table.getNumber());
			stmt.setInt(2, table.getId());
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
	
	public void insert(TableView table)
	{
		Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Tables");

	      uprs.moveToInsertRow();

	      uprs.updateInt("id", table.getId());
	      uprs.updateString("number", table.getNumber());

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
	
	public void remove(TableView table)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Tables ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setInt(1, table.getId());
	      
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
	    String query = "SELECT COUNT(*) FROM Tables WHERE id = ?";
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
