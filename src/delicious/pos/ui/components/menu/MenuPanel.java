package delicious.pos.ui.components.menu;

import javax.swing.BoxLayout;

import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.components.UIScrollPane;
import delicious.pos.ui.implementation.MenuScreen;

public class MenuPanel extends UIPanel {
	private MenuScreen parentPanel;
	private UIScrollPane scrollPane;
	
	public MenuPanel(MenuScreen menuScreen) {
		this.parentPanel = menuScreen;
		this.init();
	}
	
	public void init() {
		scrollPane = new UIScrollPane(this);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public UIScrollPane getScrollPane() {
		return this.scrollPane;
	}
	
	private void redraw() {
		this.scrollPane.validate();
		this.scrollPane.repaint();
		this.validate();
		this.repaint();
	}
}
