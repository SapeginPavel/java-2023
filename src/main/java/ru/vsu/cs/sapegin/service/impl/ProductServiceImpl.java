package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.reps.ProductRepository;
import ru.vsu.cs.sapegin.service.ProductService;

import java.sql.SQLException;

@Bean
public class ProductServiceImpl implements ProductService {

    @Inject
    ProductRepository productRepository;

    public ProductServiceImpl() throws SQLException {
    }

    @Override
    public ProductItem getById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductItem getAll() {
        return null;
    }

    @Override
    public ProductItem addProduct(ProductItem newProductItem) {
        return null;
    }

    @Override
    public ProductItem updateProduct(int id, ProductItem updatedProductItem) {
        return null;
    }

    @Override
    public ProductItem deleteProduct(int id) {
        return null;
    }
}
