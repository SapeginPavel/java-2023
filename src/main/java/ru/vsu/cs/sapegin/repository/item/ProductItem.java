package ru.vsu.cs.sapegin.repository.item;

import lombok.ToString;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_id;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

@ORM_table(table_name = "product")
@ToString
public class ProductItem {

    @ORM_id
    @ORM_column(column_name = "id")
    Integer id;

    @ORM_column(column_name = "name")
    String name;

    @ORM_column(column_name = "cost")
    Integer cost;
}
