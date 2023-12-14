package ru.vsu.cs.sapegin;

import lombok.Getter;
import ru.vsu.cs.sapegin.dependencies.ApplicationContext;
import ru.vsu.cs.sapegin.dependencies.BeanFactory;

@Getter
public class Starter {
    private ApplicationContext applicationContext;
    public void initializeAll() throws Exception {
        applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        applicationContext.initializeAllBeans();
    }
}
