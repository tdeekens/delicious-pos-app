package delicious.pos.business.logic.view;

public class CustomerView
{
	private String firstName;
	
	private String lastName;
	
	private String Street;
	
	private String ZIP;
	
	private String City;
	
	private String Phone;
	
	public CustomerView(String firstName, String lastName,  String Street,  String ZIP,  String City,  String Phone)
	{
		setFirstName(firstName);
		setLastName(firstName);
		setStreet(Street);
		setZIP(ZIP);
		setCity(City);
		setPhone(Phone);
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}	
	
	public  String getStreet()
	{
		return Street;
	}
	
	public  String getZIP()
	{
		return ZIP;
	}
	
	public  String getCity()
	{
		return City;
	}
	
	public  String getPhone()
	{
		return Phone;
	}
	
	public void setStreet( String Street)
	{
		this.Street = Street;
	}
	
	public void setZIP( String ZIP)
	{
		this.ZIP = ZIP;
	}
	
	public void setCity( String City)
	{
		this.City = City;
	}
	
	public void setPhone( String Phone)
	{
		this.Phone = Phone;
	}
}
