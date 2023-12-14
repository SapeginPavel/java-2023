package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.ApplicationContext;

public class App {
    public static void main( String[] args ) throws Exception {
        Starter starter = new Starter();
        try {
            starter.initializeAll();
        } catch (Exception e) {
            throw new Exception("Не удалось проинициализировать бины");
        }




//        ApplicationContext ap = starter.getApplicationContext();
        ApplicationContext ap = Starter.applicationContext;

        Testing t1 = ap.getBean(Testing.class);
        t1.testGettingOfDep();
        t1.testGettingOfProd();
        t1.testGettingOfDepProd();
        System.out.println("---");
        t1.testGettingOfAllProducts();
        t1.testAddingNewProduct();




//        MainRepository<ProductItem, Integer> productRepository = new MainRepository<>(ProductItem.class);
//        ProductItem productItem = productRepository.findById(2);
//        System.out.println(productItem);

//        ProductServiceImpl prodService = new ProductServiceImpl();
//        System.out.println(prodService.getById(2));

//        DepartmentServiceImpl depService = new DepartmentServiceImpl();
//        System.out.println(depService.getById(1));
//        System.out.println(depService.getById(2));

//        DepartmentProductServiceImpl departmentProductService = new DepartmentProductServiceImpl();
//        System.out.println(departmentProductService.getById(1));
    }
}
