package contactlist;

public class Address {

	private int contact_id;
	private String type;
	private String address;
	private String city;
	private String state;
	private String zip;


	public Address(String type, String address, String city, String state, String zip) {
		this(0, type, address, city, state, zip);

	}
	
	public Address(int id, String type, String address, String city, String state, String zip) {
		super();
		this.contact_id = id;
		this.type = type;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;

	}

	public int getId() {
		return contact_id;
	}

	public void setId(int id) {
		this.contact_id = id;
	}
	
	public String getAddrType() {
		return type;
	}

	public void setAddrType(String type) {
		this.type = type;
	}

	public String getAddr() {
		return address;
	}

	public void setAddr(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
