package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

import delicious.pos.ui.components.UIFrame;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.UISplitPane;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.item.ItemPricePanel;
import delicious.pos.ui.components.menu.item.ItemPricesPanel;
import delicious.pos.ui.components.menu.item.MenuItemPanel;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class MenuScreen extends UIFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;

	public MenuScreen() {
		this.init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new UIPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.splitPane = new UISplitPane();
		splitPane.setDividerLocation(0.5);
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		this.setupMenu();
		this.setupOrderList();
	}
	
	private void setupMenu() {
		menuPanel = new MenuPanel(this);
		this.splitPane.setLeftComponent(menuPanel);
		
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
		menuPanel.add(new MenuItemPanel(new Object(), this));
	}
	
	private void setupOrderList() {
		orderPanel = new OrderPanel(this);
		splitPane.setRightComponent(orderPanel);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
	}
	
	public void orderItem(Object itemImpl, Object price) {
		MenuItemPanel orderItemPanel = new MenuItemPanel(new Object(), orderPanel);
		orderItemPanel.setOrdered(true, price);
		
		orderPanel.addItem(orderItemPanel);
	}
}
