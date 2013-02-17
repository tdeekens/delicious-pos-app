package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
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
	
	public void persist(TableView table)
	{
		
	}
	
	public void remove(TableView table)
	{
		
	}
}
