package delicious.pos.ui.components.menu.item;

import delicious.pos.creator.datastructure.model.datastructure.impl.ItemImpl;
import delicious.pos.creator.datastructure.model.datastructure.impl.PriceImpl;
import delicious.pos.ui.components.*;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.implementation.App;
import delicious.pos.ui.implementation.MenuScreen;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Insets;
import java.util.ArrayList;

import delicious.pos.ui.*;


public class MenuItemPanel extends UIPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemPricesPanel itemPricesPanel = null;
	private MenuScreen menuScreen;
	private OrderPanel orderPanel;
	private Object itemImpl;
	
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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblItemName = new UILabel("Item Name");
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.insets = new Insets(0, 0, 0, 5);
		gbc_lblItemName.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 0;
		add(lblItemName, gbc_lblItemName);
		
		itemPricesPanel = new ItemPricesPanel(new ArrayList(), this);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(itemPricesPanel, gbc_panel);		
	}
	
	public void setOrdered(boolean isOrdered) {
		itemPricesPanel.setOrdered(isOrdered);
	}

	public void orderItem(Object priceImpl) {
		itemPricesPanel.deselectPrices();
		this.menuScreen.orderItem(this.itemImpl, priceImpl);
	}
}
