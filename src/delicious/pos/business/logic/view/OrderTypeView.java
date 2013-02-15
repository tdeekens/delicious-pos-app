package delicious.pos.business.logic.view;

public class OrderTypeView
{
	private String name;
	
	private PriceView price;
	
	public OrderTypeView(String name, PriceView price)
	{
		setName(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public PriceView getPrice()
	{
		return price;
	}
	
	public void setPrice(PriceView price)
	{
		this.price = price;
	}	
}
