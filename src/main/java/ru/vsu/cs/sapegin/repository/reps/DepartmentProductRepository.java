package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;

import java.sql.SQLException;

@Bean
public class DepartmentProductRepository extends MainRepository<DepartmentProductItem, Integer> {
    public DepartmentProductRepository() throws SQLException {
        super(DepartmentProductItem.class);
    }
}
