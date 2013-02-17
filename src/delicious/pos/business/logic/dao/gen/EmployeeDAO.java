package delicious.pos.business.logic.dao.gen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import delicious.pos.business.logic.dao.BaseDAO;
import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.view.gen.EmployeeView;

public class EmployeeDAO extends BaseDAO
{
	public EmployeeDAO(Connection con, String dbName, String dbms)
	{
		super(con, dbName, dbms);
	}
	
	public ArrayList<EmployeeView> findAll()throws SQLException
	{
		ArrayList<EmployeeView> result = new ArrayList<EmployeeView>();
		Statement statement = null;
	    
	    String query = "SELECT userName, salary, phone, position";
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
	
	public void persist(EmployeeView employee) throws SQLException 
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
	    	  stmt.close(); 
	      }
	    }
	}
	
	public void remove(EmployeeView employee)
	{
		
	}
}
