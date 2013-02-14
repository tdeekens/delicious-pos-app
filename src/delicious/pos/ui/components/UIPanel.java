package delicious.pos.ui.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class UIPanel extends JPanel implements Cloneable {

	public UIPanel() {
		init();
	}
	
	public UIPanel(LayoutManager layout) {
		super(layout);
	}
	
	private void init() {
		
	}
	
	public Object clone() {	
		try {
	      return super.clone();
	    }
	    catch (CloneNotSupportedException e) {
	      throw new InternalError();
	    }
	}
}
