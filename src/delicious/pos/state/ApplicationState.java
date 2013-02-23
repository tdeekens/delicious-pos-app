package delicious.pos.state;

import java.util.HashMap;

import delicious.pos.business.logic.dao.PriceDAO;
import delicious.pos.business.logic.view.PriceView;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.business.logic.view.gen.ItemView;
import delicious.pos.business.logic.view.gen.OrderTypeView;

public class ApplicationState {
	private CustomerView customer;
	private OrderTypeView orderType;
	private Float completePrice;
	private HashMap<PriceView, ItemView> orderItems;
	private PriceDAO priceDAO;
	private Float orderTypePrice;
	private Integer orderTargetId;
	
	public ApplicationState() {
		this.orderItems = new HashMap<PriceView, ItemView>();
		this.completePrice = (float) 0.0;
		this.orderTypePrice = (float) 0.0;
		this.priceDAO = new PriceDAO();
	}
	
	public void addOrderItem(ItemView orderItem, PriceView priceItem) {
		this.orderItems.put(priceItem, orderItem);
		
		this.addToCompletePrice(priceItem.getValue());
	}
	
	public void removeOrderItem(ItemView orderItem, PriceView priceItem) {
		this.orderItems.values().remove(orderItem);
		
		this.substractFromCompletePrice(priceItem.getValue());
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
		PriceView price;
		
		if(this.orderType != null && this.orderType.getPriceId() != 0) {
			price = this.priceDAO.findById(this.orderType.getPriceId());
			this.substractFromCompletePrice(price.getValue());
		}
		
		if(orderType.getPriceId() != 0) {
			price = this.priceDAO.findById(orderType.getPriceId());
			this.addToCompletePrice(price.getValue());
			this.orderTypePrice = price.getValue();
		} else {
			this.orderTypePrice = (float) 0.0;
		}
		
		this.orderType = orderType;
	}
	
	public OrderTypeView getOrderType() {
		return this.orderType;
	}
	
	public Float getCompletePrice() {
		return this.completePrice;
	}
	
	private void addToCompletePrice(Float price) {
		this.completePrice = this.completePrice + price;
	}
	
	private void substractFromCompletePrice(Float price) {
		this.completePrice = this.completePrice - price;
	}
	
	public Float getOrderTypePrice() {
		return this.orderTypePrice;
	}
	
	public void setOrderTargetId(Integer orderTargetId) {
		this.orderTargetId = orderTargetId;
	}
	
	public Integer getOrderTargetId() {
		return this.orderTargetId;
	}
}
