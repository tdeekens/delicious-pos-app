package delicious.pos.state;

import java.util.HashMap;
import java.util.Map;

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
	private HashMap<PriceView, Integer> orderedAmounts;
	private HashMap<String, String> orderTargetInputs;
	private PriceDAO priceDAO;
	private Float orderTypePrice;
	private Integer orderTargetId;
	
	public ApplicationState() {
		this.orderItems = new HashMap<PriceView, ItemView>();
		this.orderedAmounts = new HashMap<PriceView, Integer>();
		this.orderTargetInputs = new HashMap<String, String>();
		this.completePrice = (float) 0.0;
		this.orderTypePrice = (float) 0.0;
		this.priceDAO = new PriceDAO();
	}
	
	public void addOrderItem(ItemView orderItem, PriceView priceItem) {
		if( this.orderItems.containsKey(priceItem) ) {
			this.orderedAmounts.put(priceItem, 
				this.orderedAmounts.get(priceItem) + 1
			);
		} else {
			this.orderItems.put(priceItem, orderItem);
			this.orderedAmounts.put(priceItem, 1);
		}
		
		this.addToCompletePrice(priceItem.getValue());
	}
	
	public void removeOrderItem(ItemView orderItem, PriceView priceItem) {
		if(this.orderedAmounts.get(priceItem) > 1) {
			this.orderedAmounts.put(priceItem, 
				this.orderedAmounts.get(priceItem) - 1
			);
		} else {
			this.orderItems.values().remove(orderItem);
			this.orderedAmounts.put(priceItem, 0);
		}
		
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
	
	public Integer getOrderedItemsSize() {
		Integer size = 0;
		
		HashMap<PriceView, Integer> sizeMap = new HashMap<PriceView, Integer>(this.orderedAmounts);
		
		for (Map.Entry<PriceView, Integer> entry : sizeMap.entrySet()) {
			PriceView price = entry.getKey();
			size += this.orderedAmounts.get(price);
		}
		
		return size;
	}
	
	public void setTargetInput(String ident, String input) {
		this.orderTargetInputs.put(ident, input);
	}
	
	public HashMap<String, String> getTargetInputs() {
		return this.orderTargetInputs;
	}
	
	public HashMap<PriceView, Integer> getOrderedAmounts() {
		return this.orderedAmounts;
	}
	
	public Integer getOrderedAmount(PriceView priceView) {
		return this.orderedAmounts.get(priceView);
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
	
	public void reset() {
		this.orderItems = new HashMap<PriceView, ItemView>();
		this.orderedAmounts = new HashMap<PriceView, Integer>();
		this.orderTargetInputs = new HashMap<String, String>();
		this.orderTargetId = null;
		this.completePrice = (float) 0.0;
		this.orderTypePrice = (float) 0.0;
		this.priceDAO = new PriceDAO();
	}
}
