package delicious.pos.ui.components.menu.item;


import delicious.pos.creator.datastructure.model.datastructure.impl.ItemImpl;
import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UIPanel;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSizesPanel extends UIPanel {	
	
	private List<ItemSizePanel> itemSizes = null;
	private List<ItemImpl> menuItems = null;
	
	public ItemSizesPanel(List<ItemImpl> menuItems) {
		this.menuItems = menuItems;
		this.itemSizes = new ArrayList<ItemSizePanel>();
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		this.setupMenuItems();
		
		UIButton button = new UIButton("-");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		add(button);
	}
	
	public void addItem(ItemSizePanel itemSize) {
		this.add(itemSize);
	}
	
	public void selectSize(ItemSizePanel itemSize) {
		itemSize.select();
	}
	
	public void setupMenuItems() {
		//Just to something
		this.add(new ItemSizePanel());
		this.add(new ItemSizePanel());
		
		for(ItemImpl menuItem : this.menuItems) {
			this.add(new ItemSizePanel(menuItem));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void deselectSizes() {
		for(ItemSizePanel itemSize : this.itemSizes) {
			itemSize.disable();
		}
	}
}
