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
import java.util.Arrays;
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
                break;
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
            ResultSet resultSet = pStatement.executeQuery();  //своего рода итератор по строчкам?
            List<ITEM> items = new CopyOnWriteArrayList<>(); //потокобезопасный
            while (resultSet.next()) {
                items.add(parseTupleIntoItemObject(resultSet));
            }
            return items;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ITEM addItem(ITEM addedItem) throws IllegalAccessException {
        List<Field> fieldsForAdd = getFieldsOfItem(addedItem);
        StringBuilder sbNames = new StringBuilder();
        StringBuilder sbValues = new StringBuilder();
        for (int i = 1; i < fieldsForAdd.size(); i++) {
            fieldsForAdd.get(i).setAccessible(true);
            sbNames.append(fieldsForAdd.get(i).getAnnotation(ORM_column.class).column_name());
            sbValues.append("'");
            sbValues.append(fieldsForAdd.get(i).get(addedItem));
            sbValues.append("'");
            if (i != fieldsForAdd.size() - 1) {
                sbNames.append(", ");
                sbValues.append(", ");
            }
        }
        String columnNames = sbNames.toString();
        String values = sbValues.toString();
        try(
            Connection connection = connectionManager.getConnection();
            PreparedStatement pStatementAdd = connection.prepareStatement(
                    "insert into " + nameOfTable + "(" + columnNames + ")" + " values (" + values + ")"
            );
        ) {
            pStatementAdd.execute();
            PreparedStatement pStatementSelectId = connection.prepareStatement(
                    "SELECT max(" + nameOfId + ") FROM " + nameOfTable
            );
            ResultSet idRS = pStatementSelectId.executeQuery();
            idRS.next();
            int last_id = idRS.getInt(1);

            PreparedStatement pStatementSelect = connection.prepareStatement(
                    "select * from " + nameOfTable + " where " + nameOfId + " = " + last_id
            );

            ResultSet resultSet = pStatementSelect.executeQuery();
            resultSet.next();
            return parseTupleIntoItemObject(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private List<String> getColumnsNamesWithoutId(ITEM item) {
//        List<String> names = new CopyOnWriteArrayList<>();
//        Field[] fields = clazzOfItem.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(ORM_id.class)) {
//                continue;
//            };
//            ORM_column c = field.getAnnotation(ORM_column.class);
//            names.add(c.column_name());
//        }
//        return names;
//    }

    private List<Field> getFieldsOfItem(ITEM item) {
        Field[] declaredFields = clazzOfItem.getDeclaredFields();
        List<Field> fields = new CopyOnWriteArrayList<>(Arrays.stream(declaredFields).toList());
        for (Field f : fields) {
            if (f.isAnnotationPresent(ORM_id.class)) { //todo: неэффективный метод, конечно, но времени мало
                fields.remove(f);
                fields.add(0, f);
            };
        }
        return fields;
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
