package delicious.pos.business.logic.dao.gen;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.EmployeeView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO extends BaseDAO
{
	public EmployeeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<EmployeeView> findAll()
	{
		ArrayList<EmployeeView> result = new ArrayList<EmployeeView>();
		Statement statement = null;
	    
	    String query = "SELECT userName, salary, phone, position ";
	    query += "FROM Employees";
	    
	    try 
	    {
	    	statement = getCon().createStatement();
	    	ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) 
	      {
	    	  EmployeeView employee  = new EmployeeView(resultSet.getString("userName"), resultSet.getFloat("salary"), resultSet.getString("phone"), resultSet.getString("position"));
	          result.add(employee);
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
	
	public void persist(EmployeeView employee)
	{
	    Statement stmt = null;
	    try 
	    {
	      stmt = getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet uprs = stmt.executeQuery("SELECT * FROM Employees");

	      uprs.moveToInsertRow();

	      uprs.updateString("userName", employee.getUserName());
	      uprs.updateFloat("salary", employee.getSalary());
	      uprs.updateString("phone", employee.getPhone());
	      uprs.updateString("position", employee.getPosition());

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
	
	public void remove(EmployeeView employee)
	{
	    PreparedStatement stmt = null;
	    
	    String query = "SELECT value FROM Employees ";
	    query += "WHERE userName = ?";
	    
	    try 
	    {
	      stmt = getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      stmt.setString(1, employee.getUserName());
	      
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
