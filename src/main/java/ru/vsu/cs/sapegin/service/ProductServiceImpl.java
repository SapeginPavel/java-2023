package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.reps.ProductRepository;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository = new ProductRepository();

    @Override
    public ProductItem getById(int id) {
        return productRepository.findById(id);
    }
}
