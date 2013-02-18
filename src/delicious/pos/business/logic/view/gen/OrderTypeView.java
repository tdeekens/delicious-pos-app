package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class OrderTypeView extends BaseView
{
	private String name;
	
	private Integer priceId;
	
	public OrderTypeView(String name, Integer priceId)
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
	
	public Integer getPriceId()
	{
		return priceId;
	}
	
	public void setPriceId(Integer priceId)
	{
		this.priceId = priceId;
	}	
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("name", getName());
		viewAsMap.put("priceId", getPriceId().toString());
		return viewAsMap;
	}
}
