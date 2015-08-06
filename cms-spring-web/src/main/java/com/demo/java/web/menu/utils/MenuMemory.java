package com.demo.java.web.menu.utils;

import java.util.HashMap;
import java.util.Map;

import com.demo.java.web.menu.vo.MenuTree;

public class MenuMemory {

    private final static Map<Long, MenuTree> cache = new HashMap<Long, MenuTree>();

    public static Map<Long, MenuTree> getCache() {
        return cache;
    }

    public static MenuTree get(Long id) {
        return cache.get(id);
    }

    public static void put(Long id, MenuTree node) {
        cache.put(id, node);
    }
}
