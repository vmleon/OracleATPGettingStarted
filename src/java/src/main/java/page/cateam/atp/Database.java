package page.cateam.atp;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

    public void connect() throws SQLException {
        OracleDataSource ods = getDataSource();

        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
            printConnectionDetails(connection);
            printDBVersion(connection);
        }
    }

    private OracleDataSource getDataSource() throws SQLException {
        Properties config = getConfig();

        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, config.getProperty("db.user"));
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, config.getProperty("db.password"));

        OracleDataSource ods = new OracleDataSource();
        ods.setURL(config.getProperty("db.url"));
        ods.setConnectionProperties(info);
        return ods;
    }

    private void printConnectionDetails(OracleConnection connection) throws SQLException {
        DatabaseMetaData dbmd = connection.getMetaData();
        System.out.println("Driver Name: " + dbmd.getDriverName());
        System.out.println("Driver Version: " + dbmd.getDriverVersion());
        System.out.println("Database Username is: " + connection.getUserName());
    }

    private void printDBVersion(OracleConnection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement
                    .executeQuery("SELECT banner FROM v$version")) {
                while (resultSet.next())
                    System.out.println(String.format("Database: %s", resultSet.getString(1)));
            }
        }
    }

    private Properties getConfig() {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties");
                return null;
            }
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
