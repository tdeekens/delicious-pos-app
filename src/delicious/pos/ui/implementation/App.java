package delicious.pos.ui.implementation;

import java.awt.EventQueue;

public class App {
	public static MainScreen mainScreen;
	
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen = new MainScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
