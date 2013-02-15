package delicious.pos.ui.components.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;

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

	private void setupOrderTypes() {
		JRadioButton rdbtn1 = new JRadioButton("Take away");
		JRadioButton rdbtn2 = new JRadioButton("Eat here");
		JRadioButton rdbtn3 = new JRadioButton("Delivery");
		
		rdbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType("Take away");
			}
		});
		
		rdbtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType("Eat here");
			}
		});
		
		rdbtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType("Delivery");
			}
		});
		
		this.orderTypePanel.add(rdbtn1);
		this.orderTypePanel.add(rdbtn2);
		this.orderTypePanel.add(rdbtn3);

		ButtonGroup bg1 = new ButtonGroup();

		bg1.add(rdbtn1);
		bg1.add(rdbtn2);
		bg1.add(rdbtn3);
	}
	
	private void setOrderType(String orderType) {
		//App.orderState.setOrderType(orderType);
	}
}
