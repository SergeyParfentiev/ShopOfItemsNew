package shopOfItems.shopGUI;

import shopOfItems.dbManagers.DBManagementSystem;
import shopOfItems.logic.InterfaceShopOfItems;
import shopOfItems.logic.ShopOfItems;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopOfItemsGUI {

    private InterfaceShopOfItems shop;

    private JFrame frame;

    private ConnectionSuccessfulPanel connectionSuccessfulPanel;

    private ListOfItemsPanel listOfItemsPanel;
    private AddItemPanel addItemPanel;
    private EditItemPanel editItemPanel;

    private ListOfCustomersPanel listOfCustomersPanel;
    private AddCustomerPanel addCustomerPanel;
    private EditCustomerPanel editCustomerPanel;

    private ListOfOrderPanel listOfOrderPanel;
    private MakeOrderPanel makeOrderPanel;

    public ShopOfItemsGUI(DBManagementSystem dbManagementSystem) {
        shop = new ShopOfItems(dbManagementSystem);

        createPanels();
        createJFrame();
    }

    private void createPanels() {
        connectionSuccessfulPanel = new ConnectionSuccessfulPanel();

        listOfItemsPanel = new ListOfItemsPanel(shop);
        addItemPanel = new AddItemPanel(shop);
        editItemPanel = new EditItemPanel(shop, this);

        listOfCustomersPanel = new ListOfCustomersPanel(shop);
        addCustomerPanel = new AddCustomerPanel(shop);
        editCustomerPanel = new EditCustomerPanel(shop, this);

        listOfOrderPanel = new ListOfOrderPanel(shop);
        makeOrderPanel = new MakeOrderPanel(shop);
    }

    private void createJFrame() {

        frame = new JFrame("Bird Shop");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);

        JMenuItem viewItems = new JMenuItem("View items");
        viewItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeListOfItemsPanel();
            }
        });

        JMenuItem addItem = new JMenuItem("Add item");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeAddItemPanel();
            }
        });

        JMenuItem editItem = new JMenuItem("Edit item");
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeEditItemPanel(0, 0);
            }
        });

        JMenu items = new JMenu("Items");
        items.add(viewItems);
        items.add(addItem);
        items.add(editItem);

        JMenuItem viewCustomers = new JMenuItem("View customers");
        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeListOfCustomersPanel();
            }
        });

        JMenuItem addCustomer = new JMenuItem("Add customer");
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeAddCustomerPanel();
            }
        });

        JMenuItem editCustomer = new JMenuItem("Edit customer");
        editCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeEditCustomerPanel(0, 0, new JFormattedTextField().getFormatter());
            }
        });

        JMenu customers = new JMenu("Customers");
        customers.add(viewCustomers);
        customers.add(addCustomer);
        customers.add(editCustomer);

        JMenuItem viewOrder = new JMenuItem("View order");
        viewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeListOfSalesPanel();
            }
        });

        JMenuItem makeOrder = new JMenuItem("Make order");
        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeMakeOrderPanel();
            }
        });

        JMenu order = new JMenu("Order");
        order.add(viewOrder);
        order.add(makeOrder);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(items);
        jMenuBar.add(customers);
        jMenuBar.add(order);

        frame.getRootPane().setJMenuBar(jMenuBar);
        frame.getContentPane().add(connectionSuccessfulPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    protected void takeListOfItemsPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(listOfItemsPanel.list());

        frame.pack();
        frame.repaint();
    }

    protected void takeListOfCustomersPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(listOfCustomersPanel.list());

        frame.pack();
        frame.repaint();
    }

    protected void takeListOfSalesPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(listOfOrderPanel.list());

        frame.pack();
        frame.repaint();
    }

    protected void takeAddItemPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(addItemPanel.add());

        frame.pack();
        frame.repaint();
    }

    protected void takeAddCustomerPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(addCustomerPanel.add());

        frame.pack();
        frame.repaint();
    }

    protected void takeMakeOrderPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(makeOrderPanel.make());

        frame.pack();
        frame.repaint();
    }

    protected void takeEditItemPanel(int itemNameIdx, int itemColumnIdx) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(editItemPanel.edit(itemNameIdx, itemColumnIdx));

        frame.pack();
        frame.repaint();
    }

    protected void takeEditCustomerPanel(int customerNameIdx, int customerColumnIdx, AbstractFormatter formatter) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(editCustomerPanel.editCustomer(customerNameIdx, customerColumnIdx, formatter));

        frame.pack();
        frame.repaint();
    }
}