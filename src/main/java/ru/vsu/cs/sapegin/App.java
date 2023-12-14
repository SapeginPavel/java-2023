package ru.vsu.cs.sapegin;

import ru.vsu.cs.sapegin.dependencies.ApplicationContext;

public class App {
    public static void main( String[] args ) throws Exception {
        Starter starter = new Starter();
        try {
            starter.initializeAll();
        } catch (Exception e) {
            throw new Exception("Не удалось проинициализировать бины");
        }
//        ApplicationContext ap = starter.getApplicationContext();
        ApplicationContext ap = Starter.applicationContext;

        Testing t = ap.getBean(Testing.class);
        t.testGetAll();
        t.testGetting();
        t.testAdding();
        t.testUpdating();
        t.testGetAll();

    }
}
