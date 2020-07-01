package contactlist;

import java.util.*;
import java.sql.*;
import java.io.*;

/*
 * Interface with MySQL Database
 */

public class ContactDAO {
	
	private Connection myConn;
	
	public ContactDAO() throws Exception {
		
		Properties props = new Properties();
		props.load(new FileInputStream("app.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("Connection successful: " + dburl);
	}
	
	/* -------- UPDATE Methods -------- */
	public void updateContact(Contact theContact) throws SQLException {
		
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("update CONTACT"
					+ " set FName=?, MName=?, LName=?"
					+ " where Contact_id=?");
			myStmt.setString(1, theContact.getFirstName());
			myStmt.setString(2, theContact.getMiddleName());
			myStmt.setString(3, theContact.getLastName());
			myStmt.setInt(4, theContact.getId());
			myStmt.executeUpdate();			
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	public void updateAddress(Address theAddress) throws SQLException {
			
			PreparedStatement myStmt = null;
			try {
				myStmt = myConn.prepareStatement("update ADDRESS"
						+ " set Address=?, City=?, State=?, Zip=?"
						+ " where Contact_id=? and Address_type=?");
				myStmt.setString(1, theAddress.getAddr());
				myStmt.setString(2, theAddress.getCity());
				myStmt.setString(3, theAddress.getState());
				myStmt.setString(4, theAddress.getZip());
				myStmt.setInt(5, theAddress.getId());
				myStmt.setString(6, theAddress.getAddrType());
				myStmt.executeUpdate();							
			}
			finally {
				close(null, myStmt, null);
			}
			
		}
	
	public void updatePhone(Phone thePhone) throws SQLException {
		
		PreparedStatement myStmt = null;
		try {

			myStmt = myConn.prepareStatement("update PHONE"
					+ " set Area_code=?, Number=?"
					+ " where Contact_id=? and Phone_type=?");
			myStmt.setString(1, thePhone.getAreaCode());
			myStmt.setString(2, thePhone.getNumber());
			myStmt.setInt(3, thePhone.getId());
			myStmt.setString(4, thePhone.getPhoneType());
			myStmt.executeUpdate();	
			
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	public void updateDate(Date theDate) throws SQLException {
		
		PreparedStatement myStmt = null;
		try {

				myStmt = myConn.prepareStatement("update DATE"
						+ " set Date=?"
						+ " where Contact_id=? and Date_type=?");
				myStmt.setString(1, theDate.getDate());
				myStmt.setInt(2, theDate.getId());
				myStmt.setString(3, theDate.getDateType());

				myStmt.executeUpdate();	
					
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	/* -------- DELETE Method -------- */
	public void deleteContact(int contactID) throws SQLException {
		
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from CONTACT where Contact_id=?");
			myStmt.setInt(1, contactID);
			myStmt.executeUpdate();			
		}
		finally {
			close(null,myStmt,null);
		}
		
	}
	
	/* -------- ADD Methods -------- */
	public int addContact(Contact theContact) throws Exception {

		PreparedStatement myStmt = null;
		PreparedStatement myIDStmt = null;
		ResultSet myRs = null;
		int id = 0;

		try {
			myStmt = myConn.prepareStatement("insert into CONTACT"
					+ " (Fname, Mname, Lname)"
					+ " values (?, ?, ?)");
			
			myStmt.setString(1, theContact.getFirstName());
			myStmt.setString(2, theContact.getMiddleName());
			myStmt.setString(3, theContact.getLastName());
			myStmt.executeUpdate();
//			return (int) myStmt.RETURN_GENERATED_KEYS;
			
			myIDStmt = myConn.prepareStatement("SELECT contact_id FROM Contact ORDER BY contact_id DESC LIMIT 1");
			myRs = myIDStmt.executeQuery();
			while (myRs.next()) {
				id = myRs.getInt("contact_id");
			}
			return id;		
		
			
		}
		finally {
			close(null, myStmt, null);
			close(null, myIDStmt, myRs);
		}
		
	}
	
	public void addAddress(Address theAddress) throws Exception {

		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("insert into ADDRESS"
					+ " (Contact_id, Address_type, Address, City, State, Zip)"
					+ " values (?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, Integer.toString(theAddress.getId()));
			myStmt.setString(2, theAddress.getAddrType());
			myStmt.setString(3, theAddress.getAddr());
			myStmt.setString(4, theAddress.getCity());
			myStmt.setString(5, theAddress.getState());
			myStmt.setString(6, theAddress.getZip());
			myStmt.executeUpdate();
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	public void addPhone(Phone thePhone) throws Exception {

		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("insert into PHONE"
					+ " (Contact_id, Phone_type, Area_code, Number)"
					+ " values (?, ?, ?, ?)");
			myStmt.setString(1, Integer.toString(thePhone.getId()));
			myStmt.setString(2, thePhone.getPhoneType());
			myStmt.setString(3, thePhone.getAreaCode());
			myStmt.setString(4, thePhone.getNumber());
			myStmt.executeUpdate();
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	public void addDate(Date theDate) throws Exception {

		PreparedStatement myStmt = null;

		try {
			myStmt = myConn.prepareStatement("insert into DATE"
					+ " (Contact_id, Date_type, Date)"
					+ " values (?, ?, ?)");
			myStmt.setString(1, Integer.toString(theDate.getId()));
			myStmt.setString(2, theDate.getDateType());
			myStmt.setString(3, theDate.getDate());
			myStmt.executeUpdate();
		}
		finally {
			close(null, myStmt, null);
		}
		
	}
	
	/* -------- SEARCH Methods -------- */
	public List<Contact> getAllContacts() throws Exception {
		
		List<Contact> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from CONTACT");
			while (myRs.next()) {
				Contact tempEmployee = convertRowToContact(myRs);
				list.add(tempEmployee);
			}
			return list;		
		}
		finally {
			close(null, myStmt, myRs);
		}
	}

	public List<Contact> searchContacts(String searchTerm) throws Exception {
		
		List<Contact> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			searchTerm = "'%" + searchTerm + "%'";
			myStmt = myConn.prepareStatement("select distinct CONTACT.Contact_id, Fname, Mname, Lname from CONTACT"
					+ " left join ADDRESS on CONTACT.Contact_id = ADDRESS.Contact_id"
					+ " left join PHONE on PHONE.Contact_id = ADDRESS.Contact_id"
					+ " left join DATE on DATE.Contact_id = PHONE.Contact_id"
					+ " where Fname like " + searchTerm + " or"
						 + " Mname like " + searchTerm + " or"
						 + " Lname like " + searchTerm + " or"
						 + " Fname like " + searchTerm + " or"
						 + " Address_type like " + searchTerm + " or"
						 + " Address like " + searchTerm + " or"
						 + " City like " + searchTerm + " or"
						 + " State like " + searchTerm + " or"
						 + " Zip like " + searchTerm + " or"
						 + " Phone_type like " + searchTerm + " or"
						 + " Area_code like " + searchTerm + " or"
						 + " Number like " + searchTerm + " or"
						 + " Date_type like " + searchTerm + " or"
						 + " Date like " + searchTerm
					);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Contact currentContact = convertRowToContact(myRs);
				list.add(currentContact);
			}
			return list;
		}
		finally {
			close(null, myStmt, myRs);
		}
	}
		
	public List<Address> searchAddress(int id) throws Exception {
		
		List<Address> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from ADDRESS where Contact_id=?");
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Address currentContact = convertRowToAddress(myRs);
				list.add(currentContact);
			}
			return list;
		}
		finally {
			close(null, myStmt, myRs);
		}
	}
	
	public List<Phone> searchPhone(int id) throws Exception {
		
		List<Phone> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from PHONE where Contact_id=?");
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Phone currentContact = convertRowToPhone(myRs);
				list.add(currentContact);
			}
			return list;
		}
		finally {
			close(null, myStmt, myRs);
		}
	}
	
	public List<Date> searchDate(int id) throws Exception {
		
		List<Date> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from DATE where Contact_id=?");
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Date currentContact = convertRowToDate(myRs);
				list.add(currentContact);
			}
			return list;
		}
		finally {
			close(null, myStmt, myRs);
		}
	}
	
