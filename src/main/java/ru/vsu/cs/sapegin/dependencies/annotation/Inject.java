package ru.vsu.cs.sapegin.dependencies.annotation;

import ru.vsu.cs.sapegin.App;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {
    Class<?> clazz() default App.class;
}
