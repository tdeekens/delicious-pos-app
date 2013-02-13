package delicious.pos.ui.components;

import javax.swing.JPanel;

public class UIPanel extends JPanel implements Cloneable {

	public UIPanel() {
		
		init();
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
