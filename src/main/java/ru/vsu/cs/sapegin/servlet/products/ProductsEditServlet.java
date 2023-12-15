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

@WebServlet("/prod/edit/*")
public class ProductsEditServlet extends HttpServlet {

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
        String pathInfo = req.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        ProductItem productItem = productService.getById(id);
        req.setAttribute("prod", productItem);
        req.getRequestDispatcher("/prod-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        if (req.getParameter("delete") != null) {
            productService.deleteProduct(id);
            resp.sendRedirect("http://localhost:8070/products");
        } else {
            try {
                productService.updateProduct(id, getProductItemFromReq(req));
                resp.sendRedirect("http://localhost:8070/products/" + id);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ProductItem getProductItemFromReq(HttpServletRequest req) {
        return new ProductItem(
                    0,
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("cost"))
                );
    }
}
