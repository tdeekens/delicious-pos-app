package delicious.pos.ui.components.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.ItemView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class OrderedItemsPanel extends UIPanel {
	private HashMap<PriceView, ItemView> orderedItems;
	
	public OrderedItemsPanel() {
		super();
		
		this.init();
	}
	
	public void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		UIHeaderPanel orderSummaryHeader = new UIHeaderPanel(App.labels.get("order-summary"), null, null);
		orderSummaryHeader.setBackground(UIManager.getColor("Button.background"));
		orderSummaryHeader.setSize(new Dimension(400, 50));
		
		this.add(orderSummaryHeader);
	}
	
	public void renderOrderedItems() {
		this.removeAll();
		this.init();
		
		this.orderedItems = App.orderState.getOrderedItems();

		for (Map.Entry<PriceView, ItemView> entry : this.orderedItems.entrySet()) {
			PriceView price = entry.getKey();
			ItemView item = entry.getValue();

		    this.add(new OrderItemPanel(item, price));
		}
		
		UIHeaderPanel orderSummaryLabel = new UIHeaderPanel(
				this.orderedItems.size() + " " + App.labels.get("items-for") 
				+ " " + 
				App.orderState.getCompletePrice() 
				+ " (incl.: " 
				+ App.orderState.getOrderTypePrice()
				+ ") "
				+ App.labels.get("currency"), 
				null, null
		);
		
		orderSummaryLabel.setBackground(UIManager.getColor("Button.background"));
		orderSummaryLabel.setSize(new Dimension(400, 50));
		
		this.add(orderSummaryLabel);
	}
}
