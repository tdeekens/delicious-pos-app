package delicious.pos.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UIContentPanel;
import delicious.pos.ui.components.UIFrame;
import delicious.pos.ui.components.UIPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MainScreen extends UIFrame {
	private UIPanel mainPanel;
	private UIContentPanel contentPanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainScreen() {
		init();
	}
	
	private void init() {
		this.mainPanel = new UIPanel();
		
		this.contentPanel = new UIContentPanel("Greek Paradise", "...you're at home!", "geek-paradise-logo_64", this.mainPanel, false);
		
		UIButton adminBtn = new UIButton("Admin");
		adminBtn.setBounds(177, 58, 85, 54);
		adminBtn.setIcon("suggesstions_64");
		adminBtn.setPreferredSize(new Dimension(200, 100));
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Admin");
			}
		});
		
		UIButton menuBtn = new UIButton("Menu");
		menuBtn.setPreferredSize(new Dimension(200, 100));
		menuBtn.setBounds(177, 143, 85, 54);
		menuBtn.setIcon("welcome_64");
		menuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Menu");
			}
		});
		
		mainPanel.add(menuBtn);
		mainPanel.add(adminBtn);
		
		getContentPane().add(contentPanel);
		basicIntialization();
		
	}
	
	private void basicIntialization() {
		setSize(new Dimension(420, 300));
		setTitle("Main Screen");
	}
	

}
