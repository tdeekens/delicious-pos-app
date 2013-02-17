package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;

public class TableView extends BaseView
{
	private int id;
	
	private String number;
	
	public TableView(int id,  String number)
	{
		setId(id);
		setNumber(number);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public  String getNumber()
	{
		return number;
	}
	
	public void setNumber( String number)
	{
		this.number = number;
	}
}
