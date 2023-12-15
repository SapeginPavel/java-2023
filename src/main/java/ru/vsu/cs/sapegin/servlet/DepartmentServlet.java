package ru.vsu.cs.sapegin.servlet;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.service.DepartmentService;
import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {

    DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        System.out.println("Зашли в инит сервлета");
        try {
            departmentService = Starter.applicationContext.getBean(DepartmentService.class);
            System.out.println("Класс деп сёрвис: " + departmentService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        try {
//            System.out.println("Загружаем БД?");
//            Class.forName("org.h2.Driver");
//            System.out.println("Загрузили БД?");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
        System.out.println("Закончили метод init() и вызываем родительский");
        try {
            System.out.println("Соед: " + Starter.applicationContext.getBean(ConnectionManager.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DepartmentItem> departments = departmentService.getAll();
        req.setAttribute("deps", departments);
        req.getRequestDispatcher("/departments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
