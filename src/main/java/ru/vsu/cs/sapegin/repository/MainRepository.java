package ru.vsu.cs.sapegin.repository;

import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_id;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

import java.lang.reflect.Field;
import java.sql.*;

public class MainRepository<ITEM, ID> {

//    @Inject
    ConnectionManager connectionManager = new ConnectionManager();

    //todo: параметризовать классы?

    Class<ITEM> clazzOfItem;
    String nameOfId;
    String nameOfTable;

    public MainRepository(Class<ITEM> clazzOfItem) {
        this.clazzOfItem = clazzOfItem;
        handleItemClass();
    }

    private void handleItemClass() {
        ORM_table table_name = this.clazzOfItem.getAnnotation(ORM_table.class);
        this.nameOfTable = table_name.table_name();
        Field[] fields = clazzOfItem.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ORM_id.class)) {
                nameOfId = field.getAnnotation(ORM_column.class).column_name();
            };
        }
    }

    public ITEM findById(ID id) { //todo: попытаться с Object
        try (
            Connection connection = DriverManager.getConnection(
                ConnectionManager.DB_URL,
                ConnectionManager.DB_USER,
                ConnectionManager.DB_PASSWORD);
            PreparedStatement pStatement = connection.prepareStatement("select * from " + nameOfTable + " where " + nameOfId + " = " + id);
        ) {
            ResultSet resultSet = pStatement.executeQuery();
            resultSet.next();

            ITEM item = clazzOfItem.getDeclaredConstructor().newInstance();
            Field[] fields = item.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true); //установка доступа к приватным полям
                ORM_column column_name = f.getAnnotation(ORM_column.class);
                Object value = resultSet.getObject(column_name.column_name(), f.getType());
                f.set(item, value); //устанавливаем для какого-то объекта item значение value в поле f
            }
            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
