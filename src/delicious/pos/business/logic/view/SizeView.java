package delicious.pos.business.logic.view;

import java.util.HashMap;
import java.util.Map;


public class SizeView extends BaseView
{
	private String value;
	
	public SizeView(String value)
	{
		setValue(value);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("value", getValue());		
		return viewAsMap;
	}
}
