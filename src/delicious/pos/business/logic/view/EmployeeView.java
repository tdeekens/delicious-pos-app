package delicious.pos.business.logic.view;

public class EmployeeView
{
	private String userName;
	
	private Float Salary;
	
	private String Phone;
	
	private String Position;
	
	public EmployeeView(String userName,  Float Salary,  String Phone,  String Position)
	{
		setUserName(userName);
		setSalary(Salary);
		setPhone(Phone);
		setPosition(Position);
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
		return Salary;
	}
	
	public  String getPhone()
	{
		return Phone;
	}
	
	public  String getPosition()
	{
		return Position;
	}
	
	public void setSalary( Float Salary)
	{
		this.Salary = Salary;
	}
	
	public void setPhone( String Phone)
	{
		this.Phone = Phone;
	}
	
	public void setPosition( String Position)
	{
		this.Position = Position;
	}
}
