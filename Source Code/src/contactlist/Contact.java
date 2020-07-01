package contactlist;

public class Contact {

	private int contact_id;
	private String lastName;
	private String middleName;
	private String firstName;

	public Contact(String lastName, String middleName, String firstName) {
		this(0, lastName, middleName, firstName);

	}
	
	public Contact(int id, String lastName, String middleName, String firstName) {
		super();
		this.contact_id = id;
		this.lastName = lastName;
		this.middleName = middleName;
		this.firstName = firstName;

	}

	public int getId() {
		return contact_id;
	}

	public void setId(int id) {
		this.contact_id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Override
	public String toString() {
		return String
				.format("Contact [lastName=%s, middleName=%s, firstName=%s]",
						lastName, middleName, firstName);
	}
	
	@Override 
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		Contact guest = (Contact) obj; 
		return contact_id == guest.contact_id;
	}
	
	@Override
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode()); 
		result = prime * result + contact_id; 
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode()); 
		return result; 
	}	
		
}
