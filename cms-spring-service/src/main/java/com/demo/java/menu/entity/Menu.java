package com.demo.java.menu.entity;

import com.demo.java.annotation.Ignore;
import com.demo.java.annotation.Table;
import com.demo.java.common.entity.AbstractEntity;

@Table(name = "CMS_MENU")
public class Menu extends AbstractEntity {

    private static final long serialVersionUID = 8120213280811663663L;

    private String name;

    private String code;

    private String uniqueKey;

    private Long parentId;

    private String note;

    private String link;

    private String icon;

    private int weight;

    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Ignore
    public String getParentCode() {
        int codeLength = code.length();
        String pCode = "0";
        if (codeLength > 3) {
            pCode = code.substring(0, codeLength - 3);
        }
        return pCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Ignore
    public int getLevel() {
        return code.length() / 3;
    }
}