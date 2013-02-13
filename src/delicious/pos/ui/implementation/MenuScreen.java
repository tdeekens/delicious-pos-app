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
import delicious.pos.ui.components.menu.item.MenuItemPanel;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class MenuScreen extends UIFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel orderPanel;
	private JPanel menuPanel;

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
		menuPanel = new JPanel();
		this.splitPane.setLeftComponent(menuPanel);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		menuPanel.add(new MenuItemPanel());
		menuPanel.add(new MenuItemPanel());
		menuPanel.add(new MenuItemPanel());
		menuPanel.add(new MenuItemPanel());
		menuPanel.add(new MenuItemPanel());
		menuPanel.add(new MenuItemPanel());
	}
	
	private void setupOrderList() {
		orderPanel = new JPanel();
		splitPane.setRightComponent(orderPanel);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
	}
}
