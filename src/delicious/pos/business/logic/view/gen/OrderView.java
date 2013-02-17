package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;

public class OrderView extends BaseView
{
	private int id;
	
	private String orderTypeName;
	
	private String employeeUserName;
	
	private int customerId;
	
	private int tableId;
	
	public OrderView(int id, String orderTypeName, String employeeUserName, int customerId, int tableId)
	{
		setId(id);
		setOrderTypeName(orderTypeName);
		setEmployeeUserName(employeeUserName);
		setCustomerId(customerId);
		setTableId(tableId);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
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
	
	public int getCustomerId()
	{
		return customerId;
	}
	
	public int getTableId()
	{
		return tableId;
	}
	
	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}
	
	public void setTableId(int tableId)
	{
		this.tableId = tableId;
	}
}
