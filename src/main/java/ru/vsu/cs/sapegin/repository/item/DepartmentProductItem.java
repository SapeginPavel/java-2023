package ru.vsu.cs.sapegin.repository.item;

import lombok.ToString;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

@ORM_table(table_name = "department_product")
@ToString
public class DepartmentProductItem {

    @ORM_column(column_name = "dep_id")
    Integer depId;

    @ORM_column(column_name = "prod_id")
    Integer prodId;
}
