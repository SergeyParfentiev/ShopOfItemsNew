package shopOfItems.dbManagers;

import shopOfItems.dataSource.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class MySQL extends AbstractManagementSystem {

    private static String autoIncrement = "AUTO_INCREMENT";

    private final String createDB = "CREATE DATABASE IF NOT EXISTS shopOfItems";

    Connection connection;
    Statement statement;
    
    @Override
    public void connectionToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

            statement = connection.createStatement();
            statement.executeUpdate(createDB);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            dataSource = DataSource.getInstance("shopOfItems/properties/ConnectionMySQLDB");
        } catch (IOException | PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }

        createDBData(autoIncrement);
    }

    @Override
    public Connection getConnection() {
        return dataSource.getConnection();
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            connection.close();
            if(connection.isClosed()) {
                System.out.println("Database shut down normally");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
