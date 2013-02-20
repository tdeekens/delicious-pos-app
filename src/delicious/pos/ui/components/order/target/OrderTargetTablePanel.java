package delicious.pos.ui.components.order.target;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import delicious.pos.App;
import delicious.pos.business.logic.view.gen.OrderTypeView;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class OrderTargetTablePanel extends UIPanel {
	private OrderTypeView orderType;
	
	public OrderTargetTablePanel(OrderTypeView orderType) {
		super();
		
		this.orderType = orderType;
		
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(300, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		setLayout(new BorderLayout());
		
		UILabel lblItem = new UILabel(App.labels.get("restaurant-table") + ":");
		JTextField txtField = new JTextField("", 15);
		
		txtField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Tobi: set the table number... where?
			}
		});
		
		this.add(lblItem, BorderLayout.WEST);
		this.add(lblItem, BorderLayout.CENTER);
	}
}
