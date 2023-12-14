package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.db.ConnectionManager;
import ru.vsu.cs.sapegin.dependencies.ApplicationContext;
import ru.vsu.cs.sapegin.dependencies.BeanFactory;
import ru.vsu.cs.sapegin.dependencies.Utils;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.repository.MainRepository;

import java.lang.reflect.Field;
import java.util.List;

public class Starter {

    public void initializeAll() throws Exception {
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        applicationContext.initializeAllBeans();
    }

//    private void toInjectAll(ApplicationContext applicationContext) {
//        List<Class<?>> classes = Utils.findAllClasses();
//        for (Class<?> c : classes) {
//            for (Field f : c.getDeclaredFields()) {
//                f.setAccessible(true);
//                if (f.isAnnotationPresent(Inject.class)) {
//                    f.()
//                }
//            }
//        }
//    }
}
