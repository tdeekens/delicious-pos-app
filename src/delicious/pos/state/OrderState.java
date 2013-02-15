package delicious.pos.state;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import delicious.pos.ui.implementation.App;

public class OrderState {
	private Object customer;
	private HashMap<Object, Object> orderItems;
	private HashMap<Object, Object> priceItems;
	
	public OrderState() {
		this.orderItems = new HashMap<Object, Object>();
		this.priceItems = new HashMap<Object, Object>();
	}
	
	public void addOrderItem(Object orderItem, Object priceItem) {
		this.orderItems.put(priceItem, orderItem);
		this.priceItems.put(orderItem, priceItem);
		
		App.put("Added item: " + orderItem + " with price: " + priceItem);
	}
	
	public void removeOrderItem(Object orderItem, Object priceItem) {
		this.orderItems.remove(priceItem);
		this.priceItems.remove(orderItem);
		
		App.put("Removed item: " + orderItem + " with price: " + priceItem);
	}
	
	public void setCustomer(Object customer) {
		this.customer = customer;
		
		App.put("Setup customer: " + customer);
	}
}
