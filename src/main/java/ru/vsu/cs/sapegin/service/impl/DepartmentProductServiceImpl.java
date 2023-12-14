package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentProductRepository;
import ru.vsu.cs.sapegin.service.DepartmentProductService;

import java.sql.SQLException;

@Component
@Bean
public class DepartmentProductServiceImpl implements DepartmentProductService {

    @Inject
    DepartmentProductRepository departmentProductRepository;

    public DepartmentProductServiceImpl() throws SQLException {
    }

    @Override
    public DepartmentProductItem getById(int id) {
        return departmentProductRepository.findById(id);
    }
}
