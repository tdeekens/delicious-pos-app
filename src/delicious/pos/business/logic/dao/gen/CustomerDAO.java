package delicious.pos.business.logic.dao.gen;

import delicious.pos.App;
import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.CustomerView;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO extends BaseDAO
{
	public Object[] getColumnNames()
	{
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("id");
		columnNames.add("firstName");
		columnNames.add("lastName");
		columnNames.add("street");
		columnNames.add("zip");
		columnNames.add("city");
		columnNames.add("phone");
		return columnNames.toArray();
	}
	
	public Object[][] getAllAsArray()
	{
		ArrayList<CustomerView> customerViews = findAll();
		Object[][] customers = new Object[customerViews.size()][];
		
		for(int i = 0;i < customerViews.size();i++) 
		{
			List<Object> customer = new ArrayList<Object>();
			customer.add(customerViews.get(i).getId());
			customer.add(customerViews.get(i).getFirstName());
			customer.add(customerViews.get(i).getLastName());
			customer.add(customerViews.get(i).getStreet());
			customer.add(customerViews.get(i).getZIP());
			customer.add(customerViews.get(i).getCity());
			customer.add(customerViews.get(i).getPhone());
			customers[i] = customer.toArray();
		}
		
		return customers;
	}

	public ArrayList<CustomerView> findAll()
	{
		ArrayList<CustomerView> result = new ArrayList<CustomerView>();
		Statement statement = null;
	    
	    String query = "SELECT id, firstName, lastName, street, zip, city, phone ";
	    query += "FROM Customers";
	    
	    try 
	    {
	    	statement = App.DBConnection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  CustomerView customer  = new CustomerView(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("street"), resultSet.getString("zip"), resultSet.getString("city"), resultSet.getString("phone"));
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
	    if(rowCount(customer.getId()) > 0) 
	    {
	    	this.update(customer);
	    } else 
	    {
	    	this.insert(customer);
	    }
	}
	
	public void insert(CustomerView customer) 
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = App.DBConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Customers");

	      uprs.moveToInsertRow();
			
		  uprs.updateInt("id", customer.getId());
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

	public void update(CustomerView customer)
	{
		PreparedStatement stmt = null;
		String query = "Update Customers set " +
						"id = ?" +
						"firstName = ?" +
						", lastName = ?" +
				      	", street = ?" +
				      	", zip = ?" +
				      	", city = ?" +
				      	", phone = ?" +
						" where id = ?";
		
		try {
			stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getLastName());

	      	stmt.setString(1 + 3, customer.getStreet());
	      	stmt.setString(2 + 3, customer.getZIP());
	      	stmt.setString(3 + 3, customer.getCity());
	      	stmt.setString(4 + 3, customer.getPhone());

			stmt.setInt(4 + 4, customer.getId());

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
	
	public void remove(CustomerView customer)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Customers ";
	    query += "WHERE id = ?";
	    
	    try 
	    {
	      stmt = App.DBConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setInt(1, customer.getId());
	      
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
	    String query = "SELECT COUNT(*) FROM Customers WHERE id = ?";
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
