package delicious.pos.ui.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import delicious.pos.business.logic.dao.SizeDAO;
import delicious.pos.business.logic.dao.gen.CustomerDAO;
import delicious.pos.business.logic.dao.gen.EmployeeDAO;
import delicious.pos.business.logic.dao.gen.ItemDAO;
import delicious.pos.business.logic.view.gen.EmployeeView;
import delicious.pos.ui.components.extensions.UIButton;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.extensions.UIScrollPane;
import delicious.pos.ui.components.extensions.UITable;

public class AdminScreen extends UIPanel {

	public AdminScreen() {
		super();
		init();
	}

	private JTabbedPane tabbedPane;

	private void init() {

		UIPanel plusMinusPanel = plusMinusPanel();
		UIPanel btnsPanel = btnsPanel();
		UIPanel tabsPanel = new UIPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Employees", null, employees(), "Employees");
		tabbedPane.addTab("Items", null, items(), "Items");
		tabbedPane.addTab("Customers", null, customers(), "Customers");
		tabbedPane.addTab("Sizes", null, sizes(), "Sizes");
		
		tabsPanel.add(tabbedPane);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnsPanel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabsPanel, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(plusMinusPanel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabsPanel, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
						.addComponent(plusMinusPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
		);

		setLayout(groupLayout);
	}

	private int employeeRows;
	private UITable employees;
	private DefaultTableModel employeesModel;
	private List<Integer> touchedRows;
	private UIScrollPane employees() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		touchedRows = new Vector<Integer>();
		employeesModel = new DefaultTableModel(employeeDAO.getAllAsArray(), 
						employeeDAO.getColumnNames());
		employees = new UITable(employeesModel);
		
		ListSelectionModel rowSelectionModel = employees.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				touchedRows.add(employees.getSelectedRow());
			}

		});
		
		employeeRows = employeeDAO.getAllAsArray().length;
		return new UIScrollPane(employees);
	}

	private UITable items;
	private DefaultTableModel itemsModel;
	private UIScrollPane items() {
		ItemDAO itemDAO = new ItemDAO();
		itemsModel = new DefaultTableModel(itemDAO.getAllAsArray(), 
					itemDAO.getColumnNames());
		items = new UITable(itemsModel);

		return  new UIScrollPane(items);
	}
	
	private UITable customers;
	private DefaultTableModel customersModel;
	private UIScrollPane customers() {
		CustomerDAO customerDAO = new CustomerDAO();
		customersModel = new DefaultTableModel(customerDAO.getAllAsArray(), 
						customerDAO.getColumnNames());
		customers = new UITable(customersModel);

		return new UIScrollPane(customers);
	}
	
	private UITable sizes;
	private DefaultTableModel sizesModel;
	private UIScrollPane sizes() {
		SizeDAO sizeDAO = new SizeDAO();
		sizesModel = new DefaultTableModel(sizeDAO.getAllAsArray(), 
					sizeDAO.getColumnNames());
		sizes = new UITable(sizesModel);
		sizes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		return new UIScrollPane(sizes);
	}
	
	private UIPanel plusMinusPanel() {
		UIPanel plusMinusPanel = new UIPanel();

		plusMinusPanel.add(plusButton());
		plusMinusPanel.add(minusButton());

		return plusMinusPanel;
	}

	private UIPanel btnsPanel() {
		UIPanel btnsPanel = new UIPanel();
		btnsPanel.add(doneButton());
		btnsPanel.add(saveButton());
		btnsPanel.add(cancelButton());

		return btnsPanel;
	}

	private UIButton plusBtn;
	private UIButton plusButton() {
		plusBtn = new UIButton("+");
		plusBtn.setBounds(7, 172, 44, 25);
		plusBtn.setBackground(Color.GREEN);
		plusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0)
					employees.insertRow();
				else if (tabbedPane.getSelectedIndex() == 1)
					items.insertRow();
				else if (tabbedPane.getSelectedIndex() == 2)
					customers.insertRow();
				else if (tabbedPane.getSelectedIndex() == 3)
					sizes.insertRow();
			}
		});

		return plusBtn;
	}

	private UIButton minusButton() {
		UIButton minusBtn = new UIButton("-");
		minusBtn.setBounds(12, 220, 39, 25);
		minusBtn.setBackground(Color.RED);
		minusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0)
					employees.removeRow();
				else if (tabbedPane.getSelectedIndex() == 1)
					items.removeRow();
				else if (tabbedPane.getSelectedIndex() == 2)
					customers.removeRow();
				else if (tabbedPane.getSelectedIndex() == 3)
					sizes.removeRow();
			}
		});

		return minusBtn;
	}

	private UIButton saveButton() {
		UIButton saveBtn = new UIButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		return saveBtn;
	}
	
	//TODO must handle NumberFormatException
	private void save() {
		if (tabbedPane.getSelectedIndex() == 0) {
			persistEmployee();
			updateEmployee();
		}
	}
	
	private void updateEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		for(int row : touchedRows) {
			EmployeeView employee = new EmployeeView();
			employee.setUserName((String)employeesModel.getValueAt(row, 0));
			employee.setSalary(Float.parseFloat((String)employeesModel.getValueAt(row, 1)));
			employee.setPhone((String)employeesModel.getValueAt(row, 2));
			employee.setPosition((String)employeesModel.getValueAt(row, 3));
			employeeDAO.update(employee);
		}
	}
	
	private void persistEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeView employee = new EmployeeView();
		
		for(int row = employeeRows; row < employeesModel.getRowCount(); row++) {
			for(int column = 0; column < employeesModel.getColumnCount(); column++) {
				
				String columnName = employeesModel.getColumnName(column);
				Object value = employeesModel.getValueAt(row, column);
				
				if("userName".equals(columnName))
					employee.setUserName((String)value);
				if("salary".equals(columnName))
					employee.setSalary(Float.parseFloat((String)value));
				if("phone".equals(columnName))
					employee.setPhone((String)value);
				if("position".equals(columnName))
					employee.setPosition((String)value);
			}
			employeeDAO.persist(employee);
			employeeRows = employeeDAO.getAllAsArray().length;
		}
	}

	private UIButton cancelButton() {
		UIButton cancelBtn = new UIButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tabbedPane.getSelectedIndex() == 0)
					employees.removeRows(employeeRows);
			}
		});
		return cancelBtn;
	}
	
	private void removeEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeView employee = new EmployeeView();
		
		int row = employees.getSelectedRow();
		
		for(int column = 0; column < employeesModel.getColumnCount(); column++) {
			
			String columnName = employeesModel.getColumnName(column);
			Object value = employeesModel.getValueAt(row, column);
			
			if("userName".equals(columnName))
				employee.setUserName((String)value);
			if("salary".equals(columnName))
				employee.setSalary(((Float)value).floatValue());
			if("phone".equals(columnName))
				employee.setPhone((String)value);
			if("position".equals(columnName))
				employee.setPosition((String)value);
		}
		
		employeeDAO.remove(employee);
		employees.removeRow();
		employeeRows = employeeDAO.getAllAsArray().length;
	}

	private UIButton doneButton() {
		UIButton doneBtn = new UIButton("Delete");
		doneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tabbedPane.getSelectedIndex() == 0)
					removeEmployee();
			}
		});
		return doneBtn;
	}
}
