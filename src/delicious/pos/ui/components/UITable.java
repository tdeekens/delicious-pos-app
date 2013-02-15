package delicious.pos.ui.components;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class UITable extends JTable {

	public UITable() {
		super();
		init();
	}
	
	public UITable(DefaultTableModel model) {
		super(model);
		init();
	}
	
	private void init() {
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		setPreferredScrollableViewportSize(getParent().getSize());
	}
	
	public void insertRow() {
		DefaultTableModel model = (DefaultTableModel)(getModel());
		model.insertRow(model.getRowCount(), new Object[model.getColumnCount()]);
	}
	
	public void removeRow() {
		DefaultTableModel model = (DefaultTableModel)(getModel());
		if(getSelectedRow() > -1)
			model.removeRow(getSelectedRow());
	}
}
