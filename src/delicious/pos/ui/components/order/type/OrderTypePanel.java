package delicious.pos.ui.components.order.type;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.dao.PriceDAO;
import delicious.pos.business.logic.dao.gen.OrderTypeDAO;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.OrderTypeView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.order.target.*;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.order.type.OrderTypeInfoPanel;
import delicious.pos.ui.screens.OrderValidationScreen;

public class OrderTypePanel extends UIPanel {
	
	private UIPanel orderTypePanel;
	private UIPanel orderTypeInfoPanel;
	private UIPanel orderTargetPanel;
	private OrderTypeDAO orderTypeDAO;
	private ArrayList<OrderTypeView> orderTypes;
	private OrderValidationScreen parentScreen;
	
	public OrderTypePanel(OrderValidationScreen parentScreen) {
		super();
		
		this.parentScreen = parentScreen;
		
		this.orderTypeDAO = new OrderTypeDAO();
		
		this.orderTypes = this.orderTypeDAO.findAll();
		
		init();
	}

	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.orderTypePanel = new UIPanel();
		this.orderTypeInfoPanel = new UIPanel();
		this.orderTargetPanel = new UIPanel();
		
		this.orderTypePanel.setLayout(new BoxLayout(this.orderTypePanel, BoxLayout.Y_AXIS));
		
		UIHeaderPanel orderTypeHeader = new UIHeaderPanel(App.labels.get("specify-order"), null, null);
		orderTypeHeader.setBackground(UIManager.getColor("Button.background"));
		orderTypeHeader.setSize(new Dimension(400, 50));

		this.add(orderTypeHeader);
		
		this.setupOrderTypes();
		this.add(this.orderTypePanel);
		
		this.add(this.orderTypeInfoPanel);
		this.add(this.orderTargetPanel);

		
		this.orderTypeInfoPanel.setPreferredSize(new Dimension(400, 150));
		this.orderTypeInfoPanel.setMinimumSize(this.getPreferredSize());
		this.orderTypeInfoPanel.setMaximumSize(this.getPreferredSize());
	}

	private void setupOrderTypes() {
		ButtonGroup btnGroup = new ButtonGroup();
		
		for (final OrderTypeView orderType : this.orderTypes) {
			JRadioButton orderTypeBtn = new JRadioButton(
					orderType.getName()
					+ " ("
					+ this.getPriceForOrderType(orderType.getPriceId())
					+ " " + App.labels.get("currency")
					+ ") "
			);
			
			orderTypeBtn.setHorizontalAlignment(SwingConstants.LEFT);
			
			orderTypeBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setOrderType(orderType);
					showOrderTypeInfo(orderType);
					showOrderTarget(orderType);
				}
			});
			
			btnGroup.add(orderTypeBtn);
			this.orderTypePanel.add(orderTypeBtn);
		}
	}
	
	private void setOrderType(OrderTypeView orderType) {
		App.orderState.setOrderType(orderType);
		this.parentScreen.onOrderTypeSet();
	}
	
	private void showOrderTypeInfo(OrderTypeView orderType) {
		this.orderTypeInfoPanel.removeAll();
		
		this.orderTypeInfoPanel.add(new OrderTypeInfoPanel(orderType));
		
		this.orderTypeInfoPanel.validate();
		this.orderTypeInfoPanel.repaint();
	}
	
	private void showOrderTarget(OrderTypeView orderType) {
		this.orderTargetPanel.removeAll();
		
		if(orderType.getTarget() == null) {}
		
		else if(orderType.getTarget().equals("Customer")) {
			CustomerFilterList customerFilterList = new CustomerFilterList();
			
			this.orderTargetPanel.add(customerFilterList.getPane());
		}
		else if(orderType.getTarget().equals("Table")) {
			OrderTargetTablePanel 
				orderTargetTablePanel 
					= new OrderTargetTablePanel(new 
						delicious.pos.business.logic.view.gen.TableView());
			
			this.orderTargetPanel.add(orderTargetTablePanel);
		}
		
		this.orderTargetPanel.validate();
		this.orderTargetPanel.repaint();
	}
	
	public void redraw() {
		this.validate();
		this.repaint();
	}
	
	private Float getPriceForOrderType(Integer priceId) {
		Float orderTypePrice = (float) 0.0;
		
		if(priceId == 0) return orderTypePrice;
		
		PriceDAO priceDAO = new PriceDAO();

		PriceView price = priceDAO.findById(priceId);

		orderTypePrice = price.getValue();
		
		return orderTypePrice;
	}
}
