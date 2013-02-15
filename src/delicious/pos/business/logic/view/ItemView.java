package delicious.pos.business.logic.view;

public class ItemView
{
	private String name;
	
	private PriceView price;
	
	private String Description;
	
	public ItemView(String name, PriceView price,  String Description)
	{
		setName(name);
		setPrice(price);
		setDescription(Description);
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
	
	public  String getDescription()
	{
		return Description;
	}
	
	public void setDescription( String Description)
	{
		this.Description = Description;
	}
}
