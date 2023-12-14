package ru.vsu.cs.sapegin.service;


import ru.vsu.cs.sapegin.repository.item.ProductItem;

public interface ProductService {
    ProductItem getById(int id);
}
