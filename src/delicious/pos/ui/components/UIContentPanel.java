package delicious.pos.ui.components;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;

public class UIContentPanel extends UIPanel {
	private UIHeaderPanel headerPanel;
	private UIPanel bottomPanel;
	private String h1;
	private String h2;
	private String icon;
	private ImageIcon iconRessource;
	
	public UIContentPanel(String h1, String h2, String icon, UIPanel bottomPanel) {
		super();
		
		this.bottomPanel = bottomPanel;
		this.h1 = h1;
		this.h2 = h2;
		this.icon = icon;
		this.iconRessource = new ImageIcon(this.getClass().getResource("icons/" + icon + ".png"));
		
		this.init();
	}
	
	public void init() {
		setLayout(new BorderLayout(0, 0));
		
		UIHeaderPanel headerPanel = new UIHeaderPanel(
			this.h1,
			this.h2,
			this.iconRessource
		);
		
		add(headerPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
	}
	
	public UIPanel getBottomPanel() {
		return this.bottomPanel;
	}
}
