package com.tydic.jg.portal.menu.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_subsys", schema = "portal", catalog = "")
public class SysSubsysEntity  {
    private int subsysId;
    private Integer type;
    private String title;
    private String name;
    private String component;
    private Integer subsysSort;
    private String icon;
    private String path;
    private String routerBase;
    private Boolean openBlank;
    private Boolean menuCollapse;
    private Boolean used;
    private Boolean hidden;
    private String mount;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "subsys_id")
    public int getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(int subsysId) {
        this.subsysId = subsysId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "subsys_sort")
    public Integer getSubsysSort() {
        return subsysSort;
    }

    public void setSubsysSort(Integer subsysSort) {
        this.subsysSort = subsysSort;
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
    @Column(name = "open_blank")
    public Boolean getOpenBlank() {
        return openBlank;
    }

    public void setOpenBlank(Boolean openBlank) {
        this.openBlank = openBlank;
    }

    @Basic
    @Column(name = "menu_collapse")
    public Boolean getMenuCollapse() {
        return menuCollapse;
    }

    public void setMenuCollapse(Boolean menuCollapse) {
        this.menuCollapse = menuCollapse;
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
    @Column(name = "hidden")
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "mount")
    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
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
        SysSubsysEntity that = (SysSubsysEntity) o;
        return subsysId == that.subsysId &&
                Objects.equals(type, that.type) &&
                Objects.equals(title, that.title) &&
                Objects.equals(name, that.name) &&
                Objects.equals(component, that.component) &&
                Objects.equals(subsysSort, that.subsysSort) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(path, that.path) &&
                Objects.equals(routerBase, that.routerBase) &&
                Objects.equals(openBlank, that.openBlank) &&
                Objects.equals(menuCollapse, that.menuCollapse) &&
                Objects.equals(used, that.used) &&
                Objects.equals(hidden, that.hidden) &&
                Objects.equals(mount, that.mount) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subsysId, type, title, name, component, subsysSort, icon, path, routerBase, openBlank, menuCollapse, used, hidden, mount, createBy, updateBy, createTime, updateTime);
    }
}
