package delicious.pos.ui.components.menu.order;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import delicious.pos.ui.components.UILabel;
import delicious.pos.ui.components.UIPanel;
import java.awt.Component;

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
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblItemName = new UILabel("Item Name: ");
		JLabel lblItemPrice = new UILabel("Price 'n size");
		
		add(lblItemName);
		add(lblItemPrice);
	}
}
