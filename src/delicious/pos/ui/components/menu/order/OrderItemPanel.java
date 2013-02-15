package delicious.pos.ui.components.menu.order;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import delicious.pos.ui.components.UILabel;
import delicious.pos.ui.components.UIPanel;

public class OrderItemPanel extends UIPanel {
	private Object price;
	private Object item;
	
	public OrderItemPanel(Object item, Object price) {
		super();
		
		this.price = price;
		this.item = item;
		
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(300, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblItemName = new UILabel((String) this.item);
		lblItemName.setVerticalAlignment(SwingConstants.CENTER);
		
		JLabel lblItemPrice = new UILabel((String) this.price + " Û");
		lblItemPrice.setVerticalAlignment(SwingConstants.CENTER);
		
		add(lblItemName);
		add(lblItemPrice);
	}
}
