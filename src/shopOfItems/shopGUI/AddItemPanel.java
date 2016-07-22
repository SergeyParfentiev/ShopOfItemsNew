package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AddItemPanel {

    private InterfaceShopOfItems shopOfItems;

    public AddItemPanel(InterfaceShopOfItems shopOfItems) {
        this.shopOfItems = shopOfItems;
    }

    public JPanel add() {
        JPanel addItem = new JPanel(new GridBagLayout());

        JLabel itemText = new JLabel("Add a new item");
        JLabel fillText = new JLabel("Fill in the details");
        JLabel name = new JLabel("Name");
        JLabel cost = new JLabel("Cost");
        JLabel number = new JLabel("Number");

        final JTextField itemName = new JTextField(10);

        final JTextField itemCost = new JTextField(7);

        itemCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)){
                    e.consume();
                }
            }
        });

        final JTextField itemNumber = new JTextField(7);
        itemNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)){
                    e.consume();
                }
            }
        });

        JButton addItemButton = new JButton("Accept");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopOfItems.addItem(itemName.getText(), itemCost.getText(), itemNumber.getText()) ;
            }
        });

        addItem.add(itemText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
        addItem.add(fillText, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

        addItem.add(name, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
        addItem.add(cost, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        addItem.add(number, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));

        addItem.add(itemName, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 50, 20, 0), 0, 0));
        addItem.add(itemCost, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        addItem.add(itemNumber, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, 50, 0, 0), 0, 0));

        addItem.add(addItemButton, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(70, 50, 30, 0), 0, 0));

        return addItem;
    }
}
