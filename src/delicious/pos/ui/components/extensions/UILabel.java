package delicious.pos.ui.components.extensions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UILabel extends JLabel {

	public UILabel(String string) {
		super(string);
		init();
	}
	
	public UILabel() {
		init();
	}
	
	public UILabel(ImageIcon icon) {
		super(icon);
		init();
	}

	private void init() {
	}

}
