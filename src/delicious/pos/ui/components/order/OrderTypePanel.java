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
import delicious.pos.business.logic.dao.gen.OrderTypeDAO;
import delicious.pos.business.logic.view.gen.OrderTypeView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.util.ImageLoader;

public class OrderTypePanel extends UIPanel {
	
	private UIPanel orderTypePanel;
	private OrderTypeDAO orderTypeDAO;
	private ArrayList<OrderTypeView> orderTypes;
	
	public OrderTypePanel() {
		super();
		
		this.orderTypeDAO = new OrderTypeDAO(App.DBConnection, App.JDBCUtilities.dbName, App.JDBCUtilities.dbms);
		
		try {
			this.orderTypes = this.orderTypeDAO.findAll();
		} catch (SQLException e) {
			App.put("Could not load Menu, please check internet connection!");
			e.printStackTrace();
		}
		
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
		ButtonGroup btnGroup = new ButtonGroup();
		
		for (final OrderTypeView orderType : this.orderTypes) {			
			JRadioButton orderTypeBtn = new JRadioButton(App.labels.get(orderType.getName()), ImageLoader.loadImageIcon(this, orderType.getName()));
			
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
	}
}
