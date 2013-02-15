package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import delicious.pos.ui.components.UIContentPanel;
import delicious.pos.ui.components.UIFrame;
import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.UISplitPane;
import delicious.pos.ui.components.menu.MenuPanel;
import delicious.pos.ui.components.menu.OrderPanel;
import delicious.pos.ui.components.menu.item.MenuItemPanel;

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
