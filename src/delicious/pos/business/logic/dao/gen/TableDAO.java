package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableDAO extends BaseDAO
{
	public TableDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<TableView> findAll()throws SQLException
	{
		ArrayList<TableView> result = new ArrayList<TableView>();
		Statement statement = null;
	    
	    String query = "SELECT id, number ";
	    query += "FROM Tables";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  TableView table  = new TableView(resultSet.getInt("id"), resultSet.getString("number"));
	          result.add(table);
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
	
	public void persist(TableView table) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	    	  stmt.close(); 
	      }
	    }
	}
	
	public void remove(TableView table)
	{
		
	}
}
