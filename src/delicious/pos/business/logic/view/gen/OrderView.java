package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class OrderView extends BaseView
{
	private Integer id;
	
	private String orderTypeName;
	
	private String employeeUserName;
	
	private int customerId;
	
	private int tableId;
	
	public OrderView() 
	{
		
	}
	
	public OrderView(int id, String orderTypeName, String employeeUserName, int customerId, int tableId)
	{
		setId(id);
		setOrderTypeName(orderTypeName);
		setEmployeeUserName(employeeUserName);
		setCustomerId(customerId);
		setTableId(tableId);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getOrderTypeName()
	{
		return orderTypeName;
	}
	
	public void setOrderTypeName(String orderTypeName)
	{
		this.orderTypeName = orderTypeName;
	}
	
	public String getEmployeeUserName()
	{
		return employeeUserName;
	}
	
	public void setEmployeeUserName(String employeeUserName)
	{
		this.employeeUserName = employeeUserName;
	}
	
	public Integer getCustomerId()
	{
		return customerId;
	}
	
	public Integer getTableId()
	{
		return tableId;
	}
	
	public void setCustomerId(Integer customerId)
	{
		this.customerId = customerId;
	}
	
	public void setTableId(Integer tableId)
	{
		this.tableId = tableId;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("id", getId().toString());
		viewAsMap.put("orderTypeName", getOrderTypeName());
		viewAsMap.put("employeUserName", getEmployeeUserName());
		viewAsMap.put("customerId", getCustomerId().toString());
		viewAsMap.put("tableId", getTableId().toString());
		return viewAsMap;
	}
}
