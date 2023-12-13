package ru.vsu.cs.sapegin.db;

import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Singleton
public class ConnectionManager {
    public static final String DB_URL = "jdbc:h2:~/shop";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWORD = "";

    Connection connection;

    public ConnectionManager() {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        ) {
            this.connection = connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
