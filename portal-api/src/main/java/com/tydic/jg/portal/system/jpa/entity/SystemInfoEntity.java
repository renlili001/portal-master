package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "system_info", schema = "portal", catalog = "")
public class SystemInfoEntity {
    private int id;
    private Integer type;
    private String title;
    private String icon;
    private String path;
    private String routerBase;
    private Boolean used;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Override
    public String toString() {
        return "SystemInfoEntity{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", routerBase='" + routerBase + '\'' +
                ", used=" + used +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "router_base")
    public String getRouterBase() {
        return routerBase;
    }

    public void setRouterBase(String routerBase) {
        this.routerBase = routerBase;
    }

    @Basic
    @Column(name = "used")
    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
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
        SystemInfoEntity that = (SystemInfoEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(title, that.title) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(path, that.path) &&
                Objects.equals(routerBase, that.routerBase) &&
                Objects.equals(used, that.used) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, icon, path, routerBase, used, createBy, updateBy, createTime, updateTime);
    }
}
