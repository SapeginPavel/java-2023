package ru.vsu.cs.sapegin.servlet.products;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

@WebServlet("/prod/add")
public class ProductAddServlet extends HttpServlet {

    ProductService productService;

    @Override
    public void init() throws ServletException {
        try {
            productService = Starter.applicationContext.getBean(ProductService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/prod-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            productService.addProduct(new ProductItem(
                    -1,
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("cost"))
            ));
            resp.sendRedirect("http://localhost:8070/products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
