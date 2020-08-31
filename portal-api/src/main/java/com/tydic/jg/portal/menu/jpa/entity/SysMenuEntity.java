package com.tydic.jg.portal.menu.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_menu", schema = "portal", catalog = "")
public class SysMenuEntity {

    private int menuId;
    private int subsysId;
    private Integer pid;
    private Integer subCount;
    private Integer type;
    private String title;
    private String name;
    private String component;
    private Integer menuSort;
    private String icon;
    private String path;
    private Boolean iFrame;
    private Boolean cache;
    private Boolean hidden;
    private Boolean subsysShow;
    private String mount;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;


    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "subsys_id")
    public int getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(int subsysId) {
        this.subsysId = subsysId;
    }

    @Basic
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "sub_count")
    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
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
    @Column(name = "menu_sort")
    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
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
    @Column(name = "i_frame")
    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame(Boolean iFrame) {
        this.iFrame = iFrame;
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
    @Column(name = "subsys_show")
    public Boolean getSubsysShow() {
        return subsysShow;
    }

    public void setSubsysShow(Boolean subsysShow) {
        this.subsysShow = subsysShow;
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
        SysMenuEntity that = (SysMenuEntity) o;
        return menuId == that.menuId &&
                subsysId == that.subsysId &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(subCount, that.subCount) &&
                Objects.equals(type, that.type) &&
                Objects.equals(title, that.title) &&
                Objects.equals(name, that.name) &&
                Objects.equals(component, that.component) &&
                Objects.equals(menuSort, that.menuSort) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(path, that.path) &&
                Objects.equals(iFrame, that.iFrame) &&
                Objects.equals(cache, that.cache) &&
                Objects.equals(hidden, that.hidden) &&
                Objects.equals(subsysShow, that.subsysShow) &&
                Objects.equals(mount, that.mount) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, subsysId, pid, subCount, type, title, name, component, menuSort, icon, path, iFrame, cache, hidden, subsysShow, mount, createBy, updateBy, createTime, updateTime);
    }
}
