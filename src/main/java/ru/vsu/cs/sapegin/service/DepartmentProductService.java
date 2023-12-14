package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;

public interface DepartmentProductService {
    DepartmentProductItem getById(int id);
}
