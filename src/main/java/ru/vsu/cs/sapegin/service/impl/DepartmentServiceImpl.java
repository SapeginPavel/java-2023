package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentRepository;
import ru.vsu.cs.sapegin.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository = new DepartmentRepository();

    @Override
    public DepartmentItem getById(int id) {
        return departmentRepository.findById(id);
    }
}
