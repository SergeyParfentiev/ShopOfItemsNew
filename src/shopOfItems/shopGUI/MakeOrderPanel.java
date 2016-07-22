package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class MakeOrderPanel {

    private InterfaceShopOfItems shopOfItems;

    public MakeOrderPanel(InterfaceShopOfItems shopOfItems) {
        this.shopOfItems = shopOfItems;
    }

    public JPanel make() {
        JPanel order = new JPanel(new GridBagLayout());

        JLabel choose = new JLabel("Make an order");
        JLabel item = new JLabel("Choose item");
        final JLabel number = new JLabel("Choose number");
        JLabel customer = new JLabel("Choose customer");

        java.util.List<String> listOfItemsName = shopOfItems.listOfItemsName();

        final JComboBox<String> comboBoxItemsName = new JComboBox<>();
        for(String itemName : listOfItemsName) {
            comboBoxItemsName.addItem(itemName);
        }

        final JTextField itemNumber = new JTextField(5);
        itemNumber.setText("1");
        itemNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)){
                    e.consume();
                }
            }
        });

        List<String> listOfCustomersName = shopOfItems.listOfCustomersName();

        final JComboBox<String> comboBoxCustomersName = new JComboBox<>();
        for(String customerName : listOfCustomersName) {
            comboBoxCustomersName.addItem(customerName);
        }

        JButton buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(75, 25));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopOfItems.makeOrder(comboBoxItemsName.getSelectedIndex() + 1,
                        comboBoxCustomersName.getSelectedIndex() + 1, itemNumber.getText());
            }
        });

        order.add(choose, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 50, 250), 0, 0));
        order.add(item, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 150, 0, 0), 0, 0));
        order.add(comboBoxItemsName, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 80, 0, 0), 0, 0));
        order.add(number, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 150, 0, 0), 0, 0));
        order.add(itemNumber, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 80, 0, 0), 0, 0));
        order.add(customer, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 150, 0, 0), 0, 0));
        order.add(comboBoxCustomersName, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 80, 0, 0), 0, 0));
        order.add(buyButton, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(80, 10, 100, 0), 0, 0));

        return order;
    }
}
