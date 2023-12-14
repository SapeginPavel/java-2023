package ru.vsu.cs.sapegin.service.impl;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;
import ru.vsu.cs.sapegin.repository.reps.DepartmentRepository;
import ru.vsu.cs.sapegin.service.DepartmentService;

@Bean
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository = new DepartmentRepository();

//    @Inject
//    DepartmentRepository departmentRepository;

    @Override
    public DepartmentItem getById(int id) {
        return departmentRepository.findById(id);
    }
}
