package contactlist;

import java.util.*;
import javax.swing.table.AbstractTableModel;


public class ContactAddrTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int TYPE_COL = 0;
	private static final int ADDR_COL = 1;
	private static final int CITY_COL = 2;
	private static final int STATE_COL = 3;
	private static final int ZIP_COL = 4;


	private String[] columnNames = {"Type", "Address", "City", "State", "ZIP"};
	private List<Address> addresses;

	public ContactAddrTableModel(List<Address> theAddresses) {
		addresses = theAddresses;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return addresses.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Address temp = addresses.get(row);

		switch (col) {
		case OBJECT_COL:
			return temp;
		case TYPE_COL:
			return temp.getAddrType();
		case ADDR_COL:
			return temp.getAddr();
		case CITY_COL:
			return temp.getCity();
		case STATE_COL:
			return temp.getState();
		case ZIP_COL:
			return temp.getZip();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}


