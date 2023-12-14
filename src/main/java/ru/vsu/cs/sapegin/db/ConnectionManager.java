package ru.vsu.cs.sapegin.db;

import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@NotSingleton
public class ConnectionManager {
    public static final String DB_URL = "jdbc:h2:~/shop";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWORD = "";

    Connection connection;

    public void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
    public Connection getConnection() {
        return connection;
    }
}
