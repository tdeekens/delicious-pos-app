package delicious.pos.ui.components;

import javax.swing.JScrollPane;

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
	}

	public void init() {
		
	}
	
}
