package delicious.pos.ui.screens;

import java.awt.BorderLayout;

import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.OrderTypePanel;
import delicious.pos.ui.components.order.OrderedItemsPanel;

public class OrderValidationScreen extends UIPanel {

	private OrderedItemsPanel orderItemsPanel;
	private OrderTypePanel orderTypePanel;

	public OrderValidationScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.orderItemsPanel = new OrderedItemsPanel();
		this.orderTypePanel = new OrderTypePanel(this);
		
		this.add(this.orderItemsPanel, BorderLayout.CENTER);
		this.add(this.orderTypePanel, BorderLayout.WEST);
	}
	
	public void renderChildren() {
		this.orderItemsPanel.renderOrderedItems();
	}
	
	public void onOrderTypeSet() {
		this.orderItemsPanel.renderOrderSummary();
	}
}
