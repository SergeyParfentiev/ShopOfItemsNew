package shopOfItems.logic;

import shopOfItems.dbManagers.DBManagementSystem;

import java.util.List;

public interface InterfaceShopOfItems {

    Object[][] listOfItems();

    Object[][] listOfCustomers();

    Object[][] listOfOrders();

    boolean addItem(String name, String cost, String number);

    boolean addCustomer(String fName, String sName, String age, int gender_id, String address, String phoneNumber);

    List<String> listOfItemsName();

    List<String> listOfCustomersName();

    void makeOrder(int idItem, int idCustomer, String itemNumber);

    void editItem(int itemId, int boxColumnIdx, String columnName, String data);

    void editCustomer(int customerId, int boxColumnIdx, String column, String data);

    void setManagementSystem(DBManagementSystem dbManagementSystem);
}
