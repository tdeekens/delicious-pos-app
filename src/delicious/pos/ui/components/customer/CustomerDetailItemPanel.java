package delicious.pos.ui.components.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import delicious.pos.App;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class CustomerDetailItemPanel extends UIPanel {
	private String rowName;
	private String rowValue;
	
	public CustomerDetailItemPanel(String rowName, String rowValue) {
		super();
		
		this.rowName = rowName;
		this.rowValue = rowValue;
		
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(300, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		setLayout(new BorderLayout());
		
		UILabel lblItem = new UILabel(App.labels.get(this.rowName) + ": " + this.rowValue);
		
		lblItem.setHorizontalAlignment(SwingConstants.LEFT);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
