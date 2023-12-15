package ru.vsu.cs.sapegin.listener;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Starter starter = new Starter();
        applicationContext = Starter.applicationContext;
        try {
            starter.initializeAll();
        } catch (Exception e) {
            try {
                throw new Exception("Не удалось проинициализировать бины");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionManager connectionManager = applicationContext.getBean(ConnectionManager.class);
            connectionManager.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
