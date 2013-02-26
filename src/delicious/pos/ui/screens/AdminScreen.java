package delicious.pos.ui.screens;

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

import delicious.pos.business.logic.dao.PriceDAO;
import delicious.pos.business.logic.dao.SizeDAO;
import delicious.pos.business.logic.dao.gen.CustomerDAO;
import delicious.pos.business.logic.dao.gen.EmployeeDAO;
import delicious.pos.business.logic.dao.gen.ItemDAO;
import delicious.pos.business.logic.view.PriceView;
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

		UIPanel btnsPanel = btnsPanel();
		UIPanel tabsPanel = new UIPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Employees", null, employees(), "Employees");
		tabbedPane.addTab("Items", null, items(), "Items");
		tabbedPane.addTab("Customers", null, customers(), "Customers");
		tabbedPane.addTab("Sizes", null, sizes(), "Sizes");
		tabbedPane.addTab("Prices", null, prices(), "Prices");
		
		tabsPanel.add(tabbedPane);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnsPanel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabsPanel, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabsPanel, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
					.addGap(18).addComponent(btnsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
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

			employeeRows = employeeDAO.getAllAsArray().length;
			
			employeesModel = new DefaultTableModel(employeeDAO.getAllAsArray(), 
							employeeDAO.getColumnNames());
			employees = new UITable(employeesModel) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					if(employeeRows > employees.getSelectedRow())
						if(colIndex == 0)
							return false;
					return true;
			    }
			};
			
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

	private void updateEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		if(employees.isEditing())
			employees.getCellEditor().stopCellEditing();

		for(int row : touchedEmployeeRows) {
			EmployeeView employee = new EmployeeView();
			employee.setUserName((String)employeesModel.getValueAt(row, 0));
			employee.setSalary(Float.valueOf(employeesModel.getValueAt(row, 1).toString()));
			employee.setPhone((String)employeesModel.getValueAt(row, 2));
			employee.setPosition((String)employeesModel.getValueAt(row, 3));
			employeeDAO.update(employee);
			JOptionPane.showMessageDialog(this, "Employee " + employee.getUserName() 
					+ " was successfully updated");
		}
	}

	private void insertEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeView employee = new EmployeeView();
		
		if(employees.isEditing())
			employees.getCellEditor().stopCellEditing();

		for(int row = employeeRows; row < employeesModel.getRowCount(); row++) {
			for(int column = 0; column < employeesModel.getColumnCount(); column++) {
				
				String columnName = employeesModel.getColumnName(column);
				Object value = employeesModel.getValueAt(row, column);

				if(value == null)
					return;

				if("userName".equals(columnName))
					employee.setUserName((String)value);
				if("salary".equals(columnName)) {
					if(!parseFloat(String.valueOf(value)))
					return;
					employee.setSalary(Float.parseFloat((String)value));
				}
				if("phone".equals(columnName)) {
					employee.setPhone((String)value);
				}
				if("position".equals(columnName)) {
					employee.setPosition((String)value);
				}
			}
			employeeDAO.insert(employee);
			employeeRows = employeeDAO.getAllAsArray().length;
			JOptionPane.showMessageDialog(this, "Employee " + employee.getUserName() 
					+ " was successfully saved");
			touchedEmployeeRows.clear();
		}
	}

	private void removeEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeView employee = new EmployeeView();
		
		int row = employees.getSelectedRow();

		if(row == -1)
			return;
		
		for(int column = 0; column < employeesModel.getColumnCount(); column++) {

			String columnName = employeesModel.getColumnName(column);
			Object value = employeesModel.getValueAt(row, column);

			if("userName".equals(columnName))
				employee.setUserName((String)value);
			if("salary".equals(columnName)) {
				if(!parseFloat(String.valueOf(value)))
					return;
				employee.setSalary(Float.valueOf(String.valueOf(value)));
			}
			if("phone".equals(columnName)) {
				employee.setPhone((String)value);
			}
			if("position".equals(columnName)) {
				employee.setPosition((String)value);
			}
		}
		
		employeeDAO.remove(employee);
		employees.removeRow();
		employeeRows = employeeDAO.getAllAsArray().length;
		touchedEmployeeRows.clear();
		JOptionPane.showMessageDialog(this, "Employee " + employee.getUserName() 
					+ " was successfully deleted");
	}
	


	private int itemRows;
	private UITable items;
	private DefaultTableModel itemsModel;
	private List<Integer> touchedItemRows;
	private UIScrollPane items() {
		ItemDAO itemDAO = new ItemDAO();
		touchedItemRows = new Vector<Integer>();	
	
		itemRows = itemDAO.getAllAsArray().length;
		
		itemsModel = new DefaultTableModel(itemDAO.getAllAsArray(), 
					itemDAO.getColumnNames());
		items = new UITable(itemsModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if(itemRows > items.getSelectedRow())
					if(colIndex == 0)
						return false;
				return true;
		    }
		};

		ListSelectionModel rowSelectionModel = items.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(items.getSelectedRow() < itemRows)
					touchedItemRows.add(items.getSelectedRow());
			}
		});

		return  new UIScrollPane(items);
	}

	private void updateItem() {
		ItemDAO itemDAO = new ItemDAO();

		if(items.isEditing())
			items.getCellEditor().stopCellEditing();

		for(int row : touchedItemRows) {
			ItemView item = new ItemView();
			item.setName((String)itemsModel.getValueAt(row, 0));
			item.setDescription((String)itemsModel.getValueAt(row, 1));
			itemDAO.update(item);
			JOptionPane.showMessageDialog(this, "Item " + item.getName() 
					+ " was successfully updated");
		}
		touchedItemRows.clear();
	}

	private void insertItem() {
		ItemDAO itemDAO = new ItemDAO();
		ItemView item = new ItemView();

		if(items.isEditing())
			items.getCellEditor().stopCellEditing();
		
		for(int row = itemRows; row < itemsModel.getRowCount(); row++) {
			for(int column = 0; column < itemsModel.getColumnCount(); column++) {
				
				String columnName = itemsModel.getColumnName(column);
				Object value = itemsModel.getValueAt(row, column);

				if(value == null)
					return;
				
				if("name".equals(columnName))
					item.setName((String)value);

				if("description".equals(columnName))
					item.setDescription((String)value);
			}
			itemDAO.insert(item);
			itemRows = itemDAO.getAllAsArray().length;
			JOptionPane.showMessageDialog(this, "Item " + item.getName()
					+ " was successfully saved");
			touchedItemRows.clear();
		}
	}

	private void removeItem() {
		ItemDAO itemDAO = new ItemDAO();
		ItemView item = new ItemView();
		
		int row = items.getSelectedRow();
	
		if(row == -1)
			return;
		
		for(int column = 0; column < itemsModel.getColumnCount(); column++) {
			
			String columnName = itemsModel.getColumnName(column);
			Object value = itemsModel.getValueAt(row, column);
			
			if("name".equals(columnName))
				item.setName((String)value);

			if("description".equals(columnName))
				item.setDescription((String)value);
		}
		
		itemDAO.remove(item);
		items.removeRow();
		itemRows = itemDAO.getAllAsArray().length;
		touchedItemRows.clear();
		JOptionPane.showMessageDialog(this, "Item " + item.getName() 
					+ " was successfully deleted");
	}

	private int customerRows;
	private UITable customers;
	private DefaultTableModel customersModel;
	private List<Integer> touchedCustomerRows;
	private UIScrollPane customers() {
		CustomerDAO customerDAO = new CustomerDAO();
		touchedCustomerRows = new Vector<Integer>();

		customerRows = customerDAO.getAllAsArray().length;		

		customersModel = new DefaultTableModel(customerDAO.getAllAsArray(), 
						customerDAO.getColumnNames());
		customers = new UITable(customersModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if(customerRows > customers.getSelectedRow())
					if(colIndex == 0)
						return false;
				return true;
		    }
		};
		
		ListSelectionModel rowSelectionModel = customers.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(customers.getSelectedRow() < customerRows)
					touchedCustomerRows.add(customers.getSelectedRow());
			}
		});
		
		return new UIScrollPane(customers);
	}

	private void updateCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();

		if(customers.isEditing())
			customers.getCellEditor().stopCellEditing();

		for(int row : touchedCustomerRows) {
			CustomerView customer = new CustomerView();
											
			customer.setId((Integer)customersModel.getValueAt(row, 0));
			customer.setFirstName((String)customersModel.getValueAt(row, 1));
			customer.setLastName((String)customersModel.getValueAt(row, 2));
			customer.setStreet((String)employeesModel.getValueAt(row, 1+2));
			customer.setZIP((String)employeesModel.getValueAt(row, 2+2));
			customer.setCity((String)employeesModel.getValueAt(row, 3+2));
			customer.setPhone((String)employeesModel.getValueAt(row, 4+2));
			customerDAO.update(customer);
			JOptionPane.showMessageDialog(this, "Customer " 
					+ customer.getFirstName() 
					+ " " + customer.getLastName() 
					+ " was successfully updated");
		}
		touchedCustomerRows.clear();
	}

	private void insertCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerView customer = new CustomerView();

		if(customers.isEditing())
			customers.getCellEditor().stopCellEditing();
		
		for(int row = customerRows; row < customersModel.getRowCount(); row++) {
			for(int column = 0; column < customersModel.getColumnCount(); column++) {
				
				String columnName = customersModel.getColumnName(column);
				Object value = customersModel.getValueAt(row, column);

				if(value == null)
					return;

				if("id".equals(columnName))
					customer.setId(Integer.parseInt(String.valueOf(value)));
				if("firstName".equals(columnName))
					customer.setFirstName((String)value);
				if("lastName".equals(columnName))
					customer.setLastName((String)value);
				if("street".equals(columnName))
					customer.setStreet((String)value);
				if("zip".equals(columnName))
					customer.setZIP((String)value);
				if("city".equals(columnName))
					customer.setCity((String)value);
				if("phone".equals(columnName))
					customer.setPhone((String)value);
			}
			customerDAO.insert(customer);
			customerRows = customerDAO.getAllAsArray().length;
			JOptionPane.showMessageDialog(this, "Customer " 
					+ customer.getFirstName() 
					+ " " + customer.getLastName() 
					+ " was successfully saved");
			touchedCustomerRows.clear();
		}
	}

	private void removeCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		CustomerView customer = new CustomerView();
		
		int row = customers.getSelectedRow();

		if(row == -1)
			return;
		
		for(int column = 0; column < customersModel.getColumnCount(); column++) {
			
			String columnName = customersModel.getColumnName(column);
			Object value = customersModel.getValueAt(row, column);

			if("id".equals(columnName))
				customer.setId((Integer)value);
			if("firstName".equals(columnName))
				customer.setFirstName((String)value);
			if("lastName".equals(columnName))
				customer.setLastName((String)value);
			if("street".equals(columnName))
				customer.setStreet((String)value);
			if("zip".equals(columnName))
				customer.setZIP((String)value);
			if("city".equals(columnName))
				customer.setCity((String)value);
			if("phone".equals(columnName))
				customer.setPhone((String)value);
			JOptionPane.showMessageDialog(this, "Customer " 
					+ customer.getFirstName() 
					+ " " + customer.getLastName() 
					+ " was successfully deleted");
		}
		
		customers.removeRow();
		if(customer.getId() == null)
			return;
		customerDAO.remove(customer);
		customerRows = customerDAO.getAllAsArray().length;
		touchedCustomerRows.clear();
	}
	

	private int sizeRows;
	private UITable sizes;
	private DefaultTableModel sizesModel;
	private List<Integer> touchedSizeRows;
	private UIScrollPane sizes() {
		SizeDAO sizeDAO = new SizeDAO();
		touchedSizeRows = new Vector<Integer>();

		sizeRows = sizeDAO.getAllAsArray().length;

		sizesModel = new DefaultTableModel(sizeDAO.getAllAsArray(), 
					sizeDAO.getColumnNames());

		sizes = new UITable(sizesModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if(sizeRows > sizes.getSelectedRow())
					if(colIndex == 0)
						return false;
				return true;
		    }
		};
		sizes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		ListSelectionModel rowSelectionModel = sizes.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;
				if(sizes.getSelectedRow() < sizeRows)
					touchedSizeRows.add(sizes.getSelectedRow());
			}
		});
		
		return new UIScrollPane(sizes);
	}

	private void insertSize() {
		SizeDAO sizeDAO = new SizeDAO();
		SizeView size = new SizeView();

		if(sizes.isEditing())
			sizes.getCellEditor().stopCellEditing();
		
		for(int row = sizeRows; row < sizesModel.getRowCount(); row++) {
			for(int column = 0; column < sizesModel.getColumnCount(); column++) {
				
				String columnName = sizesModel.getColumnName(column);
				Object value = sizesModel.getValueAt(row, column);
				
				if(value == null)
					return;

				if("value".equals(columnName))
					size.setValue((String)value);
			}
			sizeDAO.insert(size);
			sizeRows = sizeDAO.getAllAsArray().length;

			JOptionPane.showMessageDialog(this, "Size " + size.getValue() 
					+ " was successfully saved");
	
			touchedSizeRows.clear();
		}
	}

	private int priceRows;
	private UITable prices;
	private DefaultTableModel pricesModel;
	private List<Integer> touchedPriceRows;
	private UIScrollPane prices() {
		PriceDAO priceDAO = new PriceDAO();
		touchedPriceRows = new Vector<Integer>();

		priceRows = priceDAO.getAllAsArray().length;
		
		pricesModel = new DefaultTableModel(priceDAO.getAllAsArray(), 
						priceDAO.getColumnNames());
		prices = new UITable(pricesModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if(priceRows > prices.getSelectedRow())
					if(colIndex == 0)
						return false;
				return true;
		    }
		};
		
		ListSelectionModel rowSelectionModel = prices.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting())
					return;

				if(prices.getSelectedRow() < priceRows) 
					touchedPriceRows.add(prices.getSelectedRow());
			}
		});
		
		return new UIScrollPane(prices);
	}
	
	private void updatePrice() {
		PriceDAO priceDAO = new PriceDAO();

		if(prices.isEditing())
			prices.getCellEditor().stopCellEditing();

		for(int row : touchedPriceRows) {
			PriceView price = new PriceView();
			price.setId((Integer)pricesModel.getValueAt(row, 0));
			price.setValue(Float.valueOf(pricesModel.getValueAt(row, 1).toString()));
			price.setSizeValue((String)pricesModel.getValueAt(row, 2));
			price.setItemName((String)pricesModel.getValueAt(row, 3));
			priceDAO.update(price);

			JOptionPane.showMessageDialog(this, "Price " + price.getValue() 
					+ " " + " for " + price.getItemName()
					+ " " + price.getSizeValue() 
					+ " was successfully updated");
		}
		touchedPriceRows.clear();
	}
	
	private void insertPrice() {
		PriceDAO priceDAO = new PriceDAO();
		PriceView price = new PriceView();
		
		for(int row = priceRows; row < pricesModel.getRowCount(); row++) {
			for(int column = 0; column < pricesModel.getColumnCount(); column++) {
				
				String columnName = pricesModel.getColumnName(column);
				Object value = pricesModel.getValueAt(row, column);

				if(value == null)
					return;

				if("id".equals(columnName))
					price.setId(Integer.parseInt(String.valueOf(value)));
				if("value".equals(columnName)) {
					if(!parseFloat(String.valueOf(value)))
					return;
					price.setValue(Float.parseFloat((String)value));
				}
				if("size_value".equals(columnName))
					price.setItemName((String)value);
				if("item_name".equals(columnName))
					price.setSizeValue((String)value);
			}
			priceDAO.insert(price);
			priceRows = priceDAO.getAllAsArray().length;
			JOptionPane.showMessageDialog(this, "Price " + price.getValue() 
					+ " " + " for " + price.getItemName()
					+ " " + price.getSizeValue() 
					+ " was successfully saved");
			touchedPriceRows.clear();
		}
	}
	
	private void removePrice() {
		PriceDAO priceDAO = new PriceDAO();
		PriceView price = new PriceView();
		
		int row = prices.getSelectedRow();

		if(row == -1)
			return;
		
		for(int column = 0; column < pricesModel.getColumnCount(); column++) {
			
			String columnName = pricesModel.getColumnName(column);
			Object value = pricesModel.getValueAt(row, column);

			if("id".equals(columnName))
				price.setId(Integer.parseInt(String.valueOf(value)));
			if("value".equals(columnName)) {
				if(!parseFloat(String.valueOf(value)))
				return;
				price.setValue(Float.parseFloat((String)value));
			}
			if("size_value".equals(columnName))
				price.setItemName((String)value);
			if("item_name".equals(columnName))
				price.setSizeValue((String)value);
		}
		
		prices.removeRow();
		if(price.getId() == null)
			return;
		priceDAO.remove(price);
		priceRows = priceDAO.getAllAsArray().length;
		touchedPriceRows.clear();

		JOptionPane.showMessageDialog(this, "Price " + price.getValue() 
					+ " " + " for " + price.getItemName()
					+ " " + price.getSizeValue() 
					+ " was successfully deleted");
		touchedPriceRows.clear();
	}

	private void save() {
		if (tabbedPane.getSelectedIndex() == 0) {
			insertEmployee();
			updateEmployee();
		}
		else if (tabbedPane.getSelectedIndex() == 1) {
			insertItem();
			updateItem();
		}
		else if (tabbedPane.getSelectedIndex() == 2) {
			insertCustomer();
			updateCustomer();
		}
		else if (tabbedPane.getSelectedIndex() == 3) {
			insertSize();
		}
		else if (tabbedPane.getSelectedIndex() == 4) {
			insertPrice();
			updatePrice();
		}
	}
	
	private UIPanel panel = this;
	private UIButton deleteButton() {
		UIButton deleteBtn = new UIButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tabbedPane.getSelectedIndex() == 0)
					removeEmployee();
				else if (tabbedPane.getSelectedIndex() == 1)
					removeItem();
				else if (tabbedPane.getSelectedIndex() == 2)
					removeCustomer();
				else if (tabbedPane.getSelectedIndex() == 3)
					JOptionPane.showMessageDialog(panel,
                             "Your cannot delete a size");
				else if (tabbedPane.getSelectedIndex() == 4)
					removePrice();
			}
		});
		return deleteBtn;
	}

	private UIButton cancelButton() {
		UIButton cancelBtn = new UIButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tabbedPane.getSelectedIndex() == 0)
					employees.removeRows(employeeRows);
				else if (tabbedPane.getSelectedIndex() == 1)
					items.removeRows(itemRows);
				else if (tabbedPane.getSelectedIndex() == 2)
					customers.removeRows(customerRows);
				else if (tabbedPane.getSelectedIndex() == 3)
					sizes.removeRows(sizeRows);
				else if (tabbedPane.getSelectedIndex() == 4)
					prices.removeRows(priceRows);
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

	private UIButton newBtn;
	private UIButton newButton() {
		newBtn = new UIButton("New");
		newBtn.addActionListener(new ActionListener() {
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
				else if (tabbedPane.getSelectedIndex() == 4)
					prices.insertRow();
			}
		});

		return newBtn;
	}

	private UIPanel btnsPanel() {
		UIPanel btnsPanel = new UIPanel();
		btnsPanel.add(newButton());
		btnsPanel.add(saveButton());
		btnsPanel.add(deleteButton());
		btnsPanel.add(cancelButton());

		return btnsPanel;
	}

	private boolean parseFloat(String value) {
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
			JOptionPane.showMessageDialog(this, "Value format is wrong");
			return false;
		}
		
		return true;
	}

}

