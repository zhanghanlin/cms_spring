package com.demo.java.web.menu.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.java.menu.entity.Menu;

public class MenuTree implements Serializable {

    private static final long serialVersionUID = 1488831552854335402L;

    private String code = "0";

    private String pCode = "0";

    private Menu node;

    private List<MenuTree> children;

    public static MenuTree list2tree(List<Menu> list) {
        MenuTree tree = new MenuTree();
        for (Menu m : list) {
            MenuTree t = new MenuTree(m);
            tree.addChildNode(t);
        }
        return tree;
    }

    public MenuTree() {
        init();
    }

    public MenuTree(Menu menu) {
        init();
        this.code = menu.getCode();
        this.pCode = menu.getParentCode();
        this.node = menu;
    }

    public void init() {
        if ((children == null) || children.isEmpty()) {
            children = new ArrayList<MenuTree>();
        }
    }

    public void addChildNode(MenuTree t) {
        if (code.equals(t.getPCode())) {
            children.add(t);
        } else {
            MenuTree mt = find(t.getPCode());
            mt.getChildren().add(t);
        }
    }

    public void removeChildNode(MenuTree t) {
        MenuTree mt = find(t.getPCode());
        mt.getChildren().remove(t);
    }

    public MenuTree find(String code) {
        if (this.code.equals(code)) {
            return this;
        }
        if ((children == null) || children.isEmpty()) {
            return null;
        } else {
            for (MenuTree menuTree : children) {
                MenuTree mt = menuTree.find(code);
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
        return (children != null) && (children.size() > 0);
    }

    public String getCode() {
        return code;
    }

    public String getPCode() {
        return pCode;
    }

    public Menu getNode() {
        return node;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}