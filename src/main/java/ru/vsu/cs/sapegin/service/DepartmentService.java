package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentItem;

import java.util.List;

public interface DepartmentService {
    DepartmentItem getById(int id);
    List<DepartmentItem> getAll();
    DepartmentItem addDepartment(DepartmentItem addedDepItem) throws Exception;
    DepartmentItem updateDepartment(int id, DepartmentItem updatedDepItem) throws IllegalAccessException;
    void deleteDepartment(int id);
}
