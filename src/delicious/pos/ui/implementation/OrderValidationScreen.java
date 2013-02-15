package delicious.pos.ui.implementation;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.order.OrderTypePanel;
import delicious.pos.ui.components.menu.order.OrderedItemsPanel;

public class OrderValidationScreen extends UIPanel {

	private OrderedItemsPanel orderItemsPanel;
	private OrderTypePanel orderTypePanel;

	public OrderValidationScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.orderItemsPanel = new OrderedItemsPanel();
		this.orderTypePanel = new OrderTypePanel();
		
		this.add(this.orderItemsPanel, BorderLayout.EAST);
		this.add(this.orderTypePanel, BorderLayout.CENTER);
	}
	
	public void renderChildren() {
		this.orderItemsPanel.renderOrderedItems();
	}
}
