package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;

public class DepartmentProductRepository extends MainRepository<DepartmentProductItem, Integer> {
    public DepartmentProductRepository() {
        super(DepartmentProductItem.class);
    }
}
