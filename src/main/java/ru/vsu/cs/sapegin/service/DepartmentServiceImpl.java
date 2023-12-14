package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentRepository;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository = new DepartmentRepository();

    @Override
    public DepartmentItem getById(int id) {
        return departmentRepository.findById(id);
    }
}
