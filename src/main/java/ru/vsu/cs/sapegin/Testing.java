package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.service.impl.DepartmentServiceImpl;

public class Testing {

    public void testGettingOfData() {
        DepartmentServiceImpl depService = new DepartmentServiceImpl();
        System.out.println(depService.getById(1));
        System.out.println(depService.getById(2));
    }
}
