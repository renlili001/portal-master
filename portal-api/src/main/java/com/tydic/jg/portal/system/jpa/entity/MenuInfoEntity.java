package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "menu_info", schema = "portal", catalog = "")
public class MenuInfoEntity {
    private int id;
    private int type;
    private String name;
    private int sysId;
    private String title;
    private String icon;
    private String component;
    private String path;
    private Boolean external;
    private Boolean cache;
    private Boolean hidden;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sys_id")
    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "external")
    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    @Basic
    @Column(name = "cache")
    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    @Basic
    @Column(name = "hidden")
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuInfoEntity that = (MenuInfoEntity) o;
        return id == that.id &&
                type == that.type &&
                sysId == that.sysId &&
                Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(component, that.component) &&
                Objects.equals(path, that.path) &&
                Objects.equals(external, that.external) &&
                Objects.equals(cache, that.cache) &&
                Objects.equals(hidden, that.hidden) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, sysId, title, icon, component, path, external, cache, hidden, createBy, updateBy, createTime, updateTime);
    }
}
