package delicious.pos.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UIFooterPanel extends UIPanel {

	private static final long serialVersionUID = 1L;
	private JLabel iconRight;
	private JLabel iconLeft;
	private JLabel stateCenter;
	
	public UIFooterPanel(ImageIcon iconLeft, ImageIcon iconRight, String stateText) {
		this.iconRight = new JLabel(iconLeft);
		this.iconLeft = new JLabel(iconRight);
		this.stateCenter = new JLabel(stateText);
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
	}
}
