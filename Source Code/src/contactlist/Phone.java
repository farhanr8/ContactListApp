package contactlist;

public class Phone {

	private int contact_id;
	private String type;
	private String areaCode;
	private String number;


	public Phone(String type, String areaCode, String num) {
		this(0, type, areaCode, num);
	}
	
	public Phone(int id, String type, String areaCode, String num) {
		
		super();
		this.contact_id = id;
		this.type = type;
		this.areaCode = areaCode;
		this.number = num;

	}

	public int getId() {
		return contact_id;
	}

	public void setId(int id) {
		this.contact_id = id;
	}
	
	public String getPhoneType() {
		return type;
	}

	public void setPhoneType(String type) {
		this.type = type;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String num) {
		this.number = num;
	}
	
}
