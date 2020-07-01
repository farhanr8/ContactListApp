package contactlist;

import java.util.*;
import javax.swing.table.AbstractTableModel;


public class ContactDateTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int TYPE_COL = 0;
	private static final int DATE_COL = 1;


	private String[] columnNames = {"Type", "Date"};
	private List<Date> dates;

	public ContactDateTableModel(List<Date> theDates) {
		dates = theDates;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return dates.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Date temp = dates.get(row);

		switch (col) {
		case OBJECT_COL:
			return temp;
		case TYPE_COL:
			return temp.getDateType();
		case DATE_COL:
			return temp.getDate();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}


