package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.TableView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableDAO extends BaseDAO
{
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
}
