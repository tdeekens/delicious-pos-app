package delicious.pos.business.logic.dao;

import java.sql.Connection;
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
		Statement statement = null;
	    
	    String query = "SELECT id, value, size_value, item_name ";
	    query += "FROM Prices";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

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
	    	if (statement != null) 
	    	{ 
	    		statement.close(); 
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
	
	public void remove(PriceView price)
	{
		
	}
}