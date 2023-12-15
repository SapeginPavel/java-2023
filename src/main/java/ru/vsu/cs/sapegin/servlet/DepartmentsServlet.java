package ru.vsu.cs.sapegin.servlet;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.service.DepartmentProductService;
import ru.vsu.cs.sapegin.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments/*")
public class DepartmentsServlet extends HttpServlet {

    DepartmentService departmentService;
    DepartmentProductService departmentProductService;

    @Override
    public void init() throws ServletException {
        try {
            departmentService = Starter.applicationContext.getBean(DepartmentService.class);
            departmentProductService = Starter.applicationContext.getBean(DepartmentProductService.class);
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
        if (pathInfo == null) {
            List<DepartmentItem> departments = departmentService.getAll();
            req.setAttribute("deps", departments);
            req.getRequestDispatcher("/departments.jsp").forward(req, resp);
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            DepartmentItem departmentItem = departmentService.getById(id);
            List<DepartmentProductItem> prodIdForDep = departmentProductService.getProductsWithDepId(id);
            System.out.println(prodIdForDep);
            req.setAttribute("dep", departmentItem);
            req.getRequestDispatcher("/dep.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
