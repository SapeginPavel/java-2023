package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;

@Bean
public class DepartmentRepository extends MainRepository<DepartmentItem, Integer> {

    public DepartmentRepository() throws Exception {
        super(DepartmentItem.class);
    }
}
