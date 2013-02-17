package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;

public class ItemView extends BaseView
{
	private String name;
	
	private int priceId;
	
	private String description;
	
	public ItemView(String name, int priceId,  String description)
	{
		setName(name);
		setPriceId(priceId);
		setDescription(description);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getPriceId()
	{
		return priceId;
	}
	
	public void setPriceId(int priceId)
	{
		this.priceId = priceId;
	}	
	
	public  String getDescription()
	{
		return description;
	}
	
	public void setDescription( String description)
	{
		this.description = description;
	}
}
