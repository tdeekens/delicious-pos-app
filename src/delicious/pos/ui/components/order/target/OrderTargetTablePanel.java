package delicious.pos.ui.components.order.target;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		final JTextField txtField = new JTextField("", 15);
		
		txtField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                
                //TODO: Tobi: where to set the table number, which viewobj
            }
		});
		
		this.add(lblItem, BorderLayout.WEST);
		this.add(txtField, BorderLayout.CENTER);
	}
}
