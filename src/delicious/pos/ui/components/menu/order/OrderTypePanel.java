package delicious.pos.ui.components.menu.order;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import delicious.pos.ui.components.UIHeaderPanel;
import delicious.pos.ui.components.UIPanel;

public class OrderTypePanel extends UIPanel {
	private UIPanel orderTypePanel;
	private UIPanel customerPanel;
	private String selectedOrderType;
	
	public OrderTypePanel() {
		super();
		
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());

		this.orderTypePanel = new UIPanel();
		
		this.orderTypePanel.setPreferredSize(new Dimension(300, 20));
		this.orderTypePanel.setMinimumSize(this.getPreferredSize());
		this.orderTypePanel.setMaximumSize(this.getPreferredSize());
		
		this.orderTypePanel.setLayout(new BoxLayout(this.orderTypePanel, BoxLayout.PAGE_AXIS));
				
		this.customerPanel = new UIPanel();
		customerPanel.setLayout(new BorderLayout());
		
		UIHeaderPanel orderTypeHeader = new UIHeaderPanel("Select order type and customer:", null, null);
		orderTypeHeader.setBackground(UIManager.getColor("Button.background"));
		orderTypeHeader.setSize(new Dimension(400, 50));
		
		this.setupOrderTypes();
		
		add(orderTypeHeader, BorderLayout.NORTH);
		add(this.orderTypePanel, BorderLayout.CENTER);
		add(this.customerPanel, BorderLayout.SOUTH);
	}

	public void setupOrderTypes() {
		JRadioButton rdbtn1 = new JRadioButton("Take away");
		this.orderTypePanel.add(rdbtn1);

		JRadioButton rdbtn2 = new JRadioButton("Eat here");
		this.orderTypePanel.add(rdbtn2);

		JRadioButton rdbtn3 = new JRadioButton("Delivery");
		this.orderTypePanel.add(rdbtn3);

		ButtonGroup bg1 = new ButtonGroup();

		bg1.add(rdbtn1);
		bg1.add(rdbtn2);
		bg1.add(rdbtn3);

		if (rdbtn1.isSelected())
			this.selectedOrderType = rdbtn1.getText();
		if (rdbtn2.isSelected())
			this.selectedOrderType = rdbtn2.getText();
		if (rdbtn3.isSelected())
			this.selectedOrderType = rdbtn3.getText();

	}
}
