package delicious.pos.business.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.business.logic.dao.SizeDAO;
import delicious.pos.business.logic.view.SizeView;

public class Test {

	public static void main(String[] args) 
	{
	    JDBCUtilities myJDBCUtilities;
	    Connection myConnection = null;

	    try 
	    {
	    	myJDBCUtilities = new JDBCUtilities();
	    } 
	    catch (Exception e) 
	    {
	    	System.err.println("Problem reading properties file ");
	        e.printStackTrace();
	        return;
	    }

	    try 
	    {
	      myConnection = myJDBCUtilities.getConnection();

	      SizeDAO sizeDAO = new SizeDAO(myConnection, myJDBCUtilities.dbName,
	                         myJDBCUtilities.dbms);

	      // test findAll()
	      ArrayList<SizeView> sizes = sizeDAO.findAll();
	      
    	  System.out.println("SIZES:");
	      for(SizeView size : sizes)
	      {
	    	  System.out.println(size.getValue());
	      }
	      
	      // test persist()
	      SizeView sizeXLarge = new SizeView("XLarge");
	      sizeDAO.persist(sizeXLarge);
	      
    	  System.out.println("SIZES:");
	      for(SizeView size : sizes)
	      {
	    	  System.out.println(size.getValue());
	      }
	    } 
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    } 
	    finally 
	    {
	      JDBCUtilities.closeConnection(myConnection);
	    }
	}
	
	private void testFindAllSizes()
	{
		
	}
}