	/* -------- Converting Methods -------- */
	private Date convertRowToDate(ResultSet myRs) throws SQLException {
		
		/*
		 *  Get contacts field from the query response
		 */
		
		int id = myRs.getInt("contact_id");
		String type = myRs.getString("Date_type");
		if(type == null)
			type = "";
		String date = myRs.getString("Date");
		if(date == null)
			date = "";
		Date temp = new Date(id, type, date);
		return temp;
		
	}
	
	private Phone convertRowToPhone(ResultSet myRs) throws SQLException {
		
		/*
		 *  Get contacts field from the query response
		 */
		
		int id = myRs.getInt("contact_id");
		String type = myRs.getString("Phone_type");
		if(type == null)
			type = "";
		String code = myRs.getString("Area_code");
		if(code == null || code.equals("0"))
			code = "";
		String num = myRs.getString("Number");
		if(num == null)
			num = "";
		Phone temp = new Phone(id, type, code, num);
		return temp;
		
	}
	
	private Address convertRowToAddress(ResultSet myRs) throws SQLException {
		
		/*
		 *  Get contacts field from the query response
		 */
		
		int id = myRs.getInt("contact_id");
		String type = myRs.getString("Address_type");
		if(type == null)
			type = "";
		String addr = myRs.getString("Address");
		if(addr == null)
			addr = "";
		String city = myRs.getString("City");
		if(city == null)
			city = "";
		String state = myRs.getString("State");
		if(state == null)
			state = "";
		String zip = myRs.getString("Zip");
		if(zip == null)
			zip = "";
		Address tempAddr = new Address(id, type, addr, city, state, zip);
		return tempAddr;
		
	}
	
	private Contact convertRowToContact(ResultSet myRs) throws SQLException {
		
		/*
		 *  Get contacts field from the query response
		 */
		
		int id = myRs.getInt("contact_id");
		String lastName = myRs.getString("Lname");
		String middleName = myRs.getString("Mname");
		if(middleName == null)
			middleName = "";
		String firstName = myRs.getString("Fname");
		Contact tempContact = new Contact(id, lastName, middleName, firstName);
		return tempContact;
		
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

}
