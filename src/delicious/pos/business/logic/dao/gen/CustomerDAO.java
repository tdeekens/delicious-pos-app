package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.view.gen.CustomerView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO extends BaseDAO
{
	public CustomerDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<CustomerView> findAll()throws SQLException
	{
		ArrayList<CustomerView> result = new ArrayList<CustomerView>();
		Statement statement = null;
	    
	    String query = "SELECT firstName, lastName, street, zip, city, phone";
	    query += "FROM Customers";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  CustomerView customer  = new CustomerView(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("street"), resultSet.getString("zip"), resultSet.getString("city"), resultSet.getString("phone"));
	          result.add(customer);
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
	
	public void persist(CustomerView customer)
	{
		
	}
	
	public void remove(CustomerView customer)
	{
		
	}
}
