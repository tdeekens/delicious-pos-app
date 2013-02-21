package delicious.pos.ui.components.order.target;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.*;
import javax.swing.*;

import delicious.pos.App;
import delicious.pos.business.logic.dao.gen.CustomerDAO;
import delicious.pos.business.logic.view.gen.CustomerView;
import delicious.pos.ui.components.extensions.UIPanel;
import delicious.pos.ui.components.extensions.UIScrollPane;

import java.util.ArrayList;

public class CustomerFilterList extends JList {

	private static final long serialVersionUID = 1L;
	private FilterField filterField;
    private int DEFAULT_FIELD_WIDTH = 20;
    private CustomerDAO customerDAO;
    private ArrayList<CustomerView> customerList;
    private UIScrollPane scrollPane;
    private UIPanel contentPane;

    public CustomerFilterList() {
        super();
        
        this.customerDAO = new CustomerDAO();
        this.customerList = this.customerDAO.findAll();
        
        setModel(new FilterModel(this.customerList));
        filterField = new FilterField(DEFAULT_FIELD_WIDTH);
        
        this.init();
    }

    public void setModel (ListModel m) {
        if (! (m instanceof FilterModel))
            throw new IllegalArgumentException();
        
        super.setModel(m);
    }

    public void addItem (CustomerView customerView) {
        ((FilterModel)getModel()).addElement(customerView);
    }

    public JTextField getFilterField() {
        return filterField;
    }
    
    public void init() {        
        this.scrollPane = new UIScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.contentPane = new UIPanel();
        
        this.contentPane.setPreferredSize(new Dimension(300, 300));
		this.contentPane.setMinimumSize(this.getPreferredSize());
		this.contentPane.setMaximumSize(this.getPreferredSize());
        
        this.contentPane.setLayout(new BorderLayout());
        
        this.contentPane.add(this.getFilterField(), BorderLayout.NORTH);
        this.contentPane.add(this.scrollPane, BorderLayout.CENTER);
        
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                App.orderState.setCustomer(customerList.get(index));
            }
        });
    }
    
    public UIPanel getPane() {
    	return this.contentPane;
    }

    class FilterModel extends AbstractListModel {
    	
		private static final long serialVersionUID = 1L;
		private ArrayList<CustomerView> customerList;
        private ArrayList<CustomerView> filterItems;
        
        public FilterModel(ArrayList<CustomerView> customerList) {
            super();
            this.customerList = customerList;
            this.filterItems = (ArrayList<CustomerView>) customerList.clone();
        }
        
        public Object getElementAt(int index) {
            if (index < filterItems.size())
                return filterItems.get(index);
            else
                return null;
        }
        
        public int getSize() {
            return filterItems.size();
        }
        
        public void addElement (CustomerView customerView) {
        	customerList.add(customerView);
            refilter();
        }
        
        private void refilter() {
            filterItems.clear();
            String term = getFilterField().getText();
            
            for (int i=0; i<customerList.size(); i++)
                if (customerList.get(i).toString().indexOf(term, 0) != -1)
                    filterItems.add(customerList.get(i));
            
            fireContentsChanged(this, 0, getSize());
        }
    }

    class FilterField extends JTextField implements DocumentListener {
    	private static final long serialVersionUID = 1L;
    	
		public FilterField (int width) {
            super(width);
            getDocument().addDocumentListener(this);
        }
		
        public void changedUpdate(DocumentEvent e) { ((FilterModel)getModel()).refilter(); }
        public void insertUpdate(DocumentEvent e) {((FilterModel)getModel()).refilter(); }
        public void removeUpdate(DocumentEvent e) {((FilterModel)getModel()).refilter(); }
    }
}
