package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.UISplitPane;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.item.MenuItemPanel;

public class MenuScreen extends UIPanel {

	private JSplitPane splitPane;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;

	public MenuScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
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
		
		UIHeaderPanel menuHeader = new UIHeaderPanel("Our menu...", null, null);
		menuHeader.setBackground(UIManager.getColor("Button.background"));
		menuHeader.setSize(new Dimension(400, 50));
		menuPanel.add(menuHeader);
		
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
	}
	
	private void setupOrderList() {
		orderPanel = new OrderPanel(this);
		splitPane.setRightComponent(orderPanel.getScrollPane());
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		
		UIHeaderPanel orderHeader = new UIHeaderPanel("Ordered items...", null, null);
		orderHeader.setBackground(UIManager.getColor("Button.background"));
		orderHeader.setSize(new Dimension(400, 50));
		
		orderPanel.add(orderHeader);
	}
	
	public void orderItem(Object item, Object price) {
		MenuItemPanel orderItemPanel = new MenuItemPanel(item, orderPanel);
		orderItemPanel.setOrdered(true, price);
		
		orderPanel.addItem(orderItemPanel);
		
		App.orderState.addOrderItem(item, price);
	}

	public void removeItem(Object itemImpl, Object priceImpl) {
		App.orderState.removeOrderItem(itemImpl, priceImpl);
	}
}
