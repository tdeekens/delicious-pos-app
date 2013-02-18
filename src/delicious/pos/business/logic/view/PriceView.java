package delicious.pos.business.logic.view;

import java.util.HashMap;
import java.util.Map;


public class PriceView extends BaseView
{
	private Integer id;
	
	private Float value;
	
	private String sizeValue;
	
	private String itemName;
	
	public PriceView(Integer id, Float value, String sizeValue, String itemName)
	{
		setId(id);
		setValue(value);
		setSizeValue(sizeValue);
		setItemName(itemName);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Float getValue()
	{
		return value;
	}
	
	public void setValue(Float value)
	{
		this.value = value;
	}
	
	public String getSizeValue()
	{
		return sizeValue;
	}
	
	public void setSizeValue(String sizeValue)
	{
		this.sizeValue = sizeValue;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("id", getId().toString());
		viewAsMap.put("value", getValue().toString());	
		viewAsMap.put("sizeValue", getSizeValue());	
		viewAsMap.put("itemName", getItemName());	
		return viewAsMap;
	}
}
