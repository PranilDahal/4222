
public class Address {

	public String PhoneNum;
	public String Street;
	public String City;
	public String State;

	public Address(java.lang.String phoneNum, java.lang.String street, java.lang.String city, String state) {
		super();
		PhoneNum = phoneNum;
		Street = street;
		City = city;
		State = state;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	} 
	
	

}
