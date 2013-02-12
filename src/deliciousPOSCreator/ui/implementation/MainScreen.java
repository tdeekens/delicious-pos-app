package deliciousPOSCreator.ui.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import deliciousPOSCreator.ui.components.UIButton;
import deliciousPOSCreator.ui.components.UIFrame;
import deliciousPOSCreator.ui.components.UIPanel;

public class MainScreen extends UIFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		UIPanel mainPanel = new UIPanel();
		mainPanel.setLayout(new BorderLayout());

		UIPanel btnPanel = new UIPanel();
		btnPanel.setLayout(null);
		
		UIButton adminBtn = new UIButton();
		adminBtn.setBounds(177, 58, 85, 54);
		adminBtn.setText("Admin");
		btnPanel.add(adminBtn);
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Admin");
			}
		});
		
		UIButton menuBtn = new UIButton();
		menuBtn.setBounds(177, 143, 85, 54);
		menuBtn.setText("Menu");
		btnPanel.add(menuBtn);
		menuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Menu");
			}
		});
		
		mainPanel.add(btnPanel, BorderLayout.CENTER);
		
		getContentPane().add(mainPanel);
		basicIntialization();
		
	}
	
	private void basicIntialization() {
		setSize(new Dimension(420, 300));
		setTitle("Main Screen");
	}
	

}
