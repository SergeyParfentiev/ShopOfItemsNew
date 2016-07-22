package shopOfItems.dbManagers;

import shopOfItems.dataSource.DataSource;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public abstract class AbstractManagementSystem implements DBManagementSystem {

    protected InputStream inputStream;
    protected Properties properties;
    protected DataSource dataSource;
    private CreateData createData;

    protected Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private int row;
    private Object[][] doubleArray;
    private Object[] array;

    int itemNumber;
    int itemCost;

    private final String selectAllItems = "SELECT * FROM item";

    private final String selectAllCustomers = "SELECT c.id, c.firstName, c.secondName, c.age, cg.gender, cc.address, cc.phoneNumber " +
            "FROM customer c NATURAL JOIN customerContacts cc INNER JOIN customerGender cg ON c.gender_id = cg.id";

    private final String selectAllSales = "SELECT s.id, i.name, s.cost, s.sold, s.totalCost, c.firstName, c.secondName, s.date FROM item i " +
            "INNER JOIN sales s ON i.id = s.item_id " +
            "INNER JOIN customer c ON c.id = s.customer_id";

    private final String selectItemName = "SELECT name FROM item";
    private final String addItem = "INSERT INTO item(name, cost, number) VALUES (?, ?, ?)";

    private final String selectCustomerName = "SELECT id, firstName, secondName FROM customer";

    private final String addCustomer = "INSERT INTO customer(firstName, secondName, age, gender_id) VALUES (?, ?, ?, ?)";
    private final String addCustomerContacts = "INSERT INTO customerContacts(address, phoneNumber) VALUES (?, ?)";

    private final String addOder = "INSERT INTO sales(item_id, customer_id, cost, sold, totalCost, date) VALUES (?, ?, ?, ?, ?, ?)";

    public AbstractManagementSystem(){
        properties = new Properties();

        connectionToDB();
    }

    @Override
    public void createDBData(String autoIncrement) {
        connection = getConnection();

        createData = new CreateData(autoIncrement);
        createData.createAndFill(connection);
        closeConnection(connection);
    }

    @Override
    public Object[][] listOfItems() {
        return getListAllFromTable(selectAllItems, "selectAllItems");
    }

    @Override
    public Object[][] listOfCustomers() {
        return getListAllFromTable(selectAllCustomers, "selectAllCustomers");
    }

    @Override
    public Object[][] listOfOrders() {
        return getListAllFromTable(selectAllSales, "selectAllSales");
    }

    private Object[][] getListAllFromTable(String command, String name) {
        int id;
        connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(command);
            row = 0;
            while (resultSet.next()) {
                row++;
            }

            id = row;
            doubleArray = new Object[row][];
            row = 0;
            switch (name) {
                case "selectAllItems":
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(command);

                    while (resultSet.next()) {
                        array = new Object[]{
                                resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)
                        };
                        doubleArray[row] = array;
                        row++;
                    }
                    break;
                case "selectAllCustomers":
                    for (int i = 1; i <= id; i++) {
                        final String selectAllCustomersWithId = "SELECT c.id, c.firstName, c.secondName, c.age, cg.gender, cc.address, cc.phoneNumber " +
                                "FROM customer c NATURAL JOIN customerContacts cc " +
                                "INNER JOIN customerGender cg ON c.gender_id = cg.id WHERE c.id = " + (i);

                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(selectAllCustomersWithId);
                        while (resultSet.next()) {
                            array = new Object[]{
                                    resultSet.getInt(1), resultSet.getString(2) + " " + resultSet.getString(3), resultSet.getInt(4),
                                    resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
                            };
                            doubleArray[row] = array;
                            row++;
                        }
                    }
                    break;
                case "selectAllSales":
                    for (int i = 1; i <= id; i++) {
                    String selectAllSalesWithId = "SELECT s.id, i.name, s.cost, s.sold, s.totalCost, c.firstName, c.secondName, s.date FROM sales s " +
                            "INNER JOIN item i ON i.id = s.item_id " +
                            "INNER JOIN customer c ON c.id = s.customer_id WHERE s.id = " + (i);

                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(selectAllSalesWithId);
                    while (resultSet.next()) {
                        array = new Object[]{
                                resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
                                resultSet.getInt(5), resultSet.getString(6) + " " + resultSet.getString(7), resultSet.getDate(8)
                        };
                        doubleArray[row] = array;
                        row++;
                        }
                    }
                break;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return doubleArray;
    }

    @Override
    public void addItem(String name, int cost, int number) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(addItem);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, cost);
            preparedStatement.setInt(3, number);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addCustomer(String firstName, String secondName, int age, int gender_id, String address, String phoneNumber) {
        connection = getConnection();
        try {

            preparedStatement = connection.prepareStatement(addCustomer);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, gender_id);
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(addCustomerContacts);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<String> listOfItemsName() {
        List<String> listOfItemsName = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection.createStatement();
            statement.executeQuery(selectItemName);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                listOfItemsName.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return listOfItemsName;
    }

    @Override
    public List<String> listOfCustomersName() {
        List<String> listOfCustomersName = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection.createStatement();
            statement.executeQuery(selectCustomerName);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                listOfCustomersName.add(resultSet.getString("firstName") + " " + resultSet.getString("secondName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return listOfCustomersName;
    }

    @Override
    public boolean checkNumberOfItem(int idItem, int number) {
        boolean result = false;

        String selectItemCost = "SELECT cost, number FROM item WHERE id = " + idItem;

        connection = getConnection();
        try {
            statement = connection.createStatement();
            statement.executeQuery(selectItemCost);
            resultSet = statement.getResultSet();

            resultSet.next();
            if((itemNumber = resultSet.getInt("number")) < number) {
                result = false;
            } else {
                itemCost = resultSet.getInt("cost");
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public void buyItem(int idItem, int idCustomer, int number) {
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        connection = getConnection();
        try {
            String changeItemNumber = "UPDATE item SET number = " + (itemNumber - number) + " WHERE id = " + idItem;
            statement = connection.createStatement();
            statement.execute(changeItemNumber);

            preparedStatement = connection.prepareStatement(addOder);
            preparedStatement.setInt(1, idItem);
            preparedStatement.setInt(2, idCustomer);
            preparedStatement.setInt(3, itemCost);
            preparedStatement.setInt(4, number);
            preparedStatement.setInt(5, (itemCost * number));
            preparedStatement.setDate(6, date);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void editItem(int itemId, String column, String stringData, int intData) {
        String tableName = "item";

        edit(tableName, itemId, column, stringData, intData);
    }

    @Override
    public void editCustomer(int customerId, String column, String stringData, int intData) {
        String tableName;
        if("address".toUpperCase().equals(column.toUpperCase()) || "phoneNumber".toUpperCase().equals(column.replaceAll(" ", "").toUpperCase())) {
            tableName = "customerContacts";
        } else {
            tableName = "customer";
        }
        edit(tableName, customerId, column.replaceAll(" ", "").toUpperCase(), stringData, intData);
    }

    private void edit(String tableName, int id, String column, String stringData, int intData) {
        String editData = "UPDATE " + tableName + " SET " + column + " = ? WHERE id = ?";
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(editData);
            if(stringData.equals("")) {
                preparedStatement.setInt(1, intData);
            } else {
                preparedStatement.setString(1, stringData);
            }

            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
