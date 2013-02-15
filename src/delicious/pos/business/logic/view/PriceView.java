package delicious.pos.business.logic.view;

public class PriceView
{
	private Float value;
	
	private SizeView size;
	
	public PriceView(Float value, SizeView size)
	{
		setValue(value);
		setSize(size);
	}
	
	public Float getValue()
	{
		return value;
	}
	
	public void setValue(Float value)
	{
		this.value = value;
	}
	
	public SizeView getSize()
	{
		return size;
	}
	
	public void setSize(SizeView size)
	{
		this.size = size;
	}
}
