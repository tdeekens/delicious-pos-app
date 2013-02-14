package delicious.pos.ui.implementation;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import delicious.pos.ui.components.UIContentPanel;
import delicious.pos.ui.components.UIFrame;
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
		splitPane.setDividerLocation(0.5);
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		this.add(contentPane, BorderLayout.CENTER);
		this.setupMenu();
		this.setupOrderList();
	}
	
	private void setupMenu() {
		menuPanel = new MenuPanel(this);
		this.splitPane.setLeftComponent(menuPanel.getScrollPane());
		
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
	}
	
	public void orderItem(Object item, Object price) {
		MenuItemPanel orderItemPanel = new MenuItemPanel(item, orderPanel);
		orderItemPanel.setOrdered(true, price);
		
		orderPanel.addItem(orderItemPanel);
	}
}
