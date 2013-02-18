package delicious.pos;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import delicious.pos.business.logic.dao.JDBCUtilities;
import delicious.pos.config.Labels;
import delicious.pos.state.ApplicationState;
import delicious.pos.ui.screens.MainScreen;

public class App {
	public static MainScreen mainScreen;
	public static ApplicationState orderState;
	public static Labels labels;
	public static Connection DBConnection;
	public static JDBCUtilities JDBCUtilities;
	
	public static void put(String string) {
		System.out.println(string);
	}
	
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupDatabase();
					labels = new Labels();
					orderState = new ApplicationState();
					mainScreen = new MainScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void setupDatabase() {
		try {
			JDBCUtilities = new JDBCUtilities();
	    } catch (Exception e) {
	    	App.put("Problem reading properties file ");
	        e.printStackTrace();
	        return;
	    }
		
		try {
			DBConnection = JDBCUtilities.getConnection();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	      JDBCUtilities.closeConnection(DBConnection);
	    }
	}
}
