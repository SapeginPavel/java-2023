package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentProductRepository;

public class DepartmentProductServiceImpl implements DepartmentProductService {

    DepartmentProductRepository departmentProductRepository = new DepartmentProductRepository();

    @Override
    public DepartmentProductItem getById(int id) {
        return departmentProductRepository.findById(id);
    }
}
