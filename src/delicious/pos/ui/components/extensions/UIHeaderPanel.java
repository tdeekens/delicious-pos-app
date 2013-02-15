package delicious.pos.ui.components.extensions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;

public class UIHeaderPanel extends UIPanel {

	private static final long serialVersionUID = 1L;
	private UILabel firstHeader;
	private UILabel secondHeader;
	private JLabel headerIcon;
	
	public UIHeaderPanel(String firstHeader, String secondHeader, Icon icon) {
		this.firstHeader = new UILabel(firstHeader);
		this.secondHeader = new UILabel(secondHeader);
		this.headerIcon = new JLabel(icon);
		
		this.firstHeader.setFont(new Font("Palatino", Font.BOLD, 22));
		this.secondHeader.setFont(new Font("Palatino", Font.ITALIC, 14));
				
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints iconConstraints = new GridBagConstraints();
		iconConstraints.gridx = 0;
		iconConstraints.anchor = GridBagConstraints.WEST;
		iconConstraints.gridy = 0;
		iconConstraints.gridheight = 2;
		iconConstraints.insets = new Insets(10, 10, 10, 10);
		
		GridBagConstraints firstHeaderConstraints = new GridBagConstraints();
		firstHeaderConstraints.gridx = 1;
		firstHeaderConstraints.anchor = GridBagConstraints.WEST;
		firstHeaderConstraints.gridy = 0;
		firstHeaderConstraints.insets = new Insets(10, 10, 0, 0);
		
		GridBagConstraints secondHeaderConstraints = new GridBagConstraints();
		secondHeaderConstraints.gridx = 1;
		secondHeaderConstraints.anchor = GridBagConstraints.WEST;
		secondHeaderConstraints.gridy = 1;
		secondHeaderConstraints.insets = new Insets(-5, 20, 0, 0);
		
		this.setBackground(Color.WHITE);
		this.add(this.firstHeader, firstHeaderConstraints);
		this.add(this.secondHeader, secondHeaderConstraints);
		this.add(this.headerIcon, iconConstraints);
	}
	
	public void setSize(Dimension dimension) {
		this.setPreferredSize(dimension);
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		this.revalidate();
		this.repaint();
	}
}
