package contactlist.gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import contactlist.Contact;
import contactlist.ContactDAO;
import contactlist.ContactTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;


public class SearchWindow extends JFrame{

	public JFrame frmContactSearch;
	private JTextField searchTextField;
	private JTable table;
	private ContactDAO contactDAO;

	
	public SearchWindow() {

		//  Connect to database
		try {
			contactDAO = new ContactDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		// Start GUI
		initialize();
	}

	private void initialize() {

		frmContactSearch = new JFrame();
		frmContactSearch.setTitle("Contact Search");
		frmContactSearch.setBounds(100, 100, 450, 300);
		frmContactSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmContactSearch.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblEnterSearchTerm = new JLabel("Search Entry");
		panel.add(lblEnterSearchTerm);

		searchTextField = new JTextField();
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		// SEARCH Button 
		JButton btnSearch = new JButton("Search");
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Get data for table
					String searchTerm = searchTextField.getText();
					List<Contact> contacts = new ArrayList<>();
					HashSet<Contact> set = new HashSet<>();
					boolean firstTerm = true;
					
					if (searchTerm != null && searchTerm.trim().length() > 0) {
						String[] arr = searchTerm.split(" ");
						for(String s : arr) {
							if(firstTerm) {
								contacts= contactDAO.searchContacts(s);
								firstTerm = false;
							}
							else{
								// If multiple keywords
								List<Contact> temp = contactDAO.searchContacts(s);								
								Iterator<Contact> itr = contacts.iterator(); 
								while (itr.hasNext()) { 
									Contact c = itr.next(); 
									if (!temp.contains(c)) { 
										itr.remove(); 
									} 	
								}

							}
						}
					} else {
						contacts = contactDAO.getAllContacts();
					}

					// Create the model and update the "table"
					ContactTableModel model = new ContactTableModel(contacts);
					table.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(SearchWindow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}

			}
		});

	
		JScrollPane scrollPane = new JScrollPane();
		frmContactSearch.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmContactSearch.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("122px"),
				FormSpecs.RELATED_GAP_COLSPEC,},
				new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("29px"),
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,}));

		// ADD Button	
		JButton btnAdd = new JButton("Add Contact");
		panel_2.add(btnAdd, "2, 4, left, top");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create add entry form
				AddContactWindow addWindow = new AddContactWindow(SearchWindow.this, contactDAO, null, false);
				addWindow.setVisible(true);
			}
		});

		// VIEW Button	
		JButton btnNewButton = new JButton("View Contact");
		panel_2.add(btnNewButton, "2, 6, left, top");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check row is selected
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(SearchWindow.this, "You must select a contact", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}

				// Get current contact
				Contact tempContact = (Contact) table.getValueAt(row, ContactTableModel.OBJECT_COL);

				// Create contact window
				ContactWindow contactWindow = new ContactWindow(SearchWindow.this, contactDAO, tempContact);
				contactWindow.setVisible(true);
			}
		});
		
		// UPDATE Button
		JButton btnUpdateContact = new JButton("Update Contact");
		panel_2.add(btnUpdateContact, "2, 8, left, top");
		btnUpdateContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check row is selected
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(SearchWindow.this, "You must select a contact", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}

				// Get current contact
				Contact tempContact = (Contact) table.getValueAt(row, ContactTableModel.OBJECT_COL);

				// Create modify entry form
				AddContactWindow updateWindow = new AddContactWindow(SearchWindow.this, contactDAO, 
																		tempContact, true);
				updateWindow.setVisible(true);

			}
		});
		
		// Empty place holders
		JLabel lblPh = new JLabel(" ");
		panel_2.add(lblPh, "2, 10");
		
		JLabel lblPh_1 = new JLabel(" ");
		panel_2.add(lblPh_1, "2, 12");

		// DELETE Button
		JButton btnDeleteContact = new JButton("Delete Contact");
		panel_2.add(btnDeleteContact, "2, 14, left, top");
		btnDeleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Check row is selected
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(SearchWindow.this, 
								"You must select a contact", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// Prompt user
					int response = JOptionPane.showConfirmDialog(
							SearchWindow.this, "Delete this Contact?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// Get the selected contact
					Contact tempContact = (Contact) table.getValueAt(row, ContactTableModel.OBJECT_COL);

					// Delete the contact
					contactDAO.deleteContact(tempContact.getId());

					// Refresh GUI
					refreshContactsView();

					// Success message
					JOptionPane.showMessageDialog(SearchWindow.this,
							"Contact deleted succesfully.", "Contact Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(SearchWindow.this,
							"Error deleting contact: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	public void refreshContactsView() {

		try {
			List<Contact> contacts = contactDAO.getAllContacts();
			ContactTableModel model = new ContactTableModel(contacts);
			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}






