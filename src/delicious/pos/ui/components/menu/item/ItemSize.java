package delicious.pos.ui.components.menu.item;


import delicious.pos.ui.components.*;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSize extends UIPanel {

	public ItemSize() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblSize = new UILabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSize, BorderLayout.NORTH);
		
		JButton button = new UIButton("7.99");
		button.setBackground(Color.gray);
		add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public void select() {
		this.setBackground(Color.green);
	}
	
	public void deselect() {
		this.setBackground(Color.gray);
	}
}
