package ru.vsu.cs.sapegin.listener;

import ru.vsu.cs.sapegin.App;
import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.ApplicationContext;
import ru.vsu.cs.sapegin.dependencies.BeanFactory;
import ru.vsu.cs.sapegin.dependencies.Utils;
import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {



    @Inject
    ConnectionManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {



//        Starter starter = new Starter();
//        try {
//            starter.start();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    //todo: в другом классе делать всю эту установку зависимостей


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connectionManager.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
