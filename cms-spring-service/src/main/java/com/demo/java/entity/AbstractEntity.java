package com.demo.java.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractEntity
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 */
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 4639739352013178314L;

    private Long id;

    @JSONField(serialize = false)
    private Date createdAt;

    @JSONField(serialize = false)
    private String createdBy;

    @JSONField(serialize = false)
    private Date changedAt;

    @JSONField(serialize = false)
    private String changedBy;

    @JSONField(serialize = false)
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
