package delicious.pos.ui.components.menu;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.item.MenuItemPanel;
import delicious.pos.ui.implementation.MenuScreen;
import javax.swing.BoxLayout;

public class OrderPanel extends UIPanel {
	private MenuScreen parentPanel;
	
	public OrderPanel(MenuScreen menuScreen) {
		this.parentPanel = menuScreen;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.init();
	}
	
	public void init() {
		
	}

	public void addItem(MenuItemPanel orderItemPanel) {
		this.add(orderItemPanel);
		this.validate();
	}
}
