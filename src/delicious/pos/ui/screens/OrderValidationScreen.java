package delicious.pos.ui.screens;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.item.OrderedItemsPanel;
import delicious.pos.ui.components.order.type.OrderTypePanel;

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
