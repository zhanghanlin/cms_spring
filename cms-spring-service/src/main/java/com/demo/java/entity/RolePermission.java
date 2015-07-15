package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_ROLE_PERMISSION")
public class RolePermission extends AbstractEntity {

    private static final long serialVersionUID = -2954704403355189076L;

    private int roleId;

    private int permissionId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}