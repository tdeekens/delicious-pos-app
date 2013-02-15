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

import delicious.pos.business.logic.view.ItemView;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.implementation.App;

public class OrderedItemsPanel extends UIPanel {
	private HashMap<PriceView, ItemView> orderedItems;
	
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
		Float completePrice = new Float(0.0);

		for (Map.Entry<PriceView, ItemView> entry : this.orderedItems.entrySet()) {
			PriceView price = entry.getKey();
			ItemView item = entry.getValue();

		    this.add(new OrderItemPanel(item, price));

		    completePrice =+ price.getValue();
		}
		
		UILabel orderSummaryLabel = new UILabel(this.orderedItems.size() + " items for: " + completePrice + " Û");
		orderSummaryLabel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
		orderSummaryLabel.setFont(new Font("Palatino", Font.BOLD, 20));
		
		orderSummaryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(orderSummaryLabel);
	}
}
