package delicious.pos.ui.components.extensions;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import delicious.pos.util.ImageLoader;

public class UIButton extends JButton {
	public UIButton(String string) {
		super(string);
		
		this.init();
	}
	
	public UIButton(ImageIcon imageIcon) {
		super(imageIcon);
		
		this.init();
	}
	
	public UIButton() {		
		this.init();
	}
	
	private void init() {
	}
	
	public void setIcon(String icon) {
		super.setIcon(ImageLoader.loadImageIcon(this, icon));
	}
}
