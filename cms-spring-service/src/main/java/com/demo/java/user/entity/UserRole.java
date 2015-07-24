package com.demo.java.user.entity;

import com.demo.java.annotation.Table;
import com.demo.java.common.entity.AbstractEntity;

@Table(name = "CMS_USER_ROLE")
public class UserRole extends AbstractEntity {

    private static final long serialVersionUID = 5504955904258222801L;

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