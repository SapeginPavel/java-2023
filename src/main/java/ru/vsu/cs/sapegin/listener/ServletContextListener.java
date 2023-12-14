package ru.vsu.cs.sapegin.listener;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Inject
    ConnectionManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Starter starter = new Starter();
        try {
            starter.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connectionManager.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
