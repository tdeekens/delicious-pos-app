package delicious.pos.ui.components.order;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.util.ImageLoader;

public class OrderTypePanel extends UIPanel {
	
	private UIPanel orderTypePanel;
	
	public OrderTypePanel() {
		super();
		
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(400, 200));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		this.orderTypePanel = new UIPanel();
		this.orderTypePanel.setLayout(new BoxLayout(this.orderTypePanel, BoxLayout.X_AXIS));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
						
		UIHeaderPanel orderTypeHeader = new UIHeaderPanel(App.labels.get("specify-order"), null, null);
		orderTypeHeader.setBackground(UIManager.getColor("Button.background"));
		orderTypeHeader.setSize(new Dimension(400, 50));
				
		this.setupOrderTypes();
		
		this.add(orderTypeHeader);
		this.add(this.orderTypePanel);
	}

	private void setupOrderTypes() {
		JRadioButton takeAwayBtn = new JRadioButton(App.labels.get("takeaway"), ImageLoader.loadImageIcon(this, "takeaway"));
		takeAwayBtn.setHorizontalAlignment(SwingConstants.LEFT);
		JRadioButton eatHereBtn = new JRadioButton(App.labels.get("takeaway"), ImageLoader.loadImageIcon(this, "eatHere"));
		JRadioButton deliveryBtn = new JRadioButton(App.labels.get("takeaway"), ImageLoader.loadImageIcon(this, "delivery"));
		
		takeAwayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType(App.labels.get("takeaway"));
			}
		});
		
		eatHereBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType(App.labels.get("eathere"));
			}
		});
		
		deliveryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setOrderType(App.labels.get("delivery"));
			}
		});
		
		this.orderTypePanel.add(takeAwayBtn);
		this.orderTypePanel.add(eatHereBtn);
		this.orderTypePanel.add(deliveryBtn);

		ButtonGroup bg = new ButtonGroup();

		bg.add(takeAwayBtn);
		bg.add(eatHereBtn);
		bg.add(deliveryBtn);
	}
	
	private void setOrderType(String orderType) {
		//TODO: Set ordertype in state object to gather later
		//App.orderState.setOrderType(orderType);
	}
}
