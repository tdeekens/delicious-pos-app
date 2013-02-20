package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.CustomerView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO extends BaseDAO
{
	public ArrayList<CustomerView> findAll()
	{
		ArrayList<CustomerView> result = new ArrayList<CustomerView>();
		Statement statement = null;
	    
	    String query = "SELECT firstName, lastName, street, zip, city, phone ";
	    query += "FROM Customers";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  CustomerView customer  = new CustomerView(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("street"), resultSet.getString("zip"), resultSet.getString("city"), resultSet.getString("phone"));
	          result.add(customer);
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
	
	public void persist(CustomerView customer) 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Customers");

	      uprs.moveToInsertRow();

	      uprs.updateString("firstName", customer.getFirstName());
	      uprs.updateString("lastName", customer.getLastName());
	      uprs.updateString("street", customer.getStreet());
	      uprs.updateString("zip", customer.getZIP());
	      uprs.updateString("city", customer.getCity());
	      uprs.updateString("phone", customer.getPhone());

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
	
	public void remove(CustomerView customer)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Customers ";
	    query += "WHERE firstName = ?";
	    query += "  AND lastName = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setString(1, customer.getFirstName());
	      stmt.setString(2, customer.getLastName());
	      
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
