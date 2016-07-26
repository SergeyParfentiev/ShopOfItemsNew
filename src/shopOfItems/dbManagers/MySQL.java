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
            dataSource = DataSource.getInstance();
            dataSource.createConnection("shopOfItems/properties/ConnectionMySQL");
            connection = getConnection();

            statement = connection.createStatement();
            statement.executeUpdate(createDB);

            dataSource.createConnection("shopOfItems/properties/ConnectionMySQLDB");
        } catch (IOException | SQLException | PropertyVetoException e) {
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
