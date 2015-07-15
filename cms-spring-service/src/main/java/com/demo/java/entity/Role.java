package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_ROLE")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 376155477413085670L;

    private String roleName;

    private String roleNote;

    private int status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNote() {
        return roleNote;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
