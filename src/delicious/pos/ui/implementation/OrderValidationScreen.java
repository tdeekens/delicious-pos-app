package delicious.pos.ui.implementation;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.order.OrderTypePanel;
import delicious.pos.ui.components.menu.order.OrderedItemsPanel;

public class OrderValidationScreen extends UIPanel {

	private JSplitPane splitPane;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;

	public OrderValidationScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.add(new OrderedItemsPanel(), BorderLayout.EAST);
		this.add(new OrderTypePanel(), BorderLayout.CENTER);
	}
}
