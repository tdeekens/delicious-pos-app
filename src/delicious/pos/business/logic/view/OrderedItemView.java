package delicious.pos.business.logic.view;

public class OrderedItemView extends BaseView 
{
	private Integer id;
	
	private Integer orderId;
	
	private String itemName;
	
	private Integer priceId;
	
	public OrderedItemView() 
	{
		
	}
	
	public OrderedItemView(Integer id, Integer orderId, String itemName, Integer priceId)
	{
		setId(id);
		setOrderId(orderId);
		setItemName(itemName);
		setPriceId(priceId);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getOrderId()
	{
		return orderId;
	}
	
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public Integer getPriceId()
	{
		return priceId;
	}
	
	public void setPriceId(Integer priceId)
	{
		this.priceId = priceId;
	}
}
