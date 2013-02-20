package delicious.pos.ui.components.order;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.screens.OrderValidationScreen;
import delicious.pos.util.ImageLoader;

public class OrderTypePanel extends UIPanel {
	
	private UIPanel orderTypePanel;
	private OrderTypeDAO orderTypeDAO;
	private ArrayList<OrderTypeView> orderTypes;
	private OrderValidationScreen parentScreen;
	
	public OrderTypePanel(OrderValidationScreen parentScreen) {
		super();
		
		this.parentScreen = parentScreen;
		
		this.orderTypeDAO = new OrderTypeDAO(App.DBConnection, App.JDBCUtilities.dbName, App.JDBCUtilities.dbms);
		
		this.orderTypes = this.orderTypeDAO.findAll();
		
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(400, 200));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		this.orderTypePanel = new UIPanel();
		this.orderTypePanel.setLayout(new BoxLayout(this.orderTypePanel, BoxLayout.Y_AXIS));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
						
		UIHeaderPanel orderTypeHeader = new UIHeaderPanel(App.labels.get("specify-order"), null, null);
		orderTypeHeader.setBackground(UIManager.getColor("Button.background"));
		orderTypeHeader.setSize(new Dimension(400, 50));
				
		this.setupOrderTypes();
		
		this.add(orderTypeHeader);
		this.add(this.orderTypePanel);
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
			
			orderTypeBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setOrderType(orderType);
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
	
	private Float getPriceForOrderType(Integer priceId) {
		Float orderTypePrice = (float) 0.0;
		
		if(priceId == 0) return orderTypePrice;
		
		PriceDAO priceDAO = new PriceDAO(App.DBConnection, App.JDBCUtilities.dbName, App.JDBCUtilities.dbms);

		PriceView price = priceDAO.findById(priceId);

		orderTypePrice = price.getValue();
		
		return orderTypePrice;
	}
}
