package delicious.pos.ui.components.menu.item;


import delicious.pos.ui.components.UIPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.BorderLayout;

public class ItemSize extends UIPanel {

	/**
	 * Create the panel.
	 */
	public ItemSize() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSize, BorderLayout.NORTH);
		
		JButton button = new JButton("7.99");
		add(button, BorderLayout.SOUTH);

	}

}
