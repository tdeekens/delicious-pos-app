package delicious.pos.state;

import java.util.HashMap;

import delicious.pos.ui.implementation.App;

public class OrderState {
	private Object customer;
	private String orderType;
	private Object deliveryType;
	private HashMap<Object, Object> orderItems;
	
	public OrderState() {
		this.orderItems = new HashMap<Object, Object>();
	}
	
	public void addOrderItem(Object orderItem, Object priceItem) {
		this.orderItems.put(priceItem, orderItem);
		
		App.put("Added item: " + orderItem + " with price: " + priceItem);
	}
	
	public void removeOrderItem(Object orderItem, Object priceItem) {
		this.orderItems.values().remove(orderItem);
		
		App.put("Removed item: " + orderItem + " with price: " + priceItem);
	}
	
	public void setCustomer(Object customer) {
		this.customer = customer;
		
		App.put("Setup customer: " + customer);
	}
	
	public HashMap<Object, Object> getOrderedItems() {
		return this.orderItems;
	}
	
	public void setDeliveryType(Object deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	public Object getDeliveryType() {
		return this.deliveryType;
	}
	
	public void setOrderType(String orderType) {
		App.put("Set ordertype: " + orderType);
		
		this.orderType = orderType;
	}
	
	public String getOrderType() {
		return this.orderType;
	}
}
