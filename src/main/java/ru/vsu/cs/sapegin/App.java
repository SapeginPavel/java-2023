package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.service.ProductServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
//        MainRepository<ProductItem, Integer> productRepository = new MainRepository<>(ProductItem.class);
//        ProductItem productItem = productRepository.findById(2);
//        System.out.println(productItem);

        ProductServiceImpl prodService = new ProductServiceImpl();
        System.out.println(prodService.getById(2));
    }
}
