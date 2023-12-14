package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentItem;

public class DepartmentRepository extends MainRepository<DepartmentItem, Integer> {
    public DepartmentRepository() {
        super(DepartmentItem.class);
    }
}
