package delicious.pos.ui.components.menu.item;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import delicious.pos.App;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.ui.components.extensions.UIButton;
import delicious.pos.ui.components.extensions.UILabel;
import delicious.pos.ui.components.extensions.UIPanel;

public class ItemPricePanel extends UIPanel {
	
	private UILabel sizeLbl;
	private PriceView price;
	private UIButton priceBtn;
	private ItemPricesPanel parentPanel;
	
	public ItemPricePanel() {
		this.init();
	}
	
	public ItemPricePanel(PriceView price, ItemPricesPanel parentPanel) {
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
		priceBtn.setText(Float.toString(this.price.getValue()) + " " + App.labels.get("currency"));
		sizeLbl.setText(this.price.getSizeValue() != null ? this.price.getSizeValue() : "");
	}
	
	public void select() {
		this.parentPanel.orderItem(this);
		//this.setBackground(Color.green);
	}
	
	public UIButton getPriceBtn() {
		return this.priceBtn;
	}
	
	public PriceView getPrice() {
		return this.price;
	}
	
	public void deselect() {
		this.setBackground(UIManager.getColor("Button.background"));
		this.validate();
		this.repaint();
	}

	public void hidePrices(PriceView except) {
		this.priceBtn.setEnabled(false);
		
		if ( Float.compare(except.getValue(), price.getValue()) != 0 ) {
			this.setVisible(false);
		}
	}
}
