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
        System.out.println("Прошла инициализация");
//        try {
//            applicationContext.getBean(ConnectionManager.class);
////            System.out.println("Подключение внутри стартера: " + applicationContext.getBean(ConnectionManager.class));
//        } catch (Exception e) {
//            throw new RuntimeException("Ошибка при выводе бина ConnectionManager");
//        }
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
