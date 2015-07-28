package com.demo.java.memory;

import java.util.HashMap;
import java.util.Map;

public class MenuCache {

    private final static Map<Long, Object> menuMap = new HashMap<Long, Object>();

    public static Map<Long, Object> getMenuMap() {
        return menuMap;
    }

    public static void add(Long uid, Object o) {
        menuMap.put(uid, o);
    }

    public static Object get(Long uid) {
        return menuMap.get(uid);
    }
}
