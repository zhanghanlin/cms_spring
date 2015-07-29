package com.demo.java.menu.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.java.menu.entity.Menu;

public class MenuNode implements Serializable {

    private static final long serialVersionUID = 1488831552854335402L;

    private String code = "0";

    private String parentCode = "0";

    private Menu node;

    private List<MenuNode> childNode;

    public MenuNode() {
        init();
    }

    public MenuNode(Menu menu) {
        init();
        this.code = menu.getCode();
        this.parentCode = menu.getParentCode();
        this.node = menu;
    }

    public void init() {
        if ((childNode == null) || childNode.isEmpty()) {
            childNode = new ArrayList<MenuNode>();
        }
    }

    public void addChildNode(MenuNode t) {
        if (this.code.equals(t.getParentCode())) {
            childNode.add(t);
        } else {
            MenuNode mt = find(t.getParentCode());
            mt.getChildNode().add(t);
        }
    }

    public void removeChildNode(MenuNode t) {
        MenuNode mt = find(t.getParentCode());
        mt.getChildNode().remove(t);
    }

    public MenuNode find(String code) {
        if (this.code.equals(code)) {
            return this;
        }
        if ((childNode == null) || childNode.isEmpty()) {
            return null;
        } else {
            for (MenuNode menuTree : childNode) {
                MenuNode mt = menuTree.find(code);
                if (mt != null) {
                    return mt;
                }
            }
        }
        return null;
    }

    public int getLevel() {
        return code.length() / 3;
    }

    public boolean getHasChild() {
        return (childNode != null) && (childNode.size() > 0);
    }

    public String getCode() {
        return code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public Menu getNode() {
        return node;
    }

    public List<MenuNode> getChildNode() {
        return childNode;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}