package delicious.pos.ui.components.order.target;

import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class CustomerFilterList extends JList {

	private static final long serialVersionUID = 1L;
	private FilterField filterField;
    private int DEFAULT_FIELD_WIDTH = 20;

    public CustomerFilterList() {
        super();
        setModel(new FilterModel());
        filterField = new FilterField (DEFAULT_FIELD_WIDTH);
    }

    public void setModel (ListModel m) {
        if (! (m instanceof FilterModel))
            throw new IllegalArgumentException();
        super.setModel (m);
    }

    public void addItem (Object o) {
        ((FilterModel)getModel()).addElement (o);
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
        // populate list
        CustomerFilterList list = new CustomerFilterList();
        for (int i=0; i<listItems.length; i++)
            list.addItem (listItems[i]);
        // add to gui
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
		ArrayList<Object> items;
        ArrayList<Object> filterItems;
        public FilterModel() {
            super();
            items = new ArrayList<Object>();
            filterItems = new ArrayList<Object>();
        }
        public Object getElementAt (int index) {
            if (index < filterItems.size())
                return filterItems.get (index);
            else
                return null;
        }
        public int getSize() {
            return filterItems.size();
        }
        public void addElement (Object o) {
            items.add (o);
            refilter();
        }
        private void refilter() {
            filterItems.clear();
            String term = getFilterField().getText();
            for (int i=0; i<items.size(); i++)
                if (items.get(i).toString().indexOf(term, 0) != -1)
                    filterItems.add (items.get(i));
            fireContentsChanged (this, 0, getSize());
        }
    }

    // inner class provides filter-by-keystroke field
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
