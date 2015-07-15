package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_USER_ROLE")
public class UserRole extends AbstractEntity {

    private static final long serialVersionUID = -7986696483438264667L;

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
