package delicious.pos.ui.components.menu.item;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import delicious.pos.ui.components.UILabel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.implementation.MenuScreen;


public class MenuItemPanel extends UIPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemPricesPanel itemPricesPanel = null;
	private MenuScreen menuScreen;
	private OrderPanel orderPanel;
	private Object itemImpl;
	
	/**
	 * @wbp.parser.constructor
	 */
	public MenuItemPanel(Object itemImpl, MenuScreen parentPanel) {
		this.menuScreen = parentPanel;
		this.itemImpl = itemImpl;
		this.init();
	}
	
	public MenuItemPanel(Object itemImpl, OrderPanel orderPanel) {
		this.orderPanel = orderPanel;
		this.itemImpl = itemImpl;
		this.init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(400, 50));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblItemName = new UILabel("Item Name");
		lblItemName.setVerticalAlignment(SwingConstants.CENTER);
		add(lblItemName);
		
		ArrayList<Object> itemPrices = new ArrayList<Object>();
		itemPrices.add(new Object());
		itemPrices.add(new Object());
		
		itemPricesPanel = new ItemPricesPanel(itemPrices, this);
		add(itemPricesPanel);	
		
		itemPricesPanel.getRemmoveBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeOrderedItem();
			}
		});
	}
	
	public void setOrdered(boolean isOrdered, Object price) {
		itemPricesPanel.setOrdered(isOrdered, price);
	}
	
	public void removeOrderedItem() {
		this.orderPanel.removeOrderedItem(this);		
	}

	public void orderItem(ItemPricesPanel price, ItemPricePanel itemPricePanel) {
		if(menuScreen != null) {
			itemPricesPanel.deselectPrices();
			this.menuScreen.orderItem(this.itemImpl, itemPricePanel.getPrice());
		}
	}
}
