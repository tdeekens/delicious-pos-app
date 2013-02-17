package delicious.pos.ui.components.order;

import javax.swing.BoxLayout;

import delicious.pos.business.logic.view.ItemView;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.extensions.UIScrollPane;
import delicious.pos.ui.components.menu.MenuItemPanel;
import delicious.pos.ui.screens.MenuScreen;

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

	public void removeOrderedItem(MenuItemPanel orderItemPanel, ItemView item, PriceView price) {
		this.parentPanel.removeItem(item, price);
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
