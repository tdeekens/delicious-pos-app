package delicious.pos.ui.screens;

import java.awt.BorderLayout;

import delicious.pos.App;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.ui.components.customer.CustomerDetailPanel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.item.OrderedItemsPanel;

public class OrderPlacementScreen extends UIPanel {

	private OrderedItemsPanel orderItemsPanel;
	private CustomerDetailPanel customerDetailPanel;

	public OrderPlacementScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.orderItemsPanel = new OrderedItemsPanel();
		this.customerDetailPanel = new CustomerDetailPanel();
		
		this.add(this.customerDetailPanel, BorderLayout.WEST);
		this.add(this.orderItemsPanel, BorderLayout.CENTER);
	}
	
	public void renderChildren() {
		this.orderItemsPanel.renderOrderedItems();
		this.customerDetailPanel.renderCustomerDetails();
	}
	
	public void setCustomer(CustomerView customer) {
		this.customerDetailPanel.setCustomer(customer);
	}
}
