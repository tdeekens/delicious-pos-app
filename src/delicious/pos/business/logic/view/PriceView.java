package delicious.pos.business.logic.view;


public class PriceView extends BaseView
{
	private int id;
	
	private Float value;
	
	private String sizeValue;
	
	private String itemName;
	
	public PriceView(int id, Float value, String sizeValue, String itemName)
	{
		setId(id);
		setValue(value);
		setSizeValue(sizeValue);
		setItemName(itemName);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
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
}
