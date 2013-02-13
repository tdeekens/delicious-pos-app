package delicious.pos.ui.components.menu.item;


import delicious.pos.creator.datastructure.model.datastructure.impl.ItemImpl;
import delicious.pos.creator.datastructure.model.datastructure.impl.PriceImpl;
import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.implementation.App;

import java.awt.FlowLayout;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.UIManager;

import com.adamtaft.eb.EventBusService;
import com.adamtaft.eb.EventHandler;

public class ItemPricesPanel extends UIPanel {	
	
	private List<ItemPricePanel> itemPricePanels = null;
	private List<Object> itemPrices = null;
	private JButton removeBtn = null;
	private MenuItemPanel parentPanel;
	
	public ItemPricesPanel(List<Object> itemPrices, MenuItemPanel parentPanel) {
		this.itemPrices = itemPrices;
		this.itemPricePanels = new ArrayList<ItemPricePanel>();
		this.parentPanel = parentPanel;
		
		this.init();
	}
	
	public void init() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		this.setupMenuItems();
		
		this.removeBtn = new UIButton("-");
		
		this.removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.removeBtn.setVisible(false);		
		add(removeBtn);
	}
	
	public void addItem(ItemPricePanel itemPrice) {
		this.add(itemPrice);
	}
	
	public void selectSize(ItemPricePanel itemPricePanel) {
		itemPricePanel.select();
	}
	
	public void setOrdered(boolean isOrdered) {
		this.removeBtn.setVisible(isOrdered);
	}
	
	public void setupMenuItems() {
		//Just to something
		this.itemPrices.add(new Object());
		this.itemPrices.add(new Object());
		
		for(Object price : this.itemPrices) {
			ItemPricePanel itemPricePanel = new ItemPricePanel(null, this);
			this.itemPricePanels.add(itemPricePanel);
			this.add(itemPricePanel);
		}
	}
	
	public void deselectPrices() {
		for(ItemPricePanel itemPricePanel : this.itemPricePanels) {
			itemPricePanel.deselect();
		}
	}

	public void orderItem(ItemPricePanel itemPricePanel) {
		this.parentPanel.orderItem(itemPricePanel);
	}
}
