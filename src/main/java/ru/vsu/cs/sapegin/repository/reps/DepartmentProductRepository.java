package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;

@Bean
public class DepartmentProductRepository extends MainRepository<DepartmentProductItem, Integer> {

    public DepartmentProductRepository() throws Exception {
        super(DepartmentProductItem.class);
    }
}
