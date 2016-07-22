package shopOfItems.dbManagers;

import java.sql.*;

public class CreateData {

    private ConvertDate convertDate;

    private final String itemTable;
    private final String customerTable;
    private final String customerGenderTable;
    private final String customerContactsTable;
    private final String oderTable;

    private final String insertCustomerMale = "INSERT INTO customerGender VALUES (1, 'Male')";
    private final String insertCustomerFemale = "INSERT INTO customerGender VALUES (2, 'Female')";

    private final String fillItem = "INSERT INTO item(name, cost, number) VALUES (?, ?, ?)";
    private final String fillCustomer = "INSERT INTO customer(firstName, secondName, age, gender_id) VALUES (?, ?, ?, ?)";
    private final String fillCustomerContacts = "INSERT INTO customerContacts(address, phoneNumber) VALUES (?, ?)";
    private final String fillOder = "INSERT INTO sales(item_id, customer_id, cost, sold, totalCost, date) VALUES (?, ?, ?, ?, ?, ?)";

    private PreparedStatement preparedStatement;

    public CreateData(String autoIncrement) {
        convertDate = new ConvertDate();

        itemTable = "CREATE TABLE item(id INT " + autoIncrement + ", name VARCHAR(30) NOT NULL, " +
                "cost INT, number INT, PRIMARY KEY (id))";

        customerGenderTable = "CREATE TABLE customerGender(id INT, gender VARCHAR(10) NOT NULL, " +
                "PRIMARY KEY (id))";

        customerTable = "CREATE TABLE customer(id INT " + autoIncrement + ", firstName VARCHAR(30) NOT NULL, secondName VARCHAR(30) NOT NULL," +
                " age INT NOT NULL, gender_id INT, PRIMARY KEY (id), FOREIGN KEY (gender_id) REFERENCES customerGender (id))";

        customerContactsTable = "CREATE TABLE customerContacts(id INT " + autoIncrement + ", address VARCHAR(40) NOT NULL, " +
                "phoneNumber VARCHAR(20) NOT NULL, PRIMARY KEY (id), FOREIGN KEY (id) REFERENCES customer (id))";

        oderTable = "CREATE TABLE sales(id INT " + autoIncrement + ", item_id INT NOT NULL, customer_id INT NOT NULL, " +
                "PRIMARY KEY (id), cost INT NOT NULL, sold INT NOT NULL, totalCost INT NOT NULL, date DATE NOT NULL, " +
                "FOREIGN KEY (item_id) REFERENCES item (id), FOREIGN KEY (customer_id) REFERENCES customer (id))";
    }

    public void createAndFill(Connection connection) {
        try {
            createCustomerTables(connection);
            createItemTable(connection);
            createOderTable(connection);

            fillGander(connection);
            fillItems(connection);
            fillCustomers(connection);
            fillOder(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCustomerTables(Connection connection) throws SQLException {
        preparedStatement = connection.prepareStatement(customerGenderTable);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(customerTable);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(customerContactsTable);
        preparedStatement.execute();
    }

    private void fillGander(Connection connection) throws SQLException{
        preparedStatement = connection.prepareStatement(insertCustomerMale);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(insertCustomerFemale);
        preparedStatement.execute();
    }
    private void createItemTable(Connection connection) throws SQLException{
        preparedStatement = connection.prepareStatement(itemTable);
        preparedStatement.execute();
    }

    private void createOderTable(Connection connection) throws SQLException{
        preparedStatement = connection.prepareStatement(oderTable);
        preparedStatement.execute();
    }

    private void fillItems(Connection connection) throws SQLException{
        preparedStatement = connection.prepareStatement(fillItem);
        preparedStatement.setString(1, "Duck");
        preparedStatement.setInt(2, 10);
        preparedStatement.setInt(3, 12);
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillItem);
        preparedStatement.setString(1, "Camel");
        preparedStatement.setInt(2, 50);
        preparedStatement.setInt(3, 5);
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillItem);
        preparedStatement.setString(1, "Sofa");
        preparedStatement.setInt(2, 20);
        preparedStatement.setInt(3, 8);
        preparedStatement.execute();
    }
    private void fillCustomers(Connection connection) throws SQLException{
        preparedStatement = connection.prepareStatement(fillCustomer);
        preparedStatement.setString(1, "Pasha");
        preparedStatement.setString(2, "Pavlov");
        preparedStatement.setInt(3, 30);
        preparedStatement.setInt(4, 1);
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillCustomerContacts);
        preparedStatement.setString(1, "Khreschatik st.");
        preparedStatement.setString(2, "(099)486-21-85");
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillCustomer);
        preparedStatement.setString(1, "Sveta");
        preparedStatement.setString(2, "Bukina");
        preparedStatement.setInt(3, 18);
        preparedStatement.setInt(4, 2);
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillCustomerContacts);
        preparedStatement.setString(1, "Andriyvsky Uzviz");
        preparedStatement.setString(2, "(067)992-54-81");
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillCustomer);
        preparedStatement.setString(1, "Gosha");
        preparedStatement.setString(2, "Kamushkin");
        preparedStatement.setInt(3, 25);
        preparedStatement.setInt(4, 1);
        preparedStatement.execute();

        preparedStatement = connection.prepareStatement(fillCustomerContacts);
        preparedStatement.setString(1, "Sahaidachnoho Petra St.");
        preparedStatement.setString(2, "(066)229-60-43");
        preparedStatement.execute();
    }

    private void fillOder(Connection connection) throws SQLException{
        String stringDate = "2012-09-30";
        Date date = convertDate.convert(stringDate);
        preparedStatement = connection.prepareStatement(fillOder);
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 1);
        preparedStatement.setInt(3, 10);
        preparedStatement.setInt(4, 3);
        preparedStatement.setInt(5 ,30);
        preparedStatement.setDate(6, date);
        preparedStatement.execute();

        stringDate = "2014-11-18";
        date = convertDate.convert(stringDate);
        preparedStatement = connection.prepareStatement(fillOder);
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 2);
        preparedStatement.setInt(3, 50);
        preparedStatement.setInt(4, 5);
        preparedStatement.setInt(5 ,250);
        preparedStatement.setDate(6, date);
        preparedStatement.execute();

        stringDate = "2000-01-04";
        date = convertDate.convert(stringDate);
        preparedStatement = connection.prepareStatement(fillOder);
        preparedStatement.setInt(1, 3);
        preparedStatement.setInt(2, 3);
        preparedStatement.setInt(3, 20);
        preparedStatement.setInt(4, 8);
        preparedStatement.setInt(5 ,160);
        preparedStatement.setDate(6, date);
        preparedStatement.execute();
    }
}
