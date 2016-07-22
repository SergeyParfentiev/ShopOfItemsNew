package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class AddCustomerPanel {

    private InterfaceShopOfItems shopOfItems;

    private int customerGenderIdx;

    public AddCustomerPanel(InterfaceShopOfItems shopOfItems) {
        this.shopOfItems = shopOfItems;
    }

    public JPanel add() {
        JPanel addCustomer = new JPanel(new GridBagLayout());

        customerGenderIdx = 0;

        JLabel customerText = new JLabel("Add a new customer");
        JLabel fillText = new JLabel("Fill in the details");

        JLabel firstName = new JLabel("First name");
        JLabel secondName = new JLabel("Second name");
        JLabel age = new JLabel("Age");
        JLabel gender = new JLabel("Gender");
        JLabel address = new JLabel("Address");
        JLabel phoneNumber = new JLabel("Phone Number");

        final JTextField customerFirstName = new JTextField(20);
        customerFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isAlphabetic(a)){
                    e.consume();
                }
            }
        });
        final JTextField customerSecondName = new JTextField(20);
        customerSecondName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isAlphabetic(a)){
                    e.consume();
                }
            }
        });
        final JTextField customerAge = new JTextField(5);
        customerAge.setText("16");
        customerAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)){
                    e.consume();
                }
            }
        });

        final JComboBox<String> customerGenderBox = new JComboBox<>();
        customerGenderBox.addItem("Male");
        customerGenderBox.addItem("Female");
        customerGenderBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerGenderIdx = customerGenderBox.getSelectedIndex();
            }
        });

        final JTextField customerAddress = new JTextField(20);

        final JFormattedTextField customerPhoneNumber = new JFormattedTextField(phoneNumberFormat());
        customerPhoneNumber.setPreferredSize(new Dimension(95, 20));

        JButton addCustomerButton = new JButton("Accept");
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopOfItems.addCustomer(customerFirstName.getText(), customerSecondName.getText(), customerAge.getText(),
                        customerGenderIdx + 1, customerAddress.getText(), customerPhoneNumber.getText());
            }
        });

        addCustomer.add(customerText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
        addCustomer.add(fillText, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

        addCustomer.add(firstName, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
        addCustomer.add(secondName, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        addCustomer.add(age, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        addCustomer.add(gender, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        addCustomer.add(address, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        addCustomer.add(phoneNumber, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));

        addCustomer.add(customerFirstName, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 50, 20, 0), 0, 0));
        addCustomer.add(customerSecondName, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        addCustomer.add(customerAge, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 50, 0, 0), 0, 0));
        addCustomer.add(customerGenderBox, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 50, 0, 0), 0, 0));
        addCustomer.add(customerAddress, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 50, 0, 0), 0, 0));
        addCustomer.add(customerPhoneNumber, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 50, 0, 0), 0, 0));
        addCustomer.add(addCustomerButton, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(70, 50, 50, 0), 0, 0));

        return addCustomer;
    }

    private JFormattedTextField.AbstractFormatter phoneNumberFormat() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("(###)###-##-##");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}
