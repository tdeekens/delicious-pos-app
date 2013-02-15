package delicious.pos.ui.implementation.screens;

import java.awt.BorderLayout;

import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.OrderedItemsPanel;

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
