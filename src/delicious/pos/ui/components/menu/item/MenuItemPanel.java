package delicious.pos.ui.components.menu.item;

import delicious.pos.ui.components.*;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Insets;
import java.util.ArrayList;

import delicious.pos.ui.*;


public class MenuItemPanel extends UIPanel {

	public MenuItemPanel() {
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(400, 50));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblItemName = new UILabel("Item Name");
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.insets = new Insets(0, 0, 0, 5);
		gbc_lblItemName.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 0;
		add(lblItemName, gbc_lblItemName);
		
		JPanel panel = new ItemSizesPanel(new ArrayList());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
	}

}
