package delicious.pos.state;

import java.util.HashMap;

import delicious.pos.App;
import delicious.pos.business.logic.view.CustomerView;
import delicious.pos.business.logic.view.ItemView;
import delicious.pos.business.logic.view.OrderTypeView;
import delicious.pos.business.logic.view.PriceView;

public class ApplicationState {
	private CustomerView customer;
	private OrderTypeView orderType;
	private HashMap<PriceView, ItemView> orderItems;
	
	public ApplicationState() {
		this.orderItems = new HashMap<PriceView, ItemView>();
	}
	
	public void addOrderItem(ItemView orderItem, PriceView priceItem) {
		this.orderItems.put(priceItem, orderItem);
	}
	
	public void removeOrderItem(ItemView orderItem, PriceView priceItem) {
		this.orderItems.values().remove(orderItem);
	}
	
	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}
	
	public CustomerView getCustomer() {
		return this.customer;
	}
	
	public HashMap<PriceView, ItemView> getOrderedItems() {
		return this.orderItems;
	}
	
	public void setOrderType(OrderTypeView orderType) {		
		this.orderType = orderType;
	}
	
	public OrderTypeView getOrderType() {
		return this.orderType;
	}
}