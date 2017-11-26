
public class Musician {

	public String SSN;
	public String Name;
	public String Phone;
	
	public Musician(String sSN, String name, String phone) {
		super();
		SSN = sSN;
		Name = name;
		Phone = phone;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	
}
