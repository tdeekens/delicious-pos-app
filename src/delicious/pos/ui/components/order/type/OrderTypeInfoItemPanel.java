package delicious.pos.ui.components.order.type;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import delicious.pos.App;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class OrderTypeInfoItemPanel extends UIPanel {
	private String rowName;
	private String rowValue;
	
	public OrderTypeInfoItemPanel(String rowName, String rowValue) {
		super();
		
		this.rowName = rowName;
		this.rowValue = rowValue;
		
		this.init();
	}
	
	public void init() {
		setLayout(new BorderLayout());
		
		this.setPreferredSize(new Dimension(400, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
				
		UILabel lblItem = new UILabel(App.labels.get(this.rowName) + ": " + this.rowValue);
		lblItem.setHorizontalAlignment(SwingConstants.LEFT);
		
		add(lblItem, BorderLayout.CENTER);
	}
}
