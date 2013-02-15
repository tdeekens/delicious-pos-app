package delicious.pos.ui.implementation;

import java.awt.EventQueue;

import delicious.pos.state.ApplicationState;
import delicious.pos.ui.implementation.screens.MainScreen;

public class App {
	public static MainScreen mainScreen;
	public static ApplicationState orderState;
	
	public static void put(String string) {
		System.out.println(string);
	}
	
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen = new MainScreen();
					orderState = new ApplicationState();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
