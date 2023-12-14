package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentRepository;
import ru.vsu.cs.sapegin.service.DepartmentService;

import java.util.List;

@Bean
public class DepartmentServiceImpl implements DepartmentService {

    @Inject
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentItem getById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<DepartmentItem> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentItem addDepartment(DepartmentItem addedDepItem) throws Exception {
        return departmentRepository.addItem(addedDepItem);
    }

    @Override
    public DepartmentItem updateDepartment(int id, DepartmentItem updatedDepItem) throws IllegalAccessException {
        return departmentRepository.updateItem(id, updatedDepItem);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

}
