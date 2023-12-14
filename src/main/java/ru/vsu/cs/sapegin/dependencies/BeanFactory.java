package ru.vsu.cs.sapegin.dependencies;

import ru.vsu.cs.sapegin.App;
import ru.vsu.cs.sapegin.dependencies.annotation.Component;
import ru.vsu.cs.sapegin.dependencies.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class BeanFactory {

    ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) throws Exception {

        Class<T> cl = getImplementationOf(clazz);
        T bean = cl.getDeclaredConstructor().newInstance();

        return bean;
    }

    private <T> Class<T> getImplementationOf(Class<?> clazz) throws Exception {
        if (clazz.isInterface()) {
            List<Class<?>> allClasses = Utils.findAllClasses();
            for (Class<?> cl : allClasses) {
                if (clazz.isAssignableFrom(cl) && cl.isAnnotationPresent(Component.class)) {
                    return (Class<T>) cl;
                }
            }
        } else if (clazz.isAnnotationPresent(Component.class)){
            return (Class<T>) clazz;
        }
        throw new Exception(String.format("Does not exist implementation for %s", clazz));
    }
}
