package delicious.pos.ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import delicious.pos.App;
import delicious.pos.business.logic.dao.gen.ItemDAO;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.ItemView;
import delicious.pos.ui.components.customer.CustomerDetailItemPanel;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.extensions.UISplitPane;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.item.MenuItemPanel;
import delicious.pos.ui.components.order.OrderPanel;

public class MenuScreen extends UIPanel {

	private JSplitPane splitPane;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;
	private ItemDAO itemDAO;
	private ArrayList<ItemView> menuItems;

	public MenuScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		this.itemDAO = new ItemDAO();
		this.menuItems = this.itemDAO.findAll();
		
		UIPanel contentPane = new UIPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.splitPane = new UISplitPane();		
		contentPane.add(splitPane, BorderLayout.CENTER);
		this.add(contentPane, BorderLayout.CENTER);
		
		this.setupMenu();
		this.setupOrderList();
		
		splitPane.setResizeWeight(0.2);
	}
	
	private void setupMenu() {
		menuPanel = new MenuPanel(this);
		this.splitPane.setLeftComponent(menuPanel.getScrollPane());
		
		UIHeaderPanel menuHeader = new UIHeaderPanel(App.labels.get("our-menu"), null, null);
		menuHeader.setBackground(UIManager.getColor("Button.background"));
		menuHeader.setSize(new Dimension(400, 50));
		menuPanel.add(menuHeader);
		
		for (ItemView menuItem : this.menuItems) {
			menuPanel.add(new MenuItemPanel(menuItem, this));
		}
	}
	
	private void setupOrderList() {
		orderPanel = new OrderPanel(this);
		splitPane.setRightComponent(orderPanel.getScrollPane());
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		
		UIHeaderPanel orderHeader = new UIHeaderPanel(App.labels.get("ordered-items"), null, null);
		orderHeader.setBackground(UIManager.getColor("Button.background"));
		orderHeader.setSize(new Dimension(400, 50));
		
		orderPanel.add(orderHeader);
	}
	
	public void orderItem(ItemView item, PriceView price) {
		MenuItemPanel orderItemPanel = new MenuItemPanel(item, orderPanel);
		orderItemPanel.setOrdered(true, price);
		
		orderPanel.addItem(orderItemPanel);
		
		App.orderState.addOrderItem(item, price);
	}

	public void removeItem(ItemView item, PriceView price) {
		App.orderState.removeOrderItem(item, price);
	}
}
