package com.demo.java.menu.utils;

import java.util.HashMap;
import java.util.Map;

public class MenuMemory {

    private final static Map<Long, MenuNode> cache = new HashMap<Long, MenuNode>();

    public static Map<Long, MenuNode> getCache() {
        return cache;
    }

    public static MenuNode get(Long id) {
        return cache.get(id);
    }

    public static void put(Long id, MenuNode node) {
        cache.put(id, node);
    }
}
