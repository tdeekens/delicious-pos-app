package delicious.pos.ui.components.extensions;

import javax.swing.JFrame;

import delicious.pos.ui.screens.MainScreen;

public class UIFrame extends JFrame {

	public UIFrame() {
		init();
	}

	private void init() {
		setVisible(true);
		setDefaultCloseOperation(UIFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
