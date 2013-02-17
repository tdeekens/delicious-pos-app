package delicious.pos.ui.components.menu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import delicious.pos.business.logic.view.ItemView;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.SizeView;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.OrderPanel;
import delicious.pos.ui.screens.MenuScreen;


public class MenuItemPanel extends UIPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemPricesPanel itemPricesPanel = null;
	private MenuScreen menuScreen;
	private OrderPanel orderPanel;
	private ItemView item;
	private PriceView price;
	
	public MenuItemPanel(ItemView item, MenuScreen parentPanel) {
		this.menuScreen = parentPanel;
		this.item = item;
		this.init();
	}
	
	public MenuItemPanel(ItemView item, OrderPanel orderPanel) {
		this.orderPanel = orderPanel;
		this.item = item;
		this.init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(400, 50));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblItemName = new UILabel(this.item.getName());
		lblItemName.setVerticalAlignment(SwingConstants.CENTER);
		add(lblItemName);
		
		//TODO: Shouldnt an item have n prices with a size each?
		ArrayList<PriceView> itemPrices = new ArrayList<PriceView>();
		itemPrices.add(new PriceView(new Float(2.2), new SizeView("XL")));
		itemPrices.add(new PriceView(new Float(2.2), new SizeView("XL")));

		itemPricesPanel = new ItemPricesPanel(itemPrices, this);
		add(itemPricesPanel);	
		
		itemPricesPanel.getRemmoveBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeOrderedItem();
			}
		});
	}
	
	public void setOrdered(boolean isOrdered, PriceView price) {
		this.price = price;
		
		itemPricesPanel.setOrdered(isOrdered, price);
	}
	
	public void removeOrderedItem() {
		this.orderPanel.removeOrderedItem(this, this.item, this.price);		
	}

	public void orderItem(ItemPricesPanel price, ItemPricePanel itemPricePanel) {
		if(menuScreen != null) {
			itemPricesPanel.deselectPrices();
			this.menuScreen.orderItem(this.item, itemPricePanel.getPrice());
		}
	}
}
