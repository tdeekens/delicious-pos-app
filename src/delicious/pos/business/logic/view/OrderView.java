package delicious.pos.business.logic.view;

public class OrderView
{
	private int id;
	
	private OrderTypeView orderType;
	
	private EmployeeView employee;
	
	public OrderView(int id)
	{
		setId(id);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public OrderTypeView getOrderType()
	{
		return orderType;
	}
	
	public void setOrderType(OrderTypeView orderType)
	{
		this.orderType = orderType;
	}
	
	public EmployeeView getEmployee()
	{
		return employee;
	}
	
	public void setEmployee(EmployeeView employee)
	{
		this.employee = employee;
	}
}
