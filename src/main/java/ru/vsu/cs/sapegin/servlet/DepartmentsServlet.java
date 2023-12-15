package ru.vsu.cs.sapegin.servlet;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/departments")
public class DepartmentsServlet extends HttpServlet {

    DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        System.out.println("Зашли в инит сервлета");
        try {
            departmentService = Starter.applicationContext.getBean(DepartmentService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        System.out.println("Путь: " + pathInfo);
        if (pathInfo == null) {
            List<DepartmentItem> departments = departmentService.getAll();
            req.setAttribute("deps", departments);
            req.getRequestDispatcher("/departments.jsp").forward(req, resp);
        } else {
//            int id = Integer.parseInt(pathInfo);
            int id = Integer.parseInt(pathInfo.substring(1));
            DepartmentItem departmentItem = departmentService.getById(id);
            req.setAttribute("dep", departmentItem);
            req.getRequestDispatcher("/dep.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
