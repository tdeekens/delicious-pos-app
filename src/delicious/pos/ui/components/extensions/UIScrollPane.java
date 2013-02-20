package delicious.pos.ui.components.extensions;

import javax.swing.JScrollPane;

import delicious.pos.ui.components.order.target.CustomerFilterList;

public class UIScrollPane extends JScrollPane {
	
	public UIScrollPane() {
		this.init();
	}
	
	public UIScrollPane(UIPanel panel) {
		super(panel);
		this.init();
	}
	
	public UIScrollPane(UITable table) {
		super(table);
		
		this.init();
	}

	public UIScrollPane(CustomerFilterList customerFilterList, int verticalScrollbarAlways, int horizontalScrollbarNever) {
		
		super(customerFilterList, verticalScrollbarAlways, horizontalScrollbarNever);
		
		this.init();
	}

	public void init() {
		
	}
	
}
