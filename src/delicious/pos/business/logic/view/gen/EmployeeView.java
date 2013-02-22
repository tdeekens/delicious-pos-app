package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class EmployeeView extends BaseView
{
	private String userName;
	
	private Float salary;
	
	private String phone;
	
	private String position;
	
	public EmployeeView()
	{
	}

	public EmployeeView(String userName,  Float salary,  String phone,  String position)
	{
		setUserName(userName);
		setSalary(salary);
		setPhone(phone);
		setPosition(position);
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public  Float getSalary()
	{
		return salary;
	}
	
	public  String getPhone()
	{
		return phone;
	}
	
	public  String getPosition()
	{
		return position;
	}
	
	public void setSalary( Float salary)
	{
		this.salary = salary;
	}
	
	public void setPhone( String phone)
	{
		this.phone = phone;
	}
	
	public void setPosition( String position)
	{
		this.position = position;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("userName", getUserName());
		viewAsMap.put("salary", getSalary().toString());
		viewAsMap.put("phone", getPhone());
		viewAsMap.put("position", getPosition());
		return viewAsMap;
	}
}
