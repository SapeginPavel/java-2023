package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.MainRepository;

import java.sql.SQLException;

@Bean
@Component
public class ProductRepository extends MainRepository<ProductItem, Integer> {
    public ProductRepository() throws SQLException {
        super(ProductItem.class);
    }
}
