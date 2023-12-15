package ru.vsu.cs.sapegin.servlet.departments;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.service.DepartmentProductService;
import ru.vsu.cs.sapegin.service.DepartmentService;
import ru.vsu.cs.sapegin.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/departments/*")
public class DepartmentsServlet extends HttpServlet {

    DepartmentService departmentService;
    DepartmentProductService departmentProductService;
    ProductService productService;

    @Override
    public void init() throws ServletException {
        try {
            departmentService = Starter.applicationContext.getBean(DepartmentService.class);
            departmentProductService = Starter.applicationContext.getBean(DepartmentProductService.class);
            productService = Starter.applicationContext.getBean(ProductService.class);
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
        } else { //todo: тут очень плохо добываются продукты для отделов. Но пока не очень понимаю, как переписать, чтобы это было логично и грамотно
            int indexOfSlash = pathInfo.indexOf('/', 1);
            int id = indexOfSlash > 0 ? Integer.parseInt(pathInfo.substring(1, indexOfSlash)) : Integer.parseInt(pathInfo.substring(1));
            DepartmentItem departmentItem = departmentService.getById(id);
            if (indexOfSlash > 0) {
                if (pathInfo.trim().substring(indexOfSlash + 1).equals("edit")) {
                    resp.sendRedirect("/dep/edit/" + id);
                }
            } else {
                List<DepartmentProductItem> prodIdForDep = departmentProductService.getProductsWithDepId(id);
                List<ProductItem> productItems = new CopyOnWriteArrayList<>();
                for (DepartmentProductItem dpi : prodIdForDep) {
                    productItems.add(productService.getById(dpi.getProdId()));
                }
                req.setAttribute("dep", departmentItem);
                req.setAttribute("prods", productItems);
                req.getRequestDispatcher("/dep.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
