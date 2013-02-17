package delicious.pos.ui.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

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
	private Object[][] data = {
			{ "Kathy", "Smith", "Snowboarding", new Integer(5),
					new Boolean(false) },
			{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
			{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
			{ "Jane", "White", "Speed reading", new Integer(20),
					new Boolean(true) },
			{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
	private String[] columnNames = { "First Name", "Last Name", "Sport",
			"# of Years", "Vegetarian" };

	private void init() {

		UIPanel plusMinusPanel = plusMinusPanel();
		UIPanel btnsPanel = btnsPanel();
		UIPanel tabsPanel = new UIPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabsPanel.add(tabbedPane);
		// Add images
		tabbedPane.addTab("Employees", null, employeesPanel(), "Employees");
		tabbedPane.addTab("Items", null, itemsPanel(), "Items");
		tabbedPane.addTab("Customers", null, customersPanel(), "Customers");
		tabbedPane.addTab("Sizes", null, sizesPanel(), "Sizes");
		
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

	private UITable employees;
	private DefaultTableModel employeesModel;
	private UIPanel employeesPanel() {
		UIPanel employeesPanel = new UIPanel();

		employeesModel = new DefaultTableModel(data, columnNames);
		employees = new UITable(employeesModel);

		UIScrollPane employeeScroll = new UIScrollPane(employees);
		employeesPanel.add(employeeScroll);

		return employeesPanel;
	}

	private UITable items;
	private DefaultTableModel itemsModel;
	private UIPanel itemsPanel() {
		UIPanel itemsPanel = new UIPanel();

		itemsModel = new DefaultTableModel(data, columnNames);
		items = new UITable(itemsModel);

		UIScrollPane itemScroll = new UIScrollPane(items);
		itemsPanel.add(itemScroll);

		return itemsPanel;
	}
	
	private UITable customers;
	private DefaultTableModel customersModel;
	private UIPanel customersPanel() {
		UIPanel customersPanel = new UIPanel();

		customersModel = new DefaultTableModel(data, columnNames);
		customers = new UITable(customersModel);

		UIScrollPane customerScroll = new UIScrollPane(customers);
		customersPanel.add(customerScroll);

		return customersPanel;
	}
	
	private UITable sizes;
	private DefaultTableModel sizesModel;
	private UIPanel sizesPanel() {
		UIPanel sizesPanel = new UIPanel();
		sizesModel = new DefaultTableModel(
				new Object [][]{{"Small"}, {"Medium"}, {"Large"}}, 
				new String[] {"Size"});
		sizes = new UITable(sizesModel);
		sizes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		UIScrollPane sizesScroll = new UIScrollPane(sizes);
		sizesPanel.add(sizesScroll);
		
		return sizesPanel;
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

	private UIButton plusButton() {
		UIButton plusBtn = new UIButton("+");
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
				System.out.println("Saved!");
			}
		});
		return saveBtn;
	}

	private UIButton cancelButton() {
		UIButton cancelBtn = new UIButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		return cancelBtn;
	}

	private UIButton doneButton() {
		UIButton doneBtn = new UIButton("Done");
		doneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		return doneBtn;
	}
}
