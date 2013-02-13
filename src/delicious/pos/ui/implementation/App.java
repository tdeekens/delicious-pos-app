package delicious.pos.ui.implementation;

import java.awt.EventQueue;

import com.adamtaft.eb.EventBus;
import com.adamtaft.eb.EventBusService;

public class App {
	public static MenuScreen menuScreen;
	
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuScreen = new MenuScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
