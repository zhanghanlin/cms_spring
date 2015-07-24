package com.demo.java.user.entity;

import com.demo.java.annotation.Table;
import com.demo.java.common.entity.AbstractEntity;

@Table(name = "CMS_ROLE_MENU")
public class RoleMenu extends AbstractEntity {

    private static final long serialVersionUID = 772683538716823622L;

    private int roleId;

    private int menuCode;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }
}