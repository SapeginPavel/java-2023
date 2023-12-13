package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.item.ProductItem;
import ru.vsu.cs.sapegin.repository.MainRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MainRepository<ProductItem, Integer> productRepository = new MainRepository<>(ProductItem.class);
        ProductItem productItem = productRepository.findById(1);
        System.out.println(productItem);
    }
}
