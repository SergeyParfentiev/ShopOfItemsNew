package shopOfItems.dbManagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Derby extends AbstractManagementSystem{

    private final String autoIncrement = "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)";


    @Override
    public void connectionToDB() {
        Path path = Paths.get("shopOfItemsDB");

        if(Files.notExists(path)) {
            System.out.println("Not exist");
            createDBData(autoIncrement);
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        inputStream = getClass().getClassLoader().getResourceAsStream("shopOfItems/properties/ConnectionDerbyDB");
        try {
            properties.load(inputStream);

            Class.forName(properties.getProperty("classInstance")).newInstance();

            String URL = properties.getProperty("URL");
            String userName = properties.getProperty("UserName");
            String password = properties.getProperty("Password");

            connection = DriverManager.getConnection(URL, userName, password);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IOException  e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            DriverManager.getConnection("jdbc:derby:shopOfItemsDB;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("08006") ) {
                System.out.println("Database shut down normally");
            } else {
                e.printStackTrace();
            }
        }
    }
}
