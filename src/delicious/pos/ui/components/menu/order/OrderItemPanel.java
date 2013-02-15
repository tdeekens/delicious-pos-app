package delicious.pos.ui.components.menu.order;

import java.awt.BorderLayout;
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
		
		setLayout(new BorderLayout());
		
		JLabel lblItem = new UILabel("Item Name: Price 'n size");
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
