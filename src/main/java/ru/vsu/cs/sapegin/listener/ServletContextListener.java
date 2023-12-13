package ru.vsu.cs.sapegin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        javax.servlet.ServletContextListener.super.contextInitialized(sce);
    }
}
