package shopOfItems.logic;

import shopOfItems.dbManagers.*;

import javax.swing.*;
import java.util.List;

public class ShopOfItems implements InterfaceShopOfItems{

    DBManagementSystem managementSystem;

    public ShopOfItems(DBManagementSystem dbManagementSystem) {
        this.managementSystem = dbManagementSystem;
    }

    @Override
    public Object[][] listOfItems() {

        return managementSystem.listOfItems();
    }

    @Override
    public Object[][] listOfCustomers() {
        return managementSystem.listOfCustomers();
    }

    @Override
    public Object[][] listOfOrders() {
        return managementSystem.listOfOrders();
    }

    @Override
    public boolean addItem(String name, String cost, String number) { 
        if(!"".equals(name) && !"".equals(String.valueOf(cost)) && !"".equals(number)) {
            managementSystem.addItem(name, Integer.parseInt(cost), Integer.parseInt(number));
            JOptionPane.showMessageDialog(null, "Data is entered successfully");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Not correct data", "Inane warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean addCustomer(String fName, String sName, String age, int gender_id, String address, String phoneNumber) {
        if(!"".equals(fName) && !"".equals(sName) && !"".equals(age) && !"".equals(address) && !phoneNumber.contains(" ")) {
            managementSystem.addCustomer(fName, sName, Integer.parseInt(age), gender_id, address, phoneNumber);
            JOptionPane.showMessageDialog(null, "Data is entered successfully");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Not correct data", "Inane warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    @Override
    public List<String> listOfItemsName() {
        return managementSystem.listOfItemsName();
    }

    @Override
    public List<String> listOfCustomersName() {
        return managementSystem.listOfCustomersName();
    }

    @Override
    public void makeOrder(int idItem, int idCustomer, String itemNumber) {
        if(itemNumber.length() > 0 && Integer.parseInt(itemNumber) != 0) {
            if(managementSystem.checkNumberOfItem(idItem, Integer.parseInt(itemNumber))) {
                managementSystem.buyItem(idItem, idCustomer, Integer.parseInt(itemNumber));
                JOptionPane.showMessageDialog(null, "Successful purchase");
            } else {
                JOptionPane.showMessageDialog(null, "Only " + itemNumber +
                        " left in stock ", "Inane warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not correct data", "Inane warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void editItem(int itemId, int boxColumnIdx, String column, String data) {
        if(!"".equals(data)) {
            if(boxColumnIdx == 0) {
                managementSystem.editItem(itemId, column, data, 0);
            } else {
                managementSystem.editItem(itemId, column, "", Integer.parseInt(data));
            }
            JOptionPane.showMessageDialog(null, "Data is edit successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Not correct data", "Inane warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void editCustomer(int customerId, int boxColumnIdx,  String column, String data) {
        if(!"".equals(data)) {
            if(boxColumnIdx == 2) {
                managementSystem.editCustomer(customerId, column, "", Integer.parseInt(data));
            }else {
                managementSystem.editCustomer(customerId, column, data, 0);
            }
            JOptionPane.showMessageDialog(null, "Data is edit successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Not correct data", "Inane warning", JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public void setManagementSystem(DBManagementSystem dbManagementSystem) {
        this.managementSystem = dbManagementSystem;
    }
}
