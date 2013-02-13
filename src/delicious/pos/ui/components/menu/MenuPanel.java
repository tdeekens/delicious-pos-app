package delicious.pos.ui.components.menu;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.implementation.MenuScreen;
import javax.swing.BoxLayout;

public class MenuPanel extends UIPanel {
	private MenuScreen parentPanel;
	
	public MenuPanel(MenuScreen menuScreen) {
		this.parentPanel = menuScreen;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.init();
	}
	
	public void init() {
	
	}
}
