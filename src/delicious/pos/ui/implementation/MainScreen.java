package delicious.pos.ui.implementation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UIContentPanel;
import delicious.pos.ui.components.UIFrame;
import delicious.pos.ui.components.UIPanel;
import delicious.pos.ui.event.SwitchPanel;
import delicious.pos.ui.implementation.admin.DBEditor;

public class MainScreen extends UIFrame {
	private UIContentPanel contentPanelMain;
	private UIContentPanel contentPanelMenu;
	private UIContentPanel contentPanelAdmin;
	private UIContentPanel contentPanelOrderValidation;
	private OrderValidationScreen orderValidationScreen;
	private UIContentPanel contentPanelOrderPlacement;

	public MainScreen() {
		init();
	}
	
	private void init() {
		setSize(new Dimension(1024, 768));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(dim.width/2) - (this.getSize().width/2), 
				(dim.height/2) - (this.getSize().height/2)
		);
		
		setTitle("Greek Paradise - a place greeks love!");
		
		this.setupMainPanel();
		this.setupMenuPanel();
		this.setupAdminPanel();
		this.setupOrderPlacementPanel();
		this.setupOrderValidationPanel();
		
		getContentPane().add(contentPanelMain);
	}
	
	private void showMenu() {
		if(this.contentPanelMain != null) {
			this.getContentPane().remove(this.contentPanelMain);
		}
		
		if(this.contentPanelOrderValidation != null) {
			this.getContentPane().remove(this.contentPanelOrderValidation);
		}
		
		this.getContentPane().add(this.contentPanelMenu);
		
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void showAdmin() {
		this.getContentPane().remove(this.contentPanelMain);
		
		this.getContentPane().add(this.contentPanelAdmin);
		
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void showOrderValidation() {
		if(this.contentPanelMenu != null) {
			this.getContentPane().remove(this.contentPanelMenu);
		}
		
		if(this.contentPanelOrderPlacement != null) {
			this.getContentPane().remove(this.contentPanelOrderPlacement);
		}
		
		this.orderValidationScreen.renderChildren();
		
		this.getContentPane().add(this.contentPanelOrderValidation);
		
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void showOrderPlacement() {
		if(this.contentPanelOrderValidation != null) {
			this.getContentPane().remove(this.contentPanelOrderValidation);
		}
		
		this.getContentPane().add(this.contentPanelOrderPlacement);
		
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void showMain() {
		if(this.contentPanelMenu != null) {
			this.getContentPane().remove(this.contentPanelMenu);
		}
		
		if(this.contentPanelAdmin != null) {
			this.getContentPane().remove(this.contentPanelAdmin);
		}
		
		this.getContentPane().add(this.contentPanelMain);
		
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	private void setupMainPanel() {
		UIPanel mainPanel = new UIPanel();
				
		UIButton adminBtn = new UIButton("Admin");
		adminBtn.setBounds(177, 58, 85, 54);
		adminBtn.setIcon("suggesstions_64");
		adminBtn.setPreferredSize(new Dimension(200, 100));
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAdmin();
			}
		});
		
		UIButton menuBtn = new UIButton("Menu");
		menuBtn.setPreferredSize(new Dimension(200, 100));
		menuBtn.setBounds(177, 143, 85, 54);
		menuBtn.setIcon("welcome_64");
		menuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});
		
		mainPanel.add(menuBtn);
		mainPanel.add(adminBtn);
		
		this.contentPanelMain = new UIContentPanel(
			"Greek Paradise",
			"...you're at home!", 
			"geek-paradise-logo_64", 
			mainPanel, 
			false,
			null,
			null
		);
	}
	
	private void setupMenuPanel() {
		UIPanel mainPanel = new MenuScreen();
		
		this.contentPanelMenu = new UIContentPanel(
			"Greek Paradise", 
			"The menu, place order...", 
			"welcome_64", 
			mainPanel, 
			true,
			"1 of 3",
			new SwitchPanel() {
				public void next() {
					showOrderValidation();
				}
				
				public void previous() {
					showMain();
				}
			}
		);
	}
	
	private void setupOrderValidationPanel() {
		this.orderValidationScreen = new OrderValidationScreen();
		
		this.contentPanelOrderValidation = new UIContentPanel(
			"Greek Paradise", 
			"Please verify order...", 
			"welcome_64", 
			this.orderValidationScreen, 
			true,
			"2 of 3",
			new SwitchPanel() {
				public void next() {
					showOrderPlacement();
				}
				
				public void previous() {
					showMenu();
				}
			}
		);
	}
	
	private void setupOrderPlacementPanel() {
		UIPanel mainPanel = new OrderPlacementScreen();
		
		this.contentPanelOrderPlacement = new UIContentPanel(
			"Greek Paradise", 
			"Place order...", 
			"welcome_64", 
			mainPanel, 
			true,
			"3 of 3",
			new SwitchPanel() {
				public void next() {
					
				}
				
				public void previous() {
					showOrderValidation();
				}
			}
		);
	}
	
	private void setupAdminPanel() {
		UIPanel mainPanel = new DBEditor();
		
		this.contentPanelAdmin = new UIContentPanel(
			"Greek Paradise", 
			"Administer the shit out of YOUR data!", 
			"suggesstions_64", 
			mainPanel, 
			true,
			null,
			new SwitchPanel() {
				public void next() {
					
				}
				
				public void previous() {
					showMain();
				}
			}
		);
	}
}
