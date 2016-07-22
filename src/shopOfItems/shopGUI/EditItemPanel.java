package shopOfItems.shopGUI;

import shopOfItems.logic.InterfaceShopOfItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class EditItemPanel {

    private ShopOfItemsGUI shopOfItemsGUI;
    private InterfaceShopOfItems shopOfItems;

    public EditItemPanel(InterfaceShopOfItems shopOfItems, ShopOfItemsGUI shopOfItemsGUI) {
        this.shopOfItems = shopOfItems;
        this.shopOfItemsGUI = shopOfItemsGUI;
    }

    public JPanel edit(int itemNameIdx, int itemColumnIdx) {
        JPanel editItem = new JPanel(new GridBagLayout());

        JLabel editItemText = new JLabel("Edit Item");
        JLabel fillItemText = new JLabel("Fill the details");

        JLabel selectItemName = new JLabel("Select item name");
        JLabel selectItemColumn = new JLabel("Select item column");
        JLabel insertData = new JLabel("Insert data");

        List<String> listOfItemsName = shopOfItems.listOfItemsName();

        final JComboBox<String> jComboBoxItemsName = new JComboBox<>();
        for(String itemName : listOfItemsName) {
            jComboBoxItemsName.addItem(itemName);
        }

        if(listOfItemsName.size() > 0) {
            jComboBoxItemsName.setSelectedIndex(itemNameIdx);
        }

        final JTextField insertDataField = new JTextField(10);

        final JComboBox<String> jComboBoxItemColumns = new JComboBox<>();
        jComboBoxItemColumns.addItem("Name");
        jComboBoxItemColumns.addItem("Cost");
        jComboBoxItemColumns.addItem("Number");

        jComboBoxItemColumns.setSelectedIndex(itemColumnIdx);
        jComboBoxItemColumns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDataField.setText("");

            }
        });

        insertDataField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isAlphabetic(a) && jComboBoxItemColumns.getSelectedIndex() == 0){
                    e.consume();
                }
                if(!Character.isDigit(a) && jComboBoxItemColumns.getSelectedIndex() != 0) {
                    e.consume();
                }
            }
        });

        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shopOfItems.editItem(jComboBoxItemsName.getSelectedIndex() + 1, jComboBoxItemColumns.getSelectedIndex(),
                        (String) jComboBoxItemColumns.getSelectedItem(), insertDataField.getText());

                shopOfItemsGUI.takeEditItemPanel(jComboBoxItemsName.getSelectedIndex(), jComboBoxItemColumns.getSelectedIndex());
            }
        });

        editItem.add(editItemText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
        editItem.add(fillItemText, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20, -7, 0, 0), 0, 0));
        editItem.add(selectItemName, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editItem.add(jComboBoxItemsName, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editItem.add(selectItemColumn, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editItem.add(jComboBoxItemColumns, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editItem.add(insertData, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        editItem.add(insertDataField, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30, 50, 0, 0), 0, 0));
        editItem.add(accept, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(80, -10, 75, 0), 0, 0));

        return editItem;
    }
}
