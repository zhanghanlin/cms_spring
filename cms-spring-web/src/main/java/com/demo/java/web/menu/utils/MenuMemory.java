package com.demo.java.web.menu.utils;

import java.util.HashMap;
import java.util.Map;

import com.demo.java.web.menu.vo.MenuTree;

public class MenuMemory {

    private final static Map<Object, MenuTree> cache = new HashMap<Object, MenuTree>();

    public static Map<Object, MenuTree> getCache() {
        return cache;
    }

    public static MenuTree get(Object id) {
        return cache.get(id);
    }

    public static void put(Object id, MenuTree node) {
        cache.put(id, node);
    }
}
