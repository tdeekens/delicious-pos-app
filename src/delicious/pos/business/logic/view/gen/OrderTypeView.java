package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class OrderTypeView extends BaseView
{
	private String name;
	
	private Integer priceId;
	
	private String target;
	
	public OrderTypeView()
	{
	}

	public OrderTypeView(String name, Integer priceId, String target)
	{
		setName(name);
		setPriceId(priceId);
		setTarget(target);
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
	
	public String getTarget()
	{
		return target;
	}
	
	public void setTarget(String target)
	{
		this.target = target;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("name", getName());
		viewAsMap.put("priceId", getPriceId().toString());
		return viewAsMap;
	}
}
