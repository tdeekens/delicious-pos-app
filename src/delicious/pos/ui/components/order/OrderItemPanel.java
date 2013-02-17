package delicious.pos.ui.components.order;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import delicious.pos.App;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.ItemView;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class OrderItemPanel extends UIPanel {
	private PriceView price;
	private ItemView item;
	
	public OrderItemPanel(ItemView item, PriceView price) {
		super();
		
		this.price = price;
		this.item = item;
		
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(300, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		setLayout(new BorderLayout());
		
		UILabel lblItem = new UILabel(this.item.getName() + " " + this.price.getValue() + this.price.getValue() + " " + App.labels.get("currency"));
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
