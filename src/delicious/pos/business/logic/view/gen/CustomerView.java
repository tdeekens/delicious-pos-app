package delicious.pos.business.logic.view.gen;

import delicious.pos.business.logic.view.BaseView;
import java.util.HashMap;
import java.util.Map;

public class CustomerView extends BaseView
{
	private String firstName;
	
	private String lastName;
	
	private String street;
	
	private String zip;
	
	private String city;
	
	private String phone;
	
	public CustomerView() 
	{
		
	}
	
	public CustomerView(String firstName, String lastName,  String street,  String zip,  String city,  String phone)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setStreet(street);
		setZIP(zip);
		setCity(city);
		setPhone(phone);
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
		return street;
	}
	
	public  String getZIP()
	{
		return zip;
	}
	
	public  String getCity()
	{
		return city;
	}
	
	public  String getPhone()
	{
		return phone;
	}
	
	public void setStreet( String street)
	{
		this.street = street;
	}
	
	public void setZIP( String zip)
	{
		this.zip = zip;
	}
	
	public void setCity( String city)
	{
		this.city = city;
	}
	
	public void setPhone( String phone)
	{
		this.phone = phone;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> viewAsMap = new HashMap<String, String>();
		viewAsMap.put("firstName", getFirstName());	
		viewAsMap.put("lastName", getLastName());
		viewAsMap.put("street", getStreet());
		viewAsMap.put("zip", getZIP());
		viewAsMap.put("city", getCity());
		viewAsMap.put("phone", getPhone());
		return viewAsMap;
	}
	
	public String toString() {
		String toString;
		
		toString =
			getLastName()
			+ ", "
			+ getFirstName()
			+ " ("
			+ getZIP()
			+ " "
			+ getCity()
			+ ")";
		
		return toString;
	}
}
