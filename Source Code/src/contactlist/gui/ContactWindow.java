package contactlist.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import contactlist.Address;
import contactlist.Contact;
import contactlist.ContactAddrTableModel;
import contactlist.ContactDAO;
import contactlist.ContactDateTableModel;
import contactlist.ContactPhoneTableModel;
import contactlist.Date;
import contactlist.Phone;

import java.util.List;
import java.awt.Font;

public class ContactWindow extends JFrame {

	private JPanel contentPane;
	private JTable addrTable;
	private JTable phoneTable;
	private JTable dateTable;
	
	private SearchWindow searchWindow;	
	private ContactDAO contactDAO;
	private Contact currentContact = null;
	
	JLabel FNameLabel;
	JLabel MNameLabel;
	JLabel LNameLabel;
	
	JButton deleteButton;
	JButton updateButton;


	public ContactWindow(SearchWindow theSearchWindow, ContactDAO theContactDAO, 
			Contact theCurrentContact) {
		
		this();
		searchWindow = theSearchWindow;
		contactDAO = theContactDAO;
		currentContact = theCurrentContact;
		populateFields(searchWindow, currentContact);
		
	}
	
	public ContactWindow() {
		setTitle("Contact Information");
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		// ------------------- NAME ------------------- //
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblName.setBounds(6, 41, 61, 16);
		panel_2.add(lblName);
		
		
		JLabel lblFirst = new JLabel("First");
		lblFirst.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblFirst.setBounds(74, 6, 61, 16);
		panel_2.add(lblFirst);
		
		FNameLabel = new JLabel();
		FNameLabel.setBounds(74, 41, 109, 16);
		panel_2.add(FNameLabel);
		
		JLabel lblMiddle = new JLabel("Middle");
		lblMiddle.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblMiddle.setBounds(195, 6, 61, 16);
		panel_2.add(lblMiddle);
		
		MNameLabel = new JLabel();
		MNameLabel.setBounds(195, 41, 97, 16);
		panel_2.add(MNameLabel);
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblLast.setBounds(304, 6, 61, 16);
		panel_2.add(lblLast);
		
		LNameLabel = new JLabel();
		LNameLabel.setBounds(304, 41, 126, 16);
		panel_2.add(LNameLabel);
		
		// ------------------- ADDRESS ------------------- //
		
		JLabel lblAddressType = new JLabel("Address");
		lblAddressType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAddressType.setBounds(6, 83, 85, 16);
		panel_2.add(lblAddressType);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 111, 424, 69);
		panel_2.add(scrollPane_1);
		
		addrTable = new JTable();
		scrollPane_1.setViewportView(addrTable);
		
		// ------------------- PHONE ------------------- //
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPhone.setBounds(6, 192, 61, 16);
		panel_2.add(lblPhone);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 220, 424, 69);
		panel_2.add(scrollPane_2);
		
		phoneTable = new JTable();
		scrollPane_2.setViewportView(phoneTable);
				
		// ------------------- DATE ------------------- //
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDate.setBounds(6, 301, 61, 16);
		panel_2.add(lblDate);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 325, 424, 69);
		panel_2.add(scrollPane_3);
		
		dateTable = new JTable();
		scrollPane_3.setViewportView(dateTable);
		
		// DONE Button
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(313, 429, 117, 29);
		panel_2.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		// Initialize Update Button
		updateButton = new JButton("Update");
		updateButton.setBounds(0, 429, 117, 29);
		panel_2.add(updateButton);
		
		// Initialize Delete Button
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(118, 429, 117, 29);
		panel_2.add(deleteButton);
		
		

	}

	private void populateFields(SearchWindow theSearchWindow, Contact theCurrentContact) {

		// NAME
		FNameLabel.setText(theCurrentContact.getFirstName());
		MNameLabel.setText(theCurrentContact.getMiddleName());
		LNameLabel.setText(theCurrentContact.getLastName());
		
		try {
			// ADDRESS table setup
			List<Address> addrList = null;
			addrList = contactDAO.searchAddress(theCurrentContact.getId());
			ContactAddrTableModel addrModel = new ContactAddrTableModel(addrList);
			addrTable.setModel(addrModel);
			
			// PHONE table setup
			List<Phone> phoneList = null;
			phoneList = contactDAO.searchPhone(theCurrentContact.getId());
			ContactPhoneTableModel phoneModel = new ContactPhoneTableModel(phoneList);
			phoneTable.setModel(phoneModel);
			
			// DATE table setup
			List<Date> dateList = null;
			dateList = contactDAO.searchDate(theCurrentContact.getId());
			ContactDateTableModel model = new ContactDateTableModel(dateList);
			dateTable.setModel(model);
			
			// DELETE Button
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
			
						// Prompt user
						int response = JOptionPane.showConfirmDialog(
								ContactWindow.this, "Delete this Contact?", "Confirm", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response != JOptionPane.YES_OPTION) {
							return;
						}

						// Delete the contact
						contactDAO.deleteContact(theCurrentContact.getId());

						// Refresh GUI
						theSearchWindow.refreshContactsView();

						// Success message
						JOptionPane.showMessageDialog(ContactWindow.this,
								"Contact deleted succesfully.", "Contact Deleted",
								JOptionPane.INFORMATION_MESSAGE);
						
						// Close window
						setVisible(false);
						dispose();

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(ContactWindow.this,
								"Error deleting contact: " + exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			// UPDATE Button
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					// Create modify entry form
					AddContactWindow updateWindow = new AddContactWindow(theSearchWindow, contactDAO, 
							theCurrentContact, true);
					updateWindow.setVisible(true);
					
					// Close window
					setVisible(false);
					dispose();

				}
			});
			
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(ContactWindow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
	}

}
