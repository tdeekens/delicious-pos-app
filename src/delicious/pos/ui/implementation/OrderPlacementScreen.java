package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.UIManager;

import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.order.OrderedItemsPanel;

public class OrderPlacementScreen extends UIPanel {

	private OrderedItemsPanel orderItemsPanel;

	public OrderPlacementScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.orderItemsPanel = new OrderedItemsPanel();
		
		this.add(this.orderItemsPanel, BorderLayout.CENTER);
	}
	
	public void renderChildren() {
		this.orderItemsPanel.renderOrderedItems();
	}
}
