package contactlist.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import contactlist.Address;
import contactlist.Contact;
import contactlist.ContactDAO;
import contactlist.Date;
import contactlist.Phone;

import java.util.List;
import java.awt.Font;


public class AddContactWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SearchWindow searchWindow;	
	private ContactDAO contactDAO;
	private Contact previousContact = null;
	private Address previousAddr = null;
	private Phone previousPhone = null;
	private Date previousDate = null;
	private boolean updateMode = false;

	private JTextField FNameTextField;
	private JTextField MNameTextField;
	private JTextField LNameTextField;
	private JTextField aTypeTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField zipTextField;
	private JTextField numTextField;
	private JTextField aCodeTextField;
	private JTextField pTypeTextField;
	private JTextField dateTextField;
	private JTextField dTypeTextField;
	

	public AddContactWindow(SearchWindow theSearchWindow, ContactDAO theContactDAO,
			Contact thePreviousContact, boolean theUpdateMode) {
		this();
		searchWindow = theSearchWindow;
		contactDAO = theContactDAO;
		previousContact = thePreviousContact;
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Contact");
			populateGui(previousContact);
		}
	}

	public AddContactWindow() {
		setTitle("Add Contact");
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// ------------------- NAME ------------------- //

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(9, 15, 68, 16);
		contentPanel.add(lblNewLabel);

		FNameTextField = new JTextField();
		FNameTextField.setBounds(95, 10, 350, 26);
		contentPanel.add(FNameTextField);
		FNameTextField.setColumns(10);

		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(9, 46, 82, 16);
		contentPanel.add(lblMiddleName);

		MNameTextField = new JTextField();
		MNameTextField.setBounds(95, 41, 350, 26);
		contentPanel.add(MNameTextField);
		MNameTextField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(9, 77, 66, 16);
		contentPanel.add(lblLastName);

		LNameTextField = new JTextField();
		LNameTextField.setBounds(95, 72, 350, 26);
		contentPanel.add(LNameTextField);
		LNameTextField.setColumns(10);

		// ------------------- ADDRESS ------------------- //

		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAddress.setBounds(200, 110, 61, 16);
		contentPanel.add(lblAddress);


		JLabel lblNewLabel_1 = new JLabel("Address Type");
		lblNewLabel_1.setBounds(174, 187, 101, 16);
		contentPanel.add(lblNewLabel_1);


		aTypeTextField = new JTextField();
		aTypeTextField.setColumns(10);
		aTypeTextField.setBounds(271, 182, 130, 26);
		contentPanel.add(aTypeTextField);

		JLabel lblStreetAddress = new JLabel("Street");
		lblStreetAddress.setBounds(9, 131, 36, 16);
		contentPanel.add(lblStreetAddress);

		streetTextField = new JTextField();
		streetTextField.setColumns(10);
		streetTextField.setBounds(49, 126, 395, 26);
		contentPanel.add(streetTextField);

		JLabel lblNewLabel_2 = new JLabel("City");
		lblNewLabel_2.setBounds(9, 159, 61, 16);
		contentPanel.add(lblNewLabel_2);

		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		cityTextField.setBounds(49, 154, 113, 26);
		contentPanel.add(cityTextField);

		JLabel lblState = new JLabel("State");
		lblState.setBounds(174, 159, 61, 16);
		contentPanel.add(lblState);

		stateTextField = new JTextField();
		stateTextField.setColumns(10);
		stateTextField.setBounds(212, 154, 113, 26);
		contentPanel.add(stateTextField);


		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(9, 187, 61, 16);
		contentPanel.add(lblZip);


		zipTextField = new JTextField();
		zipTextField.setColumns(10);
		zipTextField.setBounds(49, 182, 113, 26);
		contentPanel.add(zipTextField);

		// ------------------- PHONE ------------------- //

		JLabel lblPhone = new JLabel("PHONE");
		lblPhone.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPhone.setBounds(200, 224, 61, 16);
		contentPanel.add(lblPhone);


		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(9, 250, 50, 16);
		contentPanel.add(lblNumber);


		numTextField = new JTextField();
		numTextField.setColumns(10);
		numTextField.setBounds(75, 245, 369, 26);
		contentPanel.add(numTextField);


		JLabel lblAreaCode = new JLabel("Area Code");
		lblAreaCode.setBounds(9, 278, 68, 16);
		contentPanel.add(lblAreaCode);


		aCodeTextField = new JTextField();
		aCodeTextField.setColumns(10);
		aCodeTextField.setBounds(75, 273, 113, 26);
		contentPanel.add(aCodeTextField);


		JLabel lblPhoneType = new JLabel("Phone Type");
		lblPhoneType.setBounds(200, 278, 82, 16);
		contentPanel.add(lblPhoneType);


		pTypeTextField = new JTextField();
		pTypeTextField.setColumns(10);
		pTypeTextField.setBounds(284, 273, 130, 26);
		contentPanel.add(pTypeTextField);

		// ------------------- DATE ------------------- //

		JLabel lblDate = new JLabel("DATE");
		lblDate.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDate.setBounds(200, 313, 61, 16);
		contentPanel.add(lblDate);


		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setBounds(9, 341, 50, 16);
		contentPanel.add(lblDate_1);


		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		dateTextField.setBounds(49, 336, 139, 26);
		contentPanel.add(dateTextField);


		JLabel lblDateType = new JLabel("Date Type");
		lblDateType.setBounds(200, 341, 82, 16);
		contentPanel.add(lblDateType);


		dTypeTextField = new JTextField();
		dTypeTextField.setColumns(10);
		dTypeTextField.setBounds(284, 336, 130, 26);
		contentPanel.add(dTypeTextField);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		// SAVE Button
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveContact();
			}

		});
		saveButton.setActionCommand("OK");
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
		
		// CANCEL Button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		

	}

	protected void saveContact() {

		String firstName = FNameTextField.getText();
		String middleName = MNameTextField.getText();
		String lastName = LNameTextField.getText();
		
		String addrType = aTypeTextField.getText();
		String addr = streetTextField.getText();
		String city = cityTextField.getText();
		String state = stateTextField.getText();
		String zip = zipTextField.getText();
		String number = numTextField.getText();
		String areaCode = aCodeTextField.getText();
		String phoneType = pTypeTextField.getText();
		String date = dateTextField.getText();
		String dateType = dTypeTextField.getText();
		
		boolean oldAddrType = false;
		boolean oldPhoneType = false;
		boolean oldDateType = false;

		Contact tempContact = null;
		Address tempAddr = null;
		Phone tempPhone = null;
		Date tempDate = null;

		// Get the fields for contact
		if (updateMode) {
			tempContact = previousContact;
			tempContact.setLastName(lastName);
			tempContact.setMiddleName(middleName);
			tempContact.setFirstName(firstName);
			
			// UPDATE address if type exists, else ADD new address
			try {
				List<Address> addrList = null;
				addrList = contactDAO.searchAddress(tempContact.getId());
				for(Address address : addrList) {
					
					if(addrType.equals(address.getAddrType())) {
						tempAddr = address;
						tempAddr.setAddr(addr);
						tempAddr.setCity(city);
						tempAddr.setState(state);
						tempAddr.setZip(zip);
						oldAddrType = true;
						break;
					}
					
				}
			
				if(!oldAddrType) {
					tempAddr = new Address(tempContact.getId(), addrType, addr, city, state, zip);
					contactDAO.addAddress(tempAddr);
				}
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(searchWindow,
						"Error saving contact's address: "
								+ exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
			}
			
			// UPDATE phone if type exists, else ADD new phone
			try {
				List<Phone> phoneList = null;
				phoneList = contactDAO.searchPhone(tempContact.getId());
				for(Phone phone : phoneList) {
					
					if(phoneType.equals(phone.getPhoneType())) {
						tempPhone = phone;
						tempPhone.setAreaCode(areaCode);
						tempPhone.setNumber(number);
						oldPhoneType = true;
						break;
					}
					
				}
			
				if(!oldPhoneType) {
					tempPhone = new Phone(tempContact.getId(), phoneType, areaCode, number);
					contactDAO.addPhone(tempPhone);
				}
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(searchWindow,
						"Error saving contact's address: "
								+ exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
			}

			
			// UPDATE date if type exists, else ADD new date
			try {
				List<Date> dateList = null;
				dateList = contactDAO.searchDate(tempContact.getId());
				for(Date d : dateList) {
					
					if(dateType.equals(d.getDateType())) {
						tempDate = d;
						tempDate.setDate(date);
						oldDateType = true;
						break;
					}
					
				}
			
				if(!oldDateType) {
					tempDate = new Date(tempContact.getId(), dateType, date);
					contactDAO.addDate(tempDate);
				}
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(searchWindow,
						"Error saving contact's address: "
								+ exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
			}
			
			
		} else {
			// Not update mode
			tempContact = new Contact(lastName,middleName,firstName);
		}

		// Use the database interface
		try {
			if (updateMode) {
				contactDAO.updateContact(tempContact);
				if(oldAddrType)
					contactDAO.updateAddress(tempAddr);
				if(oldPhoneType)
					contactDAO.updatePhone(tempPhone);
				if(oldDateType)
					contactDAO.updateDate(tempDate);
			}
			else {
				int id = contactDAO.addContact(tempContact);	

				tempAddr = new Address(id, addrType, addr, city, state, zip);
				tempPhone = new Phone(id, phoneType, areaCode, number);
				tempDate = new Date(id, dateType, date);
				
				contactDAO.addAddress(tempAddr);
				contactDAO.addPhone(tempPhone);
				contactDAO.addDate(tempDate);
				
			}
			
			setVisible(false);
			dispose();
			searchWindow.refreshContactsView();
			
			// Success message
			if(updateMode) {
				JOptionPane.showMessageDialog(searchWindow,
						"Contact updated succesfully.",
						"Contact Updated",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(searchWindow,
						"Contact added succesfully.",
						"Contact Added",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(searchWindow,
					"Error saving Contact: "
							+ exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
		}

	}

	private void populateGui(Contact thePreviousContact) {

		FNameTextField.setText(thePreviousContact.getFirstName());
		MNameTextField.setText(thePreviousContact.getMiddleName());
		LNameTextField.setText(thePreviousContact.getLastName());
		
		try {
		
			// ADDRESS information
			List<Address> addrList = null;
			addrList = contactDAO.searchAddress(thePreviousContact.getId());
			if(!addrList.isEmpty()) {
				previousAddr = addrList.get(0);
				aTypeTextField.setText(previousAddr.getAddrType());
				streetTextField.setText(previousAddr.getAddr());
				cityTextField.setText(previousAddr.getCity());
				stateTextField.setText(previousAddr.getState());
				zipTextField.setText(previousAddr.getZip());
			}
			
			// PHONE information
			List<Phone> phoneList = null;
			phoneList = contactDAO.searchPhone(thePreviousContact.getId());
			if(!phoneList.isEmpty()) {
				previousPhone = phoneList.get(0);
				numTextField.setText(previousPhone.getNumber());
				aCodeTextField.setText(previousPhone.getAreaCode());
				pTypeTextField.setText(previousPhone.getPhoneType());
			}
			
			
			// DATE information
			List<Date> dateList = null;
			dateList = contactDAO.searchDate(thePreviousContact.getId());
			if(!dateList.isEmpty()) {
				previousDate = dateList.get(0);
				dTypeTextField.setText(previousDate.getDateType());
				dateTextField.setText(previousDate.getDate());
			}
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(AddContactWindow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}

	}


}
