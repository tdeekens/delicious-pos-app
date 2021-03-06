package delicious.pos.ui.components.customer;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.ui.components.extensions.UIHeaderPanel;
import delicious.pos.ui.components.extensions.UIPanel;

public class CustomerDetailPanel extends UIPanel {
	
	private CustomerView customer;
	
	public CustomerDetailPanel() {
		super();
				
		this.init();
	}
	
	public void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		UIHeaderPanel customerDetailHeader = new UIHeaderPanel(App.labels.get("order-details"), null, null);
		customerDetailHeader.setBackground(UIManager.getColor("Button.background"));
		customerDetailHeader.setSize(new Dimension(400, 50));
		
		this.add(customerDetailHeader);		
	}
	
	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}
	
	public void renderCustomerDetails() {
		this.removeAll();
		this.init();
		
		if(this.customer == null) return;
		
		HashMap<String, String> customerMap = new HashMap<String, String>(this.customer.toMap());
		
		for (Map.Entry<String, String> entry : customerMap.entrySet()) {
			String rowName = entry.getKey();
			String rowValue = entry.getValue();
			
			if(rowName.toLowerCase().contains("id")) continue;

		    this.add(new CustomerDetailItemPanel(rowName, rowValue));
		}
	}
	
	public void renderTargetDetails() {
		HashMap<String, String> targetInputs = new HashMap<String, String>(App.orderState.getTargetInputs());

		for (Map.Entry<String, String> entry : targetInputs.entrySet()) {
			String rowName = entry.getKey();
			String rowValue = entry.getValue();
			
			if(rowName.toLowerCase().contains("id")) continue;
			
		    this.add(new CustomerDetailItemPanel(rowName, rowValue));
		}
	}
}
