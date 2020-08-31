package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_define", schema = "portal", catalog = "")
public class ProductDefineEntity {
    private int id;
    private int parentId;
    private int productId;
    private Integer menuId;
    private String menuName;
    private Integer menuOrder;
    private Boolean cache;
    private Boolean hidden;
    private String icon;

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
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "menu_order")
    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDefineEntity that = (ProductDefineEntity) o;
        return id == that.id &&
                parentId == that.parentId &&
                productId == that.productId &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(cache, that.cache) &&
                Objects.equals(hidden, that.hidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, productId, menuId, menuName, menuOrder, cache, hidden);
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
