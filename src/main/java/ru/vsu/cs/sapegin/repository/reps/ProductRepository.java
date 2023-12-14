package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.MainRepository;

public class ProductRepository extends MainRepository<ProductItem, Integer> {
    public ProductRepository() {
        super(ProductItem.class);
    }
}
