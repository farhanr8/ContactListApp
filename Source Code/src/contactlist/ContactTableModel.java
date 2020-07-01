package contactlist;

import java.util.*;
import javax.swing.table.AbstractTableModel;

public class ContactTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int MIDDLE_NAME_COL = 1;
	private static final int FIRST_NAME_COL = 2;

	private String[] columnNames = { "Last Name", "Middle Name", "First Name"};
	private List<Contact> contacts;

	public ContactTableModel(List<Contact> theContacts) {
		contacts = theContacts;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return contacts.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Contact temp = contacts.get(row);

		switch (col) {
		case OBJECT_COL:
			return temp;
		case LAST_NAME_COL:
			return temp.getLastName();
		case MIDDLE_NAME_COL:
			return temp.getMiddleName();
		case FIRST_NAME_COL:
			return temp.getFirstName();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}


