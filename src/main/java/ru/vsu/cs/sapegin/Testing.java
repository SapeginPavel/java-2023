package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;
import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;

@NotSingleton
@Component
public class Testing {

//    DepartmentServiceImpl depService = new DepartmentServiceImpl();

    @Inject
    DepartmentServiceImpl depService;
    public void testGettingOfData() {
        System.out.println(depService.getById(1));
        System.out.println(depService.getById(2));
    }
}
