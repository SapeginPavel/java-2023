package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentItem;

public interface DepartmentService {
    DepartmentItem getById(int id);
}
