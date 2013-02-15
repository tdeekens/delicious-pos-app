package delicious.pos.ui.components.menu.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.UIManager;

import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UILabel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.implementation.App;

public class OrderedItemsPanel extends UIPanel {
	private HashMap<Object, Object> orderedItems;
	
	public OrderedItemsPanel() {
		super();
		
		this.init();
	}
	
	public void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		UIHeaderPanel orderSummaryHeader = new UIHeaderPanel("Order summary", null, null);
		orderSummaryHeader.setBackground(UIManager.getColor("Button.background"));
		orderSummaryHeader.setSize(new Dimension(400, 50));
		
		this.add(orderSummaryHeader);
	}
	
	public void renderOrderedItems() {
		this.removeAll();
		this.init();
		
		this.orderedItems = App.orderState.getOrderedItems();
		Integer completePrice = 0;

		for (Map.Entry<Object, Object> entry : this.orderedItems.entrySet()) {
		    Object price = entry.getKey();
		    Object item = entry.getValue();

		    this.add(new OrderItemPanel(item, price));

		    //completePrice =+ price;
		}
		
		this.add(new UILabel(completePrice + " Û"));
	}
}
