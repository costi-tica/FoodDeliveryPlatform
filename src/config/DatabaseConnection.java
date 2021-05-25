package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {

    }

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/delivery_platform";
            String username = "deliveryPlatformAdmin";
            String password = "delpltadm";
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
