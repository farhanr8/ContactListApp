package contactlist;

import java.util.*;
import javax.swing.table.AbstractTableModel;


public class ContactPhoneTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int TYPE_COL = 0;
	private static final int CODE_COL = 1;
	private static final int NUM_COL = 2;


	private String[] columnNames = {"Type", "Area Code", "Number"};
	private List<Phone> phones;

	public ContactPhoneTableModel(List<Phone> thePhones) {
		phones = thePhones;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return phones.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Phone temp = phones.get(row);

		switch (col) {
		case OBJECT_COL:
			return temp;
		case TYPE_COL:
			return temp.getPhoneType();
		case CODE_COL:
			return temp.getAreaCode();
		case NUM_COL:
			return temp.getNumber();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}


