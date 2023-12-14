package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.reps.ProductRepository;
import ru.vsu.cs.sapegin.service.ProductService;

import java.sql.SQLException;
import java.util.List;

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
    public List<ProductItem> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductItem addProduct(ProductItem newProductItem) throws Exception {
        return productRepository.addItem(newProductItem);
    }

    @Override
    public ProductItem updateProduct(int id, ProductItem updatedProductItem) throws IllegalAccessException {
        return productRepository.updateItem(id, updatedProductItem);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
