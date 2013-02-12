package delicious.pos.ui.components.menu.item;


import delicious.pos.ui.components.UIButton;
import delicious.pos.ui.components.UIPanel;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSizes extends UIPanel {	
	
	private List<ItemSize> itemSizes = null;
	
	public ItemSizes() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.itemSizes = new ArrayList<ItemSize>();
		
		//Just to something
		this.add(new ItemSize());
		this.add(new ItemSize());
		
		UIButton button = new UIButton("-");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(button);
	}
	
	public void addItem(ItemSize itemSize) {
		this.add(itemSize);
	}
	
	public void selectSize(ItemSize itemSize) {
		itemSize.select();
	}
	
	@SuppressWarnings("deprecation")
	public void deselectSizes() {
		for(ItemSize itemSize : this.itemSizes) {
			itemSize.disable();
		}
	}
}
