package delicious.pos.ui.components.order.item;

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
		
		String lblString = 
				this.item.getName() 
				+ " in " 
				+ this.price.getSizeValue() 
				+ " for " 
				+ this.price.getValue() 
				+ " " + App.labels.get("currency");
			
		UILabel lblItem = new UILabel(lblString);
		
		lblItem.setHorizontalAlignment(SwingConstants.LEFT);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
