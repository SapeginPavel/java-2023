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

    public void start() throws Exception {
        Starter session = new Starter();
        ApplicationContext applicationContext = session.run();
        ConnectionManager connectionManager = applicationContext.getBean(ConnectionManager.class);
        connectionManager.createConnection();
        //todo: всё подряд инжектить
    }

    public ApplicationContext run() {
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
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
