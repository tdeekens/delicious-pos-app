package delicious.pos.ui.implementation;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

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
		
	}
}
