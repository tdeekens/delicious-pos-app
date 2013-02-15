package delicious.pos.ui.components.menu;


import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import delicious.pos.business.logic.view.PriceView;
import delicious.pos.ui.components.extensions.UIButton;
import delicious.pos.ui.components.extensions.UIPanel;

public class ItemPricesPanel extends UIPanel {	
	
	private List<ItemPricePanel> itemPricePanels = null;
	private List<PriceView> itemPrices = null;
	private UIButton removeBtn = null;
	private MenuItemPanel parentPanel;
	
	public ItemPricesPanel(List<PriceView> itemPrices, MenuItemPanel parentPanel) {
		this.itemPrices = itemPrices;
		this.itemPricePanels = new ArrayList<ItemPricePanel>();
		this.parentPanel = parentPanel;
		
		this.init();
	}
	
	public void init() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		this.setupPrices();
		
		this.removeBtn = new UIButton();
		this.removeBtn.setIcon("delete_16");
		
		this.removeBtn.setVisible(false);		
		add(removeBtn);
	}
	
	public void addItem(ItemPricePanel itemPrice) {
		this.add(itemPrice);
	}
	
	public void selectSize(ItemPricePanel itemPricePanel) {
		itemPricePanel.select();
	}
	
	@SuppressWarnings("deprecation")
	public void setOrdered(boolean isOrdered, PriceView price) {
		this.removeBtn.setVisible(isOrdered);
		
		if(isOrdered) {
			for(ItemPricePanel itemPricePanel : this.itemPricePanels) {
				itemPricePanel.getPriceBtn().disable();
				itemPricePanel.hidePrices(price);
			}
		}
	}
	
	public UIButton getRemmoveBtn() {
		return this.removeBtn;
	}
	
	public void setupPrices() {
		for(PriceView price : this.itemPrices) {
			ItemPricePanel itemPricePanel = new ItemPricePanel(price, this);
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
		this.parentPanel.orderItem(this, itemPricePanel);
	}
}
