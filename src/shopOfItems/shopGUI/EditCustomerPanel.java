package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

public class EditCustomerPanel {

    private ShopOfItemsGUI shopOfItemsGUI;
    private InterfaceShopOfItems shopOfItems;

    private JFormattedTextField jFormattedTextField;

    public EditCustomerPanel(InterfaceShopOfItems shopOfItems, ShopOfItemsGUI shopOfItemsGUI) {
        this.shopOfItems = shopOfItems;
        this.shopOfItemsGUI = shopOfItemsGUI;
        jFormattedTextField = new JFormattedTextField();
    }

    public JPanel editCustomer(int customerNameIdx, int customerColumnIdx, AbstractFormatter formatter) {
        JPanel editCustomer = new JPanel(new GridBagLayout());

        JLabel editItemText = new JLabel("Edit Customer");
        JLabel fillItemText = new JLabel("Fill the details");

        JLabel selectCustomerName = new JLabel("Select customer name");
        JLabel selectCustomerColumn = new JLabel("Select customer column");
        JLabel insertData = new JLabel("Insert data");

        List<String> listOfCustomersName = shopOfItems.listOfCustomersName();
        final JComboBox<String> jComboBoxCustomersName = new JComboBox<>();
        for(String customerName : listOfCustomersName) {
            jComboBoxCustomersName.addItem(customerName);
        }

        if(listOfCustomersName.size() > 0) {
            jComboBoxCustomersName.setSelectedIndex(customerNameIdx);
        }

        final JFormattedTextField insertDataField = new JFormattedTextField(formatter);
        insertDataField.setPreferredSize(new Dimension(150, 20));

        final JComboBox<String> jComboBoxCustomerColumns = new JComboBox<>();
        jComboBoxCustomerColumns.addItem("First name");
        jComboBoxCustomerColumns.addItem("Second name");
        jComboBoxCustomerColumns.addItem("Age");
        jComboBoxCustomerColumns.addItem("Address");
        jComboBoxCustomerColumns.addItem("Phone number");

        jComboBoxCustomerColumns.setSelectedIndex(customerColumnIdx);
        jComboBoxCustomerColumns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jComboBoxCustomerColumns.getSelectedIndex() == 4) {
                    shopOfItemsGUI.takeEditCustomerPanel(jComboBoxCustomersName.getSelectedIndex(), jComboBoxCustomerColumns.getSelectedIndex(), phoneNumberFormat());
                } else {
                    shopOfItemsGUI.takeEditCustomerPanel(jComboBoxCustomersName.getSelectedIndex(), jComboBoxCustomerColumns.getSelectedIndex(), defaultFormat());
                }
                insertDataField.setText("");
            }
        });

        insertDataField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if(jComboBoxCustomerColumns.getSelectedIndex() == 3) {
                    e.setKeyCode(a);
                } else {
                    if (!Character.isDigit(a) && (jComboBoxCustomerColumns.getSelectedIndex() == 2 || jComboBoxCustomerColumns.getSelectedIndex() == 4)) {
                        e.consume();
                    }
                    if (!Character.isAlphabetic(a) && !(jComboBoxCustomerColumns.getSelectedIndex() == 2 || jComboBoxCustomerColumns.getSelectedIndex() == 4)) {
                        e.consume();
                    }
                }
            }
        });

        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shopOfItems.editCustomer(jComboBoxCustomersName.getSelectedIndex() + 1, jComboBoxCustomerColumns.getSelectedIndex(),
                        (String) jComboBoxCustomerColumns.getSelectedItem(), insertDataField.getText());

                shopOfItemsGUI.takeEditCustomerPanel(jComboBoxCustomersName.getSelectedIndex(), jComboBoxCustomerColumns.getSelectedIndex(),insertDataField.getFormatter());
            }
        });

        editCustomer.add(editItemText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, -13, 0, 0), 0, 0));
        editCustomer.add(fillItemText, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, -13, 0, 0), 0, 0));
        editCustomer.add(selectCustomerName, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editCustomer.add(jComboBoxCustomersName, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editCustomer.add(selectCustomerColumn, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editCustomer.add(jComboBoxCustomerColumns, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editCustomer.add(insertData, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editCustomer.add(insertDataField, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editCustomer.add(accept, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(50, -10, 80, 0), 0, 0));

        return editCustomer;
    }

    private AbstractFormatter defaultFormat() {
        return jFormattedTextField.getFormatter();
    }

    private AbstractFormatter phoneNumberFormat() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("(###)###-##-##");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}
