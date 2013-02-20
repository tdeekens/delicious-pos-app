package delicious.pos.ui.components.order.target;

import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

import delicious.pos.business.logic.dao.gen.CustomerDAO;
import delicious.pos.business.logic.view.gen.CustomerView;

import java.util.ArrayList;

public class CustomerFilterList extends JList {

	private static final long serialVersionUID = 1L;
	private FilterField filterField;
    private int DEFAULT_FIELD_WIDTH = 20;
    private CustomerDAO customerDAO;
    private ArrayList<CustomerView> customerList;

    public CustomerFilterList() {
        super();
        
        this.customerDAO = new CustomerDAO();
        this.customerList = this.customerDAO.findAll();
        
        setModel(new FilterModel(this.customerList));
        filterField = new FilterField (DEFAULT_FIELD_WIDTH);
    }

    public void setModel (ListModel m) {
        if (! (m instanceof FilterModel))
            throw new IllegalArgumentException();
        super.setModel (m);
    }

    public void addItem (CustomerView customerView) {
        ((FilterModel)getModel()).addElement(customerView);
    }

    public JTextField getFilterField() {
        return filterField;
    }

    // test filter list
    public static void main (String[] args) {
        String[] listItems = {
            "Kostas", "John", "Dani", "Iva",
            "Tobi", "kostas", "john", "skata", "daniela", "tobias", "psofos"
        };
        JFrame frame = new JFrame ("FilteredJList");
        frame.getContentPane().setLayout (new BorderLayout());
        
        CustomerFilterList list = new CustomerFilterList();
        
        JScrollPane pane =
            new JScrollPane (list,
                             ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getContentPane().add (pane, BorderLayout.CENTER);
        frame.getContentPane().add (list.getFilterField(),
                                    BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    // inner class to provide filtered model
    class FilterModel extends AbstractListModel {
    	
		private static final long serialVersionUID = 1L;
		private ArrayList<CustomerView> customerList;
        private ArrayList<CustomerView> filterItems;
        
        public FilterModel(ArrayList<CustomerView> customerList) {
            super();
            customerList = customerList;
            filterItems = customerList;
        }
        public Object getElementAt (int index) {
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
            
            fireContentsChanged (this, 0, getSize());
        }
    }

    class FilterField extends JTextField implements DocumentListener {
    	private static final long serialVersionUID = 1L;
    	
		public FilterField (int width) {
            super(width);
            getDocument().addDocumentListener (this);
        }
		
        public void changedUpdate (DocumentEvent e) { ((FilterModel)getModel()).refilter(); }
        public void insertUpdate (DocumentEvent e) {((FilterModel)getModel()).refilter(); }
        public void removeUpdate (DocumentEvent e) {((FilterModel)getModel()).refilter(); }
    }
}
