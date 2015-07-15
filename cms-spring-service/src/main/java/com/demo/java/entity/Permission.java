package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_PERMISSION")
public class Permission extends AbstractEntity {

    private static final long serialVersionUID = 1056036494403461663L;

    private Long permissionId;

    private Long roleId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
