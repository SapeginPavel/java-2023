package ru.vsu.cs.sapegin.repository.reps;

import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.MainRepository;
import ru.vsu.cs.sapegin.repository.item.DepartmentProductItem;
import ru.vsu.cs.sapegin.repository.item.ProductItem;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Bean
public class DepartmentProductRepository extends MainRepository<DepartmentProductItem, Integer> {

    public DepartmentProductRepository() throws Exception {
        super(DepartmentProductItem.class);
    }

//    public List<DepartmentProductItem> getAllProductsForDepartmentId(int id) {
//        try( //верно же, что не надо ресурсы закрывать?
//                Connection connection = connectionManager.getConnection();
//                PreparedStatement ps = connection.prepareStatement("select * from " + nameOfTable + " where dep_id = " + id);
//        ) {
//            ResultSet rs = ps.executeQuery();
//            List<DepartmentProductItem> items = new CopyOnWriteArrayList<>();
//            while (rs.next()) {
//                items.add(parseTupleIntoItemObject(rs));
//            }
//            return items;
//        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
//            throw new RuntimeException("Не удалось получить подключение в " + this.getClass() + e);
//        }
//    }
}
