package ru.vsu.cs.sapegin.dependencies;

import ru.vsu.cs.sapegin.App;
import ru.vsu.cs.sapegin.dependencies.annotation.Bean;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;
import ru.vsu.cs.sapegin.dependencies.annotation.NotSingleton;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> beans = new ConcurrentHashMap<>();

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public <T> T getBean(Class<T> clazz) throws Exception {
        if (beans.containsKey(clazz) && !clazz.isAnnotationPresent(NotSingleton.class)) {
            return (T) beans.get(clazz);
        }
        T bean = beanFactory.getBean(clazz);

        if (!clazz.isAnnotationPresent(NotSingleton.class)) {
            beans.put(clazz, bean);
        }

        doInjects(List.of(bean));
        return bean;
    }

    public void initializeAllBeans() throws Exception {
        List<Class<?>> classes = Utils.findAllClasses();
        for (Class<?> cl : classes) {
            if (cl.isAnnotationPresent(Bean.class)) {
                this.getBean(cl);
            }
        }
        Collection<Object> allBeans = this.getAllBeans();
        doInjects(allBeans);
    }

    private void doInjects(Collection<Object> beans) throws Exception {
        for (Object o : beans) {
            for (Field field : Arrays.stream(o.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                if (annotation.clazz() == App.class) {
                    field.set(o, this.getBean(field.getType()));
                } else {
                    field.set(o, this.getBean(annotation.clazz()));
                }
            }
        }
    }

    public <T> Collection<Object> getAllBeans() {
        return beans.values();
    }
}
