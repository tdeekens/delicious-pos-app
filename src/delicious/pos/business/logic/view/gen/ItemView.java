package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class ItemView extends BaseView
{
	private String name;
	
	private Integer priceId;
	
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
	
	public Integer getPriceId()
	{
		return priceId;
	}
	
	public void setPriceId(Integer priceId)
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("name", getName());	
		viewAsMap.put("priceId", getPriceId().toString());
		viewAsMap.put("description", getDescription());
		return viewAsMap;
	}
}
