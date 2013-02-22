package delicious.pos.ui.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
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
import delicious.pos.business.logic.view.SizeView;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.business.logic.view.gen.EmployeeView;
import delicious.pos.business.logic.view.gen.ItemView;
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
	private List<Integer> touchedEmployeeRows;
	private UIScrollPane employees() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		touchedEmployeeRows = new Vector<Integer>();
		employeesModel = new DefaultTableModel(employeeDAO.getAllAsArray(), 
						employeeDAO.getColumnNames());
		employees = new UITable(employeesModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if(colIndex == 0)
					return false;
				return true;
		    }
		};
		
		employeeRows = employeeDAO.getAllAsArray().length;
		
		ListSelectionModel rowSelectionModel = employees.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;

				if(employees.getSelectedRow() < employeeRows) 
					touchedEmployeeRows.add(employees.getSelectedRow());
			}
		});
		
		return new UIScrollPane(employees);
	}

	private int itemRows;
	private UITable items;
	private DefaultTableModel itemsModel;
	private List<Integer> touchedItemRows;
	private UIScrollPane items() {
		ItemDAO itemDAO = new ItemDAO();
		touchedItemRows = new Vector<Integer>();
		itemsModel = new DefaultTableModel(itemDAO.getAllAsArray(), 
					itemDAO.getColumnNames());
		items = new UITable(itemsModel);
		
		itemRows = itemDAO.getAllAsArray().length;
		
		ListSelectionModel rowSelectionModel = items.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(itemRows < items.getSelectedRow())
					touchedItemRows.add(items.getSelectedRow());
			}
		});

		return  new UIScrollPane(items);
	}
	
	private int customerRows;
	private UITable customers;
	private DefaultTableModel customersModel;
	private List<Integer> touchedCustomerRows;
	private UIScrollPane customers() {
		CustomerDAO customerDAO = new CustomerDAO();
		touchedCustomerRows = new Vector<Integer>();
		customersModel = new DefaultTableModel(customerDAO.getAllAsArray(), 
						customerDAO.getColumnNames());
		customers = new UITable(customersModel);
		
		customerRows = customerDAO.getAllAsArray().length;
		
		ListSelectionModel rowSelectionModel = customers.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(customerRows <  customers.getSelectedRow())
					touchedCustomerRows.add(customers.getSelectedRow());
			}
		});
		
		return new UIScrollPane(customers);
	}
	
	private int sizeRows;
	private UITable sizes;
	private DefaultTableModel sizesModel;
	private List<Integer> touchedSizeRows;
	private UIScrollPane sizes() {
		SizeDAO sizeDAO = new SizeDAO();
		touchedSizeRows = new Vector<Integer>();
		sizesModel = new DefaultTableModel(sizeDAO.getAllAsArray(), 
					sizeDAO.getColumnNames());
		sizes = new UITable(sizesModel);
		sizes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		sizeRows = sizeDAO.getAllAsArray().length;
		
		ListSelectionModel rowSelectionModel = sizes.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(sizeRows < sizes.getSelectedRow())
					touchedSizeRows.add(sizes.getSelectedRow());
			}
		});
		
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

	private void save() {
		if (tabbedPane.getSelectedIndex() == 0) {
			persistEmployee();
			updateEmployee();
		}
		else if (tabbedPane.getSelectedIndex() == 1) {
			persistItem();
			updateItem();
		}
		else if (tabbedPane.getSelectedIndex() == 2) {
			persistCustomer();
			updateCustomer();
		}
		else if (tabbedPane.getSelectedIndex() == 3) {
			persistSize();
			updateSize();
		}
	}
	
	private void updateEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		for(int row : touchedEmployeeRows) {

			EmployeeView employee = new EmployeeView();
			employee.setUserName((String)employeesModel.getValueAt(row, 0));
			employee.setSalary(Float.valueOf(employeesModel.getValueAt(row, 1).toString()));
			employee.setPhone((String)employeesModel.getValueAt(row, 2));
			employee.setPosition((String)employeesModel.getValueAt(row, 3));
			employeeDAO.update(employee);
		}
	}
	
	private void updateCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		for(int row : touchedCustomerRows) {
			CustomerView customer = new CustomerView();
			customer.setFirstName((String)customersModel.getValueAt(row, 0));
			customer.setLastName((String)customersModel.getValueAt(row, 1));
			customer.setStreet((String)customersModel.getValueAt(row, 2));
			customer.setZIP((String)customersModel.getValueAt(row, 3));
			customer.setCity((String)customersModel.getValueAt(row, 4));
			customer.setPhone((String)customersModel.getValueAt(row, 5));
			customerDAO.update(customer);
		}
	}
	
	private void updateItem() {
		ItemDAO itemDAO = new ItemDAO();
		for(int row : touchedItemRows) {
			ItemView item = new ItemView();
			item.setName((String)itemsModel.getValueAt(row, 0));
			item.setDescription((String)itemsModel.getValueAt(row, 1));
			itemDAO.update(item);
		}
	}
	
	private void updateSize() {
		SizeDAO sizeDAO = new SizeDAO();
		for(int row : touchedSizeRows) {
			SizeView size = new SizeView();
			size.setValue((String)itemsModel.getValueAt(row, 0));
			sizeDAO.update(size);
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
				else if("salary".equals(columnName)) {
					if(!parseSalary((String)value))
						return; 
					employee.setSalary(Float.parseFloat((String)value));
				}
				else if("phone".equals(columnName))
					employee.setPhone((String)value);
				else if("position".equals(columnName))
					employee.setPosition((String)value);
			}
			employeeDAO.insert(employee);
			employeeRows = employeeDAO.getAllAsArray().length;
		}
	}
	
	private void persistCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerView customer = new CustomerView();
		
		for(int row = customerRows; row < customersModel.getRowCount(); row++) {
			for(int column = 0; column < customersModel.getColumnCount(); column++) {
				
				String columnName = customersModel.getColumnName(column);
				Object value = customersModel.getValueAt(row, column);
				
				if("firstName".equals(columnName))
					customer.setFirstName((String)value);
				else if("lastName".equals(columnName))
					customer.setLastName((String)value);
				else if("street".equals(columnName))
					customer.setStreet((String)value);
				else if("zip".equals(columnName))
					customer.setZIP((String)value);
				else if("city".equals(columnName))
					customer.setCity((String)value);
				else if("phone".equals(columnName))
					customer.setPhone((String)value);
			}
			customerDAO.persist(customer);
			customerRows = customerDAO.getAllAsArray().length;
		}
	}
	
	private void persistItem() {
		ItemDAO itemDAO = new ItemDAO();
		ItemView item = new ItemView();
		
		for(int row = itemRows; row < itemsModel.getRowCount(); row++) {
			for(int column = 0; column < itemsModel.getColumnCount(); column++) {
				
				String columnName = itemsModel.getColumnName(column);
				Object value = itemsModel.getValueAt(row, column);
				
				if("name".equals(columnName))
					item.setName((String)value);
				else if("description".equals(columnName))
					item.setDescription((String)value);
			}
			itemDAO.persist(item);
			itemRows = itemDAO.getAllAsArray().length;
		}
	}
	
	private void persistSize() {
		SizeDAO sizeDAO = new SizeDAO();
		SizeView size = new SizeView();
		
		for(int row = sizeRows; row < sizesModel.getRowCount(); row++) {
			for(int column = 0; column < sizesModel.getColumnCount(); column++) {
				
				String columnName = sizesModel.getColumnName(column);
				Object value = sizesModel.getValueAt(row, column);
				
				if("value".equals(columnName))
					size.setValue((String)value);
			}
			sizeDAO.persist(size);
			sizeRows = sizeDAO.getAllAsArray().length;
		}
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
			else if("salary".equals(columnName)) {
				if(!parseSalary(String.valueOf(value)))
					return;
				employee.setSalary(((Float)value).floatValue());
			}
			else if("phone".equals(columnName))
				employee.setPhone((String)value);
			else if("position".equals(columnName))
				employee.setPosition((String)value);
		}
		
		employeeDAO.remove(employee);
		employees.removeRow();
		employeeRows = employeeDAO.getAllAsArray().length;
	}
	
	private void removeCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerView customer = new CustomerView();
		
		int row = customers.getSelectedRow();
		
		for(int column = 0; column < customersModel.getColumnCount(); column++) {
			
			String columnName = customersModel.getColumnName(column);
			Object value = customersModel.getValueAt(row, column);
			
			if("firstName".equals(columnName))
				customer.setFirstName((String)value);
			else if("lastName".equals(columnName))
				customer.setLastName((String)value);
			else if("street".equals(columnName))
				customer.setStreet((String)value);
			else if("zip".equals(columnName))
				customer.setZIP((String)value);
			else if("city".equals(columnName))
				customer.setCity((String)value);
			else if("phone".equals(columnName))
				customer.setPhone((String)value);
		}
		
		customerDAO.remove(customer);
		customers.removeRow();
		customerRows = customerDAO.getAllAsArray().length;
	}
	
	private void removeItem() {
		ItemDAO itemDAO = new ItemDAO();
		ItemView item = new ItemView();
		
		int row = items.getSelectedRow();
		
		for(int column = 0; column < itemsModel.getColumnCount(); column++) {
			
			String columnName = itemsModel.getColumnName(column);
			Object value = itemsModel.getValueAt(row, column);
			
			if("name".equals(columnName))
				item.setName((String)value);
			else if("description".equals(columnName))
				item.setDescription((String)value);
		}
		
		itemDAO.remove(item);
		items.removeRow();
		itemRows = itemDAO.getAllAsArray().length;
	}
	
	private void removeSize() {
		SizeDAO sizeDAO = new SizeDAO();
		SizeView size = new SizeView();
		
		int row = sizes.getSelectedRow();
		
		for(int column = 0; column < sizesModel.getColumnCount(); column++) {
			
			String columnName = sizesModel.getColumnName(column);
			Object value = sizesModel.getValueAt(row, column);
			
			if("value".equals(columnName))
				size.setValue((String)value);
		}
		
		sizeDAO.remove(size);
		sizes.removeRow();
		sizeRows = sizeDAO.getAllAsArray().length;
	}

	private UIButton doneButton() {
		UIButton doneBtn = new UIButton("Delete");
		doneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tabbedPane.getSelectedIndex() == 0)
					removeEmployee();
				else if (tabbedPane.getSelectedIndex() == 1)
					removeItem();
				else if (tabbedPane.getSelectedIndex() == 2)
					removeCustomer();
				else if (tabbedPane.getSelectedIndex() == 3)
					removeSize();
			}
		});
		return doneBtn;
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
	
	private boolean parseSalary(String value) {
		boolean hasString = false;
		for(int index = 0; index < value.length(); index++) {
			if(Character.isDigit(value.charAt(index)) || '.' == value.charAt(index))
				continue;
			else {
				hasString = true;
				break;
			}
		}
		
		if(hasString) {
			JOptionPane.showMessageDialog(this, "Salary format is wrong");
			return false;
		}
		
		return true;
	}
}
