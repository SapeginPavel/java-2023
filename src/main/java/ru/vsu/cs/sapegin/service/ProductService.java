package ru.vsu.cs.sapegin.service;


import ru.vsu.cs.sapegin.repository.item.ProductItem;

public interface ProductService {
    ProductItem getById(int id);
    ProductItem getAll();
    ProductItem addProduct(ProductItem newProductItem);
    ProductItem updateProduct(int id, ProductItem updatedProductItem);
    ProductItem deleteProduct(int id);
}
