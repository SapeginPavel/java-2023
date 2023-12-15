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
import java.sql.Time;

@WebServlet("/dep/edit/*")
public class DepartmentsEditServlet extends HttpServlet {

    DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        try {
            departmentService = Starter.applicationContext.getBean(DepartmentService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        DepartmentItem departmentItem = departmentService.getById(id);
        req.setAttribute("dep", departmentItem);
        req.getRequestDispatcher("/edit-dep.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getPathInfo().substring(1));
//        System.out.println(req.getParameter("name"));
        String name = req.getParameter("name");
        Time open = Time.valueOf(req.getParameter("open_time"));
        Time close = Time.valueOf(req.getParameter("close_time"));
        try {
            departmentService.updateDepartment(id, new DepartmentItem(-1, name, open, close));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("http://localhost:8070/departments/" + id);
    }
}
