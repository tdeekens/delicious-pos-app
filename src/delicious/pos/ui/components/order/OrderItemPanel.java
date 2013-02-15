package delicious.pos.ui.components.order;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import delicious.pos.business.logic.view.ItemView;
import delicious.pos.business.logic.view.PriceView;
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
		
		JLabel lblItem = new UILabel(this.item.getName() + " " + this.price.getSize().getValue() + this.price.getValue() + " Û");
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
