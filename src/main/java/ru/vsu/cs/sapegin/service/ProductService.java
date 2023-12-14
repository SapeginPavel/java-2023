package ru.vsu.cs.sapegin.service;


import ru.vsu.cs.sapegin.repository.item.ProductItem;

import java.util.List;

public interface ProductService {
    ProductItem getById(int id);
    List<ProductItem> getAll();
    ProductItem addProduct(ProductItem addedProductItem) throws Exception;
    ProductItem updateProduct(int id, ProductItem updatedProductItem) throws IllegalAccessException;
    void deleteProduct(int id);
}
