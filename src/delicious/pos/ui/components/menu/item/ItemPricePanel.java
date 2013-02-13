package delicious.pos.ui.components.menu.item;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UILabel;
import delicious.pos.ui.components.UIPanel;

public class ItemPricePanel extends UIPanel {
	
	private UILabel sizeLbl;
	private Object price;
	private UIButton priceBtn;
	private ItemPricesPanel parentPanel;
	
	public ItemPricePanel() {
		this.init();
	}
	
	public ItemPricePanel(Object price, ItemPricesPanel parentPanel) {
		super();
		this.price = price;
		this.parentPanel = parentPanel;
		this.init();
	}
	
	public void init() {
		setLayout(new BorderLayout(0, 0));
		
		sizeLbl = new UILabel();
		sizeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(sizeLbl, BorderLayout.NORTH);
		
		priceBtn = new UIButton();
		priceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});

		add(priceBtn, BorderLayout.SOUTH);
				
		this.setupLabels();
	}
	
	private void setupLabels() {
		priceBtn.setText("7.99");
		sizeLbl.setText("Size");
	}
	
	public void select() {
		this.parentPanel.orderItem(this);
		this.setBackground(Color.green);
	}
	
	public UIButton getPriceBtn() {
		return this.priceBtn;
	}
	
	public Object getPrice() {
		return this.price;
	}
	
	public void deselect() {
		this.setBackground(UIManager.getColor("Button.background"));
		this.validate();
		this.repaint();
	}

	public void hidePrices(Object except) {
		this.priceBtn.setEnabled(false);
		
		if ( ! except.equals(this.price) ) {
			this.setVisible(false);
		}
	}
}
