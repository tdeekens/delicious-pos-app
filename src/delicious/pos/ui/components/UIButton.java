package delicious.pos.ui.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UIButton extends JButton {
	public UIButton(String string) {
		super(string);
		
		this.init();
	}
	
	public UIButton() {		
		this.init();
	}
	
	private void init() {
		
	}
	
	public void setIcon(String icon) {
		super.setIcon(new ImageIcon(this.getClass().getResource("icons/" + icon + ".png")));
	}
}
