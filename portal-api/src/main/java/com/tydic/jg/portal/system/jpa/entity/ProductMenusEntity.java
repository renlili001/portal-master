package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ProductMenusEntity implements Serializable {

    private int id;
    private String productName;
    private int parentId;
    private int productId;
    private int menuId;
    private int menuOrder;
    private int type;
    private String title;
    private String name;
    private int sysId;
    private String icon;
    private String component;
    private String routerBase;
    private String path;
    private Boolean external;
    private Boolean cache;
    private Boolean hidden;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "menu_id")
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_order")
    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
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
    @Column(name = "sys_id")
    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
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
    @Column(name = "router_base")
    public String getRouterBase() {
        return routerBase;
    }

    public void setRouterBase(String routerBase) {
        this.routerBase = routerBase;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, productId, menuId, cache, hidden);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMenusEntity that = (ProductMenusEntity) o;
        return id == that.id &&
                parentId == that.parentId &&
                productId == that.productId &&
                menuId == that.menuId &&
                menuOrder == that.menuOrder &&
                type == that.type &&
                sysId == that.sysId &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(title, that.title) &&
                Objects.equals(name, that.name) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(component, that.component) &&
                Objects.equals(routerBase, that.routerBase) &&
                Objects.equals(path, that.path) &&
                Objects.equals(external, that.external) &&
                Objects.equals(cache, that.cache) &&
                Objects.equals(hidden, that.hidden);
    }
}
