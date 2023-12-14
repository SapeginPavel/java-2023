package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.service.impl.DepartmentProductServiceImpl;
import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;
import ru.vsu.cs.sapegin.service.impl.ProductServiceImpl;

@NotSingleton
@Component
public class Testing {

//    DepartmentServiceImpl depService = new DepartmentServiceImpl();

    @Inject
    DepartmentServiceImpl depService;

    @Inject
    ProductServiceImpl productService;

    @Inject
    DepartmentProductServiceImpl departmentProductService;

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
}
