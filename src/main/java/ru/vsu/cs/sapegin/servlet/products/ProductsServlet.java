package ru.vsu.cs.sapegin.servlet.products;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/products/*")
public class ProductsServlet extends HttpServlet {

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
        if (pathInfo == null) {
            List<ProductItem> products = productService.getAll();
            req.setAttribute("prods", products);
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } else {
            int indexOfSlash = pathInfo.indexOf('/', 1);
            int id = indexOfSlash > 0 ? Integer.parseInt(pathInfo.substring(1, indexOfSlash)) : Integer.parseInt(pathInfo.substring(1));
            ProductItem departmentItem = productService.getById(id);
            if (indexOfSlash > 0) {
                if (pathInfo.trim().substring(indexOfSlash + 1).equals("edit")) {
                    resp.sendRedirect("/prod/edit/" + id);
                }
            } else {
                req.setAttribute("prod", departmentItem);
                req.getRequestDispatcher("/prod.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
