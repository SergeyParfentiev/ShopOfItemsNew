package shopOfItems.dataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

    private static DataSource datasource = null;
    private ComboPooledDataSource cpds = null;
    private Connection connection;
    private Properties properties;
    private InputStream inputStream;

    private int maxSize = 10;

    private DataSource(String propertiesFileName) throws IOException, SQLException, PropertyVetoException {
        properties = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        System.out.println(propertiesFileName);
        properties.load(inputStream);

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(properties.getProperty("classInstance"));
        cpds.setJdbcUrl(properties.getProperty("URL"));
        cpds.setUser(properties.getProperty("UserName"));
        cpds.setPassword(properties.getProperty("Password"));

        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(maxSize);
        cpds.setMaxStatements(20);
    }

    public static DataSource getInstance(String propertiesFileName) throws IOException, SQLException, PropertyVetoException {

        if (datasource == null){
        datasource = new DataSource(propertiesFileName);
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection()  {
        try {
            if(cpds.getNumBusyConnections() == maxSize){
                cpds.softResetAllUsers();
            }
            connection = cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
