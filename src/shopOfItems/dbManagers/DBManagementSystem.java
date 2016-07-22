package shopOfItems.dbManagers;

import java.sql.Connection;
import java.util.List;

public interface DBManagementSystem {

    void connectionToDB();

    void createDBData(String autoIncrement);

    void closeConnection(Connection connection);

    Object[][] listOfItems();

    Object[][] listOfCustomers();

    Object[][] listOfOrders();

    void addItem(String name, int cost, int number);

    void addCustomer(String firstName, String secondName, int age, int gender_id, String address, String phoneNumber);

    List<String> listOfItemsName();

    List<String> listOfCustomersName();

    void buyItem(int idItem, int idCustomer, int number);

    boolean checkNumberOfItem(int idItem, int number);

    void editItem(int itemId, String column, String stringData, int intData);

    void editCustomer(int customerId, String column, String stringData, int intData);

    Connection getConnection();
}
