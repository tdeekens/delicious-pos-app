package delicious.pos.ui.components.menu.order;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import delicious.pos.ui.components.UIFrame;
import delicious.pos.ui.components.UIPanel;

public class OrderTypePanel extends UIFrame {


	public OrderTypePanel() {
		init();
	}
	
	private void init() {
		
		UIPanel mainPanel = new UIPanel();
		mainPanel.setLayout(new BorderLayout());

		UIPanel btnPanel = new UIPanel();
		btnPanel.setLayout(null);
		
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		
		groupButton();
	}
	
	private void groupButton() {
		
		String radioText = "";
		
	JRadioButton rdbtn1 = new JRadioButton("Take away");
	rdbtn1.setHorizontalAlignment(SwingConstants.WEST);
	getContentPane().add(rdbtn1, BorderLayout.WEST);
	
	JRadioButton rdbtn2 = new JRadioButton("Eat here");
	rdbtn2.setHorizontalAlignment(SwingConstants.CENTER);
	getContentPane().add(rdbtn2, BorderLayout.CENTER);
	
	JRadioButton rdbtn3 = new JRadioButton("Delivery");
	rdbtn3.setHorizontalAlignment(SwingConstants.EAST);
	getContentPane().add(rdbtn3, BorderLayout.EAST);
	
	ButtonGroup bg1 = new ButtonGroup();
	
	bg1.add(rdbtn1);
	bg1.add(rdbtn2);
	bg1.add(rdbtn3);
	
	if (rdbtn1.isSelected()) radioText = rdbtn1.getText();
	if (rdbtn2.isSelected()) radioText = rdbtn2.getText();
	if (rdbtn3.isSelected()) radioText = rdbtn3.getText();	
	
	}
	
	private void basicIntialization() {
		setSize(new Dimension(421, 249));
		setTitle("Selecting Order");
	}
}

