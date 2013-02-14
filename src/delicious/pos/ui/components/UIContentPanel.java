package delicious.pos.ui.components;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;

import delicious.pos.ui.event.SwitchPanel;

public class UIContentPanel extends UIPanel {
	private UIHeaderPanel headerPanel;
	private UIPanel contentPanel;
	private SwitchPanel switchPanel;
	private UIFooterPanel footerPanel;
	private boolean enableFooter;
	private String h1;
	private String h2;
	private String icon;
	private ImageIcon iconRessource;
	
	public UIContentPanel(String h1, String h2, String icon, UIPanel contentPanel, boolean enableFooter, SwitchPanel switchPanel) {
		super();
		
		this.contentPanel = contentPanel;
		this.enableFooter = enableFooter;
		this.h1 = h1;
		this.switchPanel = switchPanel;
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
		
		if(this.enableFooter) {
			this.footerPanel = new UIFooterPanel(
				new ImageIcon(this.getClass().getResource("icons/left_32.png")),
				new ImageIcon(this.getClass().getResource("icons/right_32.png")),
				"1 of 3",
				this.switchPanel
			);
			
			add(footerPanel, BorderLayout.SOUTH);
		}
		
		
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	public UIPanel getBottomPanel() {
		return this.contentPanel;
	}
}
