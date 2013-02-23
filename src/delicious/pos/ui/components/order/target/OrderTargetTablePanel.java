package delicious.pos.ui.components.order.target;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.BoxLayout;

import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.business.logic.view.gen.TableView;
import delicious.pos.business.logic.dao.gen.TableDAO;

public class OrderTargetTablePanel extends UIPanel {
	private TableView targetView;
	private TableDAO targetDAO;
	
	public OrderTargetTablePanel(TableView targetView) {
		super();
		
		this.targetView = targetView;
		this.targetDAO = new TableDAO();
		
		this.init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(300, 20));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
			UIPanel innerPanelNumber = new UIPanel();
			innerPanelNumber.setLayout(new BorderLayout());
			
			UILabel lblTargetNumber = new UILabel("Number" + ":");
			final JTextField txtFieldNumber = new JTextField("", 15);
			
			txtFieldNumber.addKeyListener(new KeyAdapter() {
	            public void keyReleased(KeyEvent e) {
	                JTextField textFieldNumber = (JTextField) e.getSource();
	                
	                targetView.setNumber(textFieldNumber.getText());
	            }
			});
			
			innerPanelNumber.add(lblTargetNumber, BorderLayout.WEST);
			innerPanelNumber.add(txtFieldNumber, BorderLayout.CENTER);
			
			this.add(innerPanelNumber);
	}
}

