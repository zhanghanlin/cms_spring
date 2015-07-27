package com.demo.java.menu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MenuTree implements Serializable {

    private static final long serialVersionUID = 1488831552854335402L;

    private String code;

    private String parentCode;

    private Menu node;

    private List<MenuTree> childNode;

    public MenuTree(Menu menu) {
        init();
        this.code = menu.getCode();
        this.parentCode = menu.getParentCode();
        this.node = menu;
    }

    public void init() {
        if ((childNode == null) || childNode.isEmpty()) {
            childNode = new ArrayList<MenuTree>();
        }
    }

    public void addChildNode(MenuTree t) {
        if (this.code.equals(t.getParentCode())) {
            childNode.add(t);
        } else {
            MenuTree mt = find(t.getParentCode());
            mt.getChildNode().add(t);
        }
    }

    public void removeChildNode(MenuTree t) {
        MenuTree mt = find(t.getParentCode());
        mt.getChildNode().remove(t);
    }

    public MenuTree find(String code) {
        if (this.code.equals(code)) {
            return this;
        }
        if ((childNode == null) || childNode.isEmpty()) {
            return null;
        } else {
            for (MenuTree menuTree : childNode) {
                MenuTree mt = menuTree.find(menuTree.getCode());
                if (mt != null) {
                    return mt;
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Menu getNode() {
        return node;
    }

    public void setNode(Menu node) {
        this.node = node;
    }

    public List<MenuTree> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<MenuTree> childNode) {
        this.childNode = childNode;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}