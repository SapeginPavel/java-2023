package ru.vsu.cs.sapegin.servlet;

import ru.vsu.cs.sapegin.dependencies.ApplicationContext;
import ru.vsu.cs.sapegin.dependencies.BeanFactory;
import ru.vsu.cs.sapegin.item.ProductItem;
import ru.vsu.cs.sapegin.repository.MainRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        pw.println("<html>");
        pw.println("<h1>Hello</h1>");
        pw.println("</html>");

        System.out.println("Привет начало");

//        Integer g = 5;
//        System.out.println(g);
        MainRepository<ProductItem, Integer> productRepository = new MainRepository<>(ProductItem.class);
//        System.out.println("Создали Мэйн репозитори");
//        ProductItem productItem = productRepository.findById(1);
//        System.out.println(productItem);


        System.out.println("Привет конец!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
