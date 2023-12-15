package ru.vsu.cs.sapegin.repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cache <T, Y> {

    private final int CACHE_SIZE = 20;
    private final double FILL_LEVEL = 0.75;
    private int currentSize = 0;
    private Map<T, Y> cache;


    public Cache() {
        cache = new ConcurrentHashMap<>();
    }

    public void pushObj(T id, Y obj) {
        if (!cache.containsKey(id)) {
            cache.put(id, obj);
            System.out.println("Закидываем в кеш: " + obj);
            currentSize++;
            System.out.println("Текущий размер кеша: " + currentSize);
            if (checkCacheSizeIsTooBig()) {
                System.out.println("Удаляем");
                removeRandomObjs();
                System.out.println("Удалили");
            }
        }
    }

    public Y getObj(T id) {
        if (cache.containsKey(id)) {
            System.out.println("Забираем объект из кеша!");
            return cache.get(id);
        }
        return null;
    }

    public Y removeObj(T id) {
        return cache.remove(id);
    }

    private boolean checkCacheSizeIsTooBig() {
        return (currentSize + 0.0) / CACHE_SIZE > FILL_LEVEL;
    }

    private void removeRandomObjs() {
        Set<T> keys = cache.keySet();
        System.out.println("-Из удаления: количество ключей в начале - " + keys.size());
        int i = 0;
        for (T k : keys) {
            if (i % 2 == 0) {
                cache.remove(k);
                currentSize--;
            }
            i++;
        }
        System.out.println("-Из удаления: количество ключей после удаления - " + cache.keySet().size());
    }
}
