package delicious.pos.business.logic.dao.gen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.OrderTypeView;

public class OrderTypeDAO extends BaseDAO
{
	public OrderTypeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<OrderTypeView> findAll()throws SQLException
	{
		ArrayList<OrderTypeView> result = new ArrayList<OrderTypeView>();
		Statement statement = null;
	    
	    String query = "SELECT name, price_id ";
	    query += "FROM Items";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  OrderTypeView orderType  = new OrderTypeView(resultSet.getString("name"), resultSet.getInt("price_id"));
	          result.add(orderType);
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
	
	public void persist(OrderTypeView orderType) throws SQLException 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM OrderTypes");

	      uprs.moveToInsertRow();

	      uprs.updateString("name", orderType.getName());
	      uprs.updateInt("price_id", orderType.getPriceId());

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
	
	public void remove(OrderTypeView orderType)
	{
		
	}
}
