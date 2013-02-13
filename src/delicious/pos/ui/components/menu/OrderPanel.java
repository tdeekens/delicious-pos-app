package delicious.pos.ui.components.menu;

import javax.swing.BoxLayout;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.UIScrollPane;
import delicious.pos.ui.components.menu.item.MenuItemPanel;
import delicious.pos.ui.implementation.MenuScreen;

public class OrderPanel extends UIPanel {
	private MenuScreen parentPanel;
	private UIScrollPane scrollPane;
	
	public OrderPanel(MenuScreen menuScreen) {
		this.parentPanel = menuScreen;
		this.init();
	}
	
	public void init() {
		scrollPane = new UIScrollPane(this);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void addItem(MenuItemPanel orderItemPanel) {
		this.add(orderItemPanel);
		this.redraw();
	}

	public void removeOrderedItem(MenuItemPanel orderItemPanel) {
		this.remove(orderItemPanel);
		this.redraw();
	}
	
	public UIScrollPane getScrollPane() {
		return this.scrollPane;
	}
	
	private void redraw() {
		this.scrollPane.validate();
		this.scrollPane.repaint();
		this.validate();
		this.repaint();
	}
}
