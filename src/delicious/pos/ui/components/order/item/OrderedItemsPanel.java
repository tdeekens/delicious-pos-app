package delicious.pos.ui.components.order.item;

import java.awt.Color;
import java.awt.Component;
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
	private UIHeaderPanel orderSummaryHeader;
	
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
		
		this.renderOrderSummary();
	}
	
	public void renderOrderSummary() {
		if(this.orderSummaryHeader instanceof Component) this.remove(this.orderSummaryHeader);
		
		String orderSummaryLbl = 
				this.orderedItems.size() + " " + App.labels.get("items-for") 
				+ " " + 
				App.orderState.getCompletePrice() 
				+ " " + App.labels.get("currency")
				+ " (incl.: " 
				+ App.orderState.getOrderTypePrice()
				+ " "
				+ App.labels.get("currency")
				+ ")";
		
		this.orderSummaryHeader = new UIHeaderPanel(
				orderSummaryLbl, 
				null, null
		);
						
		this.orderSummaryHeader.setBackground(UIManager.getColor("Button.background"));
		this.orderSummaryHeader.setSize(new Dimension(400, 50));
		
		this.add(this.orderSummaryHeader);
		
		this.redraw();
	}
	
	private void redraw() {
		this.validate();
		this.repaint();
	}
}
