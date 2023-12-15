package ru.vsu.cs.sapegin.servlet.departments;

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

@WebServlet("/dep/create")
public class DepartmentAddServlet extends HttpServlet {

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
        req.getRequestDispatcher("/dep-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            departmentService.addDepartment(new DepartmentItem(
                                                -1,
                                                req.getParameter("name"),
                                                Time.valueOf(req.getParameter("open_time")),
                                                Time.valueOf(req.getParameter("close_time"))
                                            ));
            resp.sendRedirect("http://localhost:8070/departments");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
