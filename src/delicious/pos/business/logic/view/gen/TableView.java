package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class TableView extends BaseView
{
	private Integer id;
	
	private String number;
	
	public TableView()
	{
	}

	public TableView(int id,  String number)
	{
		setId(id);
		setNumber(number);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("id", getId().toString());
		viewAsMap.put("number", getNumber());
		return viewAsMap;
	}
}
