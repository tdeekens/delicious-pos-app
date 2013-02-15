package delicious.pos.ui.implementation;

import java.awt.EventQueue;

import delicious.pos.state.OrderState;

public class App {
	public static MainScreen mainScreen;
	public static OrderState orderState;
	
	public static void put(String string) {
		System.out.println(string);
	}
	
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen = new MainScreen();
					orderState = new OrderState();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
