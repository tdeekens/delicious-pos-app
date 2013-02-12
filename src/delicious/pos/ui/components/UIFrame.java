package delicious.pos.ui.components;

import javax.swing.JFrame;

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
