package delicious.pos.ui.components.extensions;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class UITable extends JTable {

	private DefaultTableModel model;
	
	public UITable() {
		super();
		init();
	}
	
	public UITable(DefaultTableModel model) {
		super(model);
		init();
	}
	
	private void init() {
		model = (DefaultTableModel)(getModel());
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		setPreferredScrollableViewportSize(getParent().getSize());
	}
	
	public void insertRow() {
		model.insertRow(model.getRowCount(), new Object[model.getColumnCount()]);
	}
	
	public void removeRow() {
		if(getSelectedRow() > -1)
			model.removeRow(getSelectedRow());
	}
	
	public void removeRows(int rows) {
		int rowCount = model.getRowCount();
		for(int index = rowCount-1; index >= rows; index--)
			model.removeRow(index);
	}
}
