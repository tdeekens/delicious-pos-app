package delicious.pos.ui.components.extensions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import delicious.pos.ui.event.SwitchPanel;

public class UIFooterPanel extends UIPanel {

	private static final long serialVersionUID = 1L;
	private UIButton iconRight;
	private SwitchPanel switchPanel;
	private UIButton iconLeft;
	private UILabel stateCenter;
	
	public UIFooterPanel(ImageIcon iconLeft, ImageIcon iconRight, String stateText, SwitchPanel switchPanel) {
		this.iconRight = new UIButton(iconLeft);
		this.iconLeft = new UIButton(iconRight);
		this.stateCenter = new UILabel(stateText);
		
		this.switchPanel = switchPanel;
		stateCenter.setHorizontalAlignment(SwingConstants.CENTER);
		
		EmptyBorder eBorder = new EmptyBorder(10, 10, 10, 10); 
        LineBorder lBorder = new LineBorder(Color.WHITE); 
        
        this.iconRight.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder)); 
        this.iconLeft.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
        this.stateCenter.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		
		this.iconRight.setBackground(Color.WHITE);
		this.iconLeft.setBackground(Color.WHITE);
		this.stateCenter.setBackground(Color.WHITE);
		
		this.stateCenter.setFont(new Font("Palatino", Font.BOLD, 14));
		
		this.setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		this.add(this.iconRight, BorderLayout.WEST);
		this.add(this.stateCenter, BorderLayout.CENTER);
		this.add(this.iconLeft, BorderLayout.EAST);
		
		if(this.switchPanel != null) {
			this.setupSwitchPanelActions();
		}
	}
	
	private void setupSwitchPanelActions() {
		this.iconRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel.previous();
			}
		});
		
		this.iconLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel.next();
			}
		});
	}
}
