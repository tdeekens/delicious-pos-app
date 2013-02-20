package delicious.pos.ui.components.order.type;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.business.logic.view.gen.OrderTypeView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;

public class OrderTypeInfoPanel extends UIPanel {
	
	private OrderTypeView orderType;
	
	public OrderTypeInfoPanel(OrderTypeView orderType) {
		super();
				
		this.orderType = orderType;
		
		this.init();
	}
	
	public void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.renderOrderTypeDetails();
	}
	
	public void renderOrderTypeDetails() {		
		HashMap<String, String> orderTypeMap = new HashMap<String, String>(this.orderType.toMap());
		
		for (Map.Entry<String, String> entry : orderTypeMap.entrySet()) {
			String rowName = entry.getKey();
			String rowValue = entry.getValue();

		    this.add(new OrderTypeInfoItemPanel(rowName, rowValue));
		}
	}
}
