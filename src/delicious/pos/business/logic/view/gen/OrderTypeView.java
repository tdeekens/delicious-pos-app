package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;

public class OrderTypeView extends BaseView
{
	private String name;
	
	private int priceId;
	
	public OrderTypeView(String name, int priceId)
	{
		setName(name);
		setPriceId(priceId);
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
}
