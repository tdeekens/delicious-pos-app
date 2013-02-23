package delicious.pos.ui.screens;

import java.awt.BorderLayout;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.view.OrderedItemView;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.business.logic.view.gen.ItemView;
import delicious.pos.business.logic.view.gen.OrderView;
import delicious.pos.ui.components.customer.CustomerDetailPanel;
import delicious.pos.ui.components.extensions.UIInfoDialog;
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

	public void persistOrder() {
		/*OrderView orderView = new OrderView(
			null,
			App.orderState.getOrderType().getName(),
			"Kostas", //TODO not always kostas!
			App.orderState.getCustomer().getId(),
			0 //TODO how to set right target value?
		);
		
		for (Entry<PriceView, ItemView> item : App.orderState.getOrderedItems().entrySet()) {
			OrderedItemView orderedItem = new OrderedItemView(
				null,
				orderView.getId(),
				item.getValue().getName(),
				item.getKey().getId()
			);
		}*/
		
		new UIInfoDialog(App.labels.get("order-success"), App.labels.get("close-dialog"));
	}
}
