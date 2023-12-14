package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentProductRepository;
import ru.vsu.cs.sapegin.service.DepartmentProductService;

import java.sql.SQLException;
import java.util.List;

@Bean
public class DepartmentProductServiceImpl implements DepartmentProductService {

    @Inject
    DepartmentProductRepository departmentProductRepository;

    @Override
    public DepartmentProductItem getById(int id) {
        return departmentProductRepository.findById(id);
    }

    @Override
    public List<DepartmentProductItem> getAll() {
        return departmentProductRepository.findAll();
    }

    @Override
    public DepartmentProductItem addDepartmentProduct(DepartmentProductItem addedDepProdItem) throws Exception {
        return departmentProductRepository.addItem(addedDepProdItem);
    }

    @Override
    public DepartmentProductItem updateDepartmentProduct(int id, DepartmentProductItem updatedDepProdItem) throws IllegalAccessException {
        return departmentProductRepository.updateItem(id, updatedDepProdItem);
    }

    @Override
    public void deleteDepartmentProduct(int id) {
        departmentProductRepository.deleteById(id);
    }
}
