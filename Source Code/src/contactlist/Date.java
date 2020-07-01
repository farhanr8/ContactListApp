package contactlist;

public class Date {

	private int contact_id;
	private String type;
	private String date;


	public Date(String type, String date) {
		this(0, type, date);
	}
	
	public Date(int id, String type, String date) {
		
		super();
		this.contact_id = id;
		this.type = type;
		this.date = date;

	}

	public int getId() {
		return contact_id;
	}

	public void setId(int id) {
		this.contact_id = id;
	}
	
	public String getDateType() {
		return type;
	}

	public void setDateType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
