package ru.vsu.cs.sapegin.repository;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_id;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainRepository<ITEM, ID> {

    private ConnectionManager connectionManager = Starter.applicationContext.getBean(ConnectionManager.class);

    //todo: параметризовать классы?

    Class<ITEM> clazzOfItem;
    String nameOfId;
    String nameOfTable;

    public MainRepository(Class<ITEM> clazzOfItem) throws Exception {
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

    public ITEM findById(ID id) {
        try (
//            Connection connection = DriverManager.getConnection(
//                ConnectionManager.DB_URL,
//                ConnectionManager.DB_USER,
//                ConnectionManager.DB_PASSWORD);
            Connection connection = connectionManager.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("select * from " + nameOfTable + " where " + nameOfId + " = " + id);
        ) {

            ResultSet resultSet = pStatement.executeQuery();
            resultSet.next();

            //            ITEM item = clazzOfItem.getDeclaredConstructor().newInstance();
//            Field[] fields = item.getClass().getDeclaredFields();
//            for (Field f : fields) {
//                f.setAccessible(true); //установка доступа к приватным полям
//                ORM_column column_name = f.getAnnotation(ORM_column.class);
//                Object value = resultSet.getObject(column_name.column_name(), f.getType());
//                f.set(item, value); //устанавливаем для какого-то объекта item значение value в поле f
//            }
            return parseTupleIntoItemObject(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ITEM> findAll() {
        try(
            Connection connection = connectionManager.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("select * from " + nameOfTable);
        ) {
            ResultSet resultSet = pStatement.executeQuery();
            resultSet.next();

            List<ITEM> items = new CopyOnWriteArrayList<>(); //потокобезопасный

            for (ITEM i : items) {

//                i = clazzOfItem.getDeclaredConstructor().newInstance();
//                Field[] fields = i.getClass().getDeclaredFields();
//                for (Field f : fields) {
//                    f.setAccessible(true); //установка доступа к приватным полям
//                    ORM_column column_name = f.getAnnotation(ORM_column.class);
//                    Object value = resultSet.getObject(column_name.column_name(), f.getType());
//                    f.set(i, value); //устанавливаем для какого-то объекта item значение value в поле f
//                }
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ITEM parseTupleIntoItemObject(ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        ITEM item = clazzOfItem.getDeclaredConstructor().newInstance();
        Field[] fields = item.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true); //установка доступа к приватным полям
            ORM_column column_name = f.getAnnotation(ORM_column.class);
            Object value = resultSet.getObject(column_name.column_name(), f.getType());
            f.set(item, value); //устанавливаем для какого-то объекта item значение value в поле f
        }
        return item;
    }
}
