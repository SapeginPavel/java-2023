package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.service.DepartmentProductService;
import ru.vsu.cs.sapegin.service.DepartmentService;
import ru.vsu.cs.sapegin.service.ProductService;
import ru.vsu.cs.sapegin.service.impl.DepartmentProductServiceImpl;
import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;
import ru.vsu.cs.sapegin.service.impl.ProductServiceImpl;

@NotSingleton
@Component
public class Testing {
    @Inject
    DepartmentService depService;

    @Inject
    ProductService productService;

    @Inject
    DepartmentProductService departmentProductService;

    public void testGettingOfDep() {
        System.out.println(depService.getById(1));
        System.out.println(depService.getById(2));
    }

    public void testGettingOfProd() {
        System.out.println(productService.getById(1));
        System.out.println(productService.getById(2));
    }

    public void testGettingOfDepProd() {
        System.out.println(departmentProductService.getById(1));
        System.out.println(departmentProductService.getById(2));
    }

    public void testGettingOfAllDepartments() {
//        System.out.println(depService.getAll());
    }
    public void testGettingOfAllProducts() {
        System.out.println(productService.getAll());
    }
    public void testGettingOfAllDepartmentsProducts() {
//        System.out.println(departmentProductService.getAll());
    }

    public void testAddingNewProduct() throws Exception {
        System.out.println(productService.addProduct(new ProductItem(-1, "ТЕСТОВОЕ", 501)));
        System.out.println(productService.addProduct(new ProductItem(-1, "ТЕСТОВОЕ2", 501)));
    }

    public void testUpdatingProduct() throws IllegalAccessException {
//        System.out.println(productService.updateProduct(2, new ProductItem(-1, "'; DROP TABLE PRODUCT; --", 501)));
        System.out.println(productService.updateProduct(2, new ProductItem(-1, "Новое название", 501)));
    }

    public void testDeleteProduct() {
        productService.deleteProduct(2);
        System.out.println("Удалили");
    }
}
