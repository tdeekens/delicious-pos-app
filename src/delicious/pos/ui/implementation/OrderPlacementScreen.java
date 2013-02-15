package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.UIManager;

import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;

public class OrderPlacementScreen extends UIPanel {

	private JSplitPane splitPane;
	private OrderPanel orderPanel;
	private MenuPanel menuPanel;

	public OrderPlacementScreen() {
		this.init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		
		UIHeaderPanel orderPlacementHeader = new UIHeaderPanel("Please verify si bloody order!", null, null);
		orderPlacementHeader.setBackground(UIManager.getColor("Button.background"));
		orderPlacementHeader.setSize(new Dimension(1024, 50));
		
		this.add(orderPlacementHeader, BorderLayout.NORTH);
	}
}
