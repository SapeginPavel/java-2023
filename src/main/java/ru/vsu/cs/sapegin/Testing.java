package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.service.DepartmentProductService;
import ru.vsu.cs.sapegin.service.DepartmentService;
import ru.vsu.cs.sapegin.service.ProductService;
import ru.vsu.cs.sapegin.service.impl.DepartmentProductServiceImpl;
import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;
import ru.vsu.cs.sapegin.service.impl.ProductServiceImpl;

import java.sql.Time;

@NotSingleton
@Component
public class Testing {
    @Inject
    DepartmentService depService;

    @Inject
    ProductService productService;

    @Inject
    DepartmentProductService departmentProductService;

    public void testGetting() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--GETTING--");
        System.out.println("Departments:");
        System.out.println(depService.getById(1));
        System.out.println(depService.getById(3));
        System.out.println("--");
        System.out.println("Products:");
        System.out.println(productService.getById(1));
        System.out.println(productService.getById(3));
        System.out.println("--");
        System.out.println("DepProd:");
        System.out.println(departmentProductService.getById(1));
        System.out.println(departmentProductService.getById(2));
        System.out.println("-----------");
    }

    public void testAdding() throws Exception {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--ADDING--");
        System.out.println("Departments:");
        System.out.println(depService.addDepartment(new DepartmentItem(-1, "NEW", new Time(80000L), new Time(85000L))));
        System.out.println("--");
        System.out.println("Products:");
        System.out.println(productService.addProduct(new ProductItem(-1, "ТЕСТОВОЕ_ИМЯ", 501)));
        System.out.println("--");
        System.out.println("DepProd:");
        System.out.println(departmentProductService.addDepartmentProduct(new DepartmentProductItem(-1, 2, 3)));
        System.out.println("-----------");
    }

    public void testUpdating() throws IllegalAccessException {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--UPDATING--");
        System.out.println("Departments:");
        System.out.println(depService.updateDepartment(1, new DepartmentItem(-1, "NEW", new Time(100_000L), new Time(200_000L))));
        System.out.println("--");
        System.out.println("Products:");
        System.out.println(productService.updateProduct(1, new ProductItem(-1, "ОБНОвлённое_ИМЯ", 333)));
        System.out.println("--");
        System.out.println("DepProd:");
        System.out.println(departmentProductService.updateDepartmentProduct(1, new DepartmentProductItem(-1, 2, 3)));
        System.out.println("-----------");
    }

    public void testDeleting() throws IllegalAccessException {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--DELETING--");
        System.out.println("Departments:");
        depService.deleteDepartment(3);
        System.out.println("--");
        System.out.println("Products:");
        productService.deleteProduct(36);
        System.out.println("--");
        System.out.println("DepProd:");
        departmentProductService.deleteDepartmentProduct(3);
        System.out.println("-----------");
    }

    public void testGetAll() throws IllegalAccessException {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--GetAll--");
        System.out.println("Departments:");
        System.out.println(depService.getAll());
        System.out.println("--");
        System.out.println("Products:");
        System.out.println(productService.getAll());
        System.out.println("--");
        System.out.println("DepProd:");
        System.out.println(departmentProductService.getAll());
        System.out.println("-----------");
    }
}
