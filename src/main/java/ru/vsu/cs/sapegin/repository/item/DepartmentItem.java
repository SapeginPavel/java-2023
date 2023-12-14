package ru.vsu.cs.sapegin.repository.item;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_id;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

import java.sql.Time;

@ORM_table(table_name = "department")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentItem {

    @ORM_id
    @ORM_column(column_name = "id")
    Integer id;

    @ORM_column(column_name = "name")
    String name;

    @ORM_column(column_name = "open_time")
    Time openTime;

    @ORM_column(column_name = "close_time")
    Time closeTime;
}
