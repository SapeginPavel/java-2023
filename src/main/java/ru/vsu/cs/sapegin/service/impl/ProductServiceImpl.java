package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.reps.ProductRepository;
import ru.vsu.cs.sapegin.service.ProductService;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository = new ProductRepository();

    @Override
    public ProductItem getById(int id) {
        return productRepository.findById(id);
    }
}
