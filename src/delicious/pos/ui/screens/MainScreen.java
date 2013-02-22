package delicious.pos.ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import delicious.pos.App;
import delicious.pos.ui.components.extensions.UIButton;
import delicious.pos.ui.components.extensions.UIContentPanel;
import delicious.pos.ui.components.extensions.UIFrame;
import delicious.pos.ui.components.extensions.UIInfoDialog;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.event.SwitchPanel;

public class MainScreen extends UIFrame {
	private UIContentPanel contentPanelMain;
	private UIContentPanel contentPanelMenu;
	private UIContentPanel contentPanelAdmin;
	private UIContentPanel contentPanelOrderValidation;
	private OrderValidationScreen orderValidationScreen;
	private OrderPlacementScreen orderPlacementScreen;
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
		
		setTitle(App.labels.get("app-title"));
		
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
		
		if(App.orderState.getCustomer() == null) {
			System.out.println("No customer selected, can't go on ordering!");
		} else {
			this.orderPlacementScreen.setCustomer(App.orderState.getCustomer());
			this.orderPlacementScreen.renderChildren();
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
		mainPanel.setLayout(new BorderLayout());
				
		UIButton adminBtn = new UIButton(App.labels.get("admin-btn"));
		
		adminBtn.setPreferredSize(new Dimension(500, 700));
		adminBtn.setMinimumSize(this.getPreferredSize());
		adminBtn.setMaximumSize(this.getPreferredSize());
		
		adminBtn.setIcon("suggesstions_64");
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAdmin();
			}
		});
		
		UIButton menuBtn = new UIButton(App.labels.get("menu-btn"));
		
		menuBtn.setPreferredSize(new Dimension(500, 700));
		menuBtn.setMinimumSize(this.getPreferredSize());
		menuBtn.setMaximumSize(this.getPreferredSize());
		
		menuBtn.setIcon("welcome_64");
		menuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});
		
		mainPanel.add(menuBtn, BorderLayout.CENTER);
		mainPanel.add(adminBtn, BorderLayout.WEST);
		
		this.contentPanelMain = new UIContentPanel(
			App.labels.get("restaurant-name"),
			App.labels.get("home-h2"), 
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
			App.labels.get("restaurant-name"), 
			App.labels.get("menu-h2"), 
			"welcome_64", 
			mainPanel, 
			true,
			"1 " + App.labels.get("step-of") + " 3",
			new SwitchPanel() {
				public void next() {
					if(App.orderState.getOrderedItems().size() > 0) {
						showOrderValidation();
					} else {
						new UIInfoDialog(App.labels.get("order-no-items"), App.labels.get("close-dialog"));
					}
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
			App.labels.get("restaurant-name"), 
			App.labels.get("order-validation-h2"), 
			"welcome_64", 
			this.orderValidationScreen, 
			true,
			"2 " + App.labels.get("step-of") + " 3",
			new SwitchPanel() {
				public void next() {
					if(App.orderState.getOrderType() != null) {
						showOrderPlacement();
					} else {
						new UIInfoDialog(App.labels.get("order-no-type"), App.labels.get("close-dialog"));
					}
				}
				
				public void previous() {
					showMenu();
				}
			}
		);
	}
	
	private void setupOrderPlacementPanel() {
		this.orderPlacementScreen = new OrderPlacementScreen();
		
		this.contentPanelOrderPlacement = new UIContentPanel(
			App.labels.get("restaurant-name"), 
			App.labels.get("order-placement-h2"), 
			"welcome_64", 
			this.orderPlacementScreen, 
			true,
			"3 " + App.labels.get("step-of") + " 3",
			new SwitchPanel() {
				public void next() {
					orderPlacementScreen.persistOrder();
				}
				
				public void previous() {
					showOrderValidation();
				}
			}
		);
	}
	
	private void setupAdminPanel() {
		UIPanel mainPanel = new AdminScreen();
		
		this.contentPanelAdmin = new UIContentPanel(
			App.labels.get("restaurant-name"),
			App.labels.get("admin-h2"),
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
