package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class ItemView extends BaseView
{
	private String name;
	
	private String description;
	
	public ItemView()
	{
	}

	public ItemView(String name,  String description)
	{
		setName(name);
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
		viewAsMap.put("description", getDescription());
		return viewAsMap;
	}
}
