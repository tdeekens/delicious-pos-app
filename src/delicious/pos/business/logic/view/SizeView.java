package delicious.pos.business.logic.view;

public class SizeView
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
}