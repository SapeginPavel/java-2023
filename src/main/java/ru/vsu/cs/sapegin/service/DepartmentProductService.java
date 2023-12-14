package ru.vsu.cs.sapegin.service;

import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;

import java.util.List;

public interface DepartmentProductService {
    DepartmentProductItem getById(int id);
    List<DepartmentProductItem> getAll();
    DepartmentProductItem addDepartmentProduct(DepartmentProductItem addedDepProdItem) throws Exception;
    DepartmentProductItem updateDepartmentProduct(int id, DepartmentProductItem updatedDepProdItem) throws IllegalAccessException;
    void deleteDepartmentProduct(int id);
}
