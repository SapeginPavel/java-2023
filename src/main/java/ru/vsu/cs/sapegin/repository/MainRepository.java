package ru.vsu.cs.sapegin.repository;

import ru.vsu.cs.sapegin.Starter;
import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_column;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_id;
import ru.vsu.cs.sapegin.repository.rep_annotations.ORM_table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainRepository<ITEM, ID> {

    protected ConnectionManager connectionManager = Starter.applicationContext.getBean(ConnectionManager.class);

    //todo: параметризовать классы?

    protected Class<ITEM> clazzOfItem;
    protected String nameOfId;
    protected String nameOfTable;

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

    public ITEM updateItem(ID id, ITEM updatedItem) throws IllegalAccessException {
        try(
                Connection connection = connectionManager.getConnection();
        ) {
            List<Field> fieldsForAdd = getFieldsOfItem(updatedItem);
            StringBuilder query = new StringBuilder();
            query.append("set ");
            for (int i = 1; i < fieldsForAdd.size(); i++) {;
                fieldsForAdd.get(i).setAccessible(true);
                query.append(fieldsForAdd.get(i).getAnnotation(ORM_column.class).column_name());
                query.append("=");
                query.append("'");
                query.append(String.format("%s", fieldsForAdd.get(i).get(updatedItem)));
//                query.append(fieldsForAdd.get(i).get(updatedItem));
                query.append("'");
                if (i != fieldsForAdd.size() - 1) {
                    query.append(", ");
                }
            }
            String queryString = query.toString();

            PreparedStatement pStatementUpdate = connection.prepareStatement(
                    "update " + nameOfTable + " " + queryString + " where " + nameOfId + "=" + id
            );
            PreparedStatement pStatementSelect = connection.prepareStatement(
                    "select * from " + nameOfTable + " where " + nameOfId + " = " + id
            );

            pStatementUpdate.execute();
            ResultSet resultSet = pStatementSelect.executeQuery();
            resultSet.next();
            return parseTupleIntoItemObject(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(ID id) {
        try(
            Connection connection = connectionManager.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(
                    "delete from " + nameOfTable + " where " + nameOfId + "=" + id
            );
        ) {
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public <T> List<ITEM> getByColumnName(List<String> requiredColumns, String columnName, T comparable) {
        StringBuilder requiredColumnsAsSB = new StringBuilder();
        if (requiredColumns == null) {
            requiredColumnsAsSB.append(" * ");
        } else {
            for (int i = 0; i < requiredColumns.size(); i++) {
                requiredColumnsAsSB.append(requiredColumns.get(i));
                if (i != requiredColumns.size() - 1) {
                    requiredColumnsAsSB.append(", ");
                }
            }
        }
        String requiredColumnsAsStr = requiredColumnsAsSB.toString();
        try( //верно же, что не надо ресурсы закрывать?
             Connection connection = connectionManager.getConnection();
             //todo: проверить, работает ли на строках
             PreparedStatement ps = connection.prepareStatement("select " + requiredColumnsAsStr + " from " + nameOfTable + " where " + columnName + "=" + "'" + comparable + "'");
        ) {
            ResultSet rs = ps.executeQuery();
            List<ITEM> items = new CopyOnWriteArrayList<>();
            while (rs.next()) {
                items.add(parseTupleIntoItemObject(rs));
            }
            return items;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Ошибка в " + this.getClass() + e);
        }
    }







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

    protected ITEM parseTupleIntoItemObject(ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        ITEM item = clazzOfItem.getDeclaredConstructor().newInstance();
        Field[] fields = item.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true); //установка доступа к приватным полям
            ORM_column column_name = f.getAnnotation(ORM_column.class);
            if (isExist(resultSet, column_name.column_name())) {
                Object value = resultSet.getObject(column_name.column_name(), f.getType());
                f.set(item, value); //устанавливаем для какого-то объекта item значение value в поле f
            }
        }
        return item;
    }

    private static boolean isExist(ResultSet resultSet, String columnName) throws SQLException {
        if (columnName == null || (columnName = columnName.trim()).isEmpty())
            return false;

        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            if (columnName.toLowerCase(Locale.ROOT).equals(metaData.getColumnName(i).toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
