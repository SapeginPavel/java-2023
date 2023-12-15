package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.MainRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Bean
public class ProductRepository extends MainRepository<ProductItem, Integer> {
    public ProductRepository() throws Exception {
        super(ProductItem.class);
    }

}
