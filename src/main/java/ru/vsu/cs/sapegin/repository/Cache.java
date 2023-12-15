package ru.vsu.cs.sapegin.repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cache <T, Y> {

    private final int CACHE_SIZE = 20;
    private final double FILL_LEVEL = 0.75;
    private int currentSize = 0;
    Map<T, Y> cache;


    public Cache() {
        cache = new ConcurrentHashMap<>();
    }

    public void pushObj(T id, Y obj) {
        if (!cache.containsKey(id)) {
            cache.put(id, obj);
            currentSize++;
            if (checkCacheSizeIsTooBig()) {
                removeRandomObjs();
            }
        }
    }

    public Y getObj(T id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        return null;
    }

    private boolean checkCacheSizeIsTooBig() {
        return (currentSize + 0.0) / CACHE_SIZE > FILL_LEVEL;
    }

    private void removeRandomObjs() {
        Set<T> keys = cache.keySet();
        int i = 0;
        for (T k : keys) {
            if (i % 2 == 0) {
                cache.remove(k);
            }
            i++;
        }
    }
}
