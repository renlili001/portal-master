package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_info", schema = "portal", catalog = "")
public class ProductInfoEntity {
    private int id;
    private String name;
    private String icon;
    private String description;
    private String role;
    private Boolean sider;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfoEntity that = (ProductInfoEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(description, that.description) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, description, role);
    }

    @Basic
    @Column(name = "sider")
    public Boolean getSider() {
        return sider;
    }

    public void setSider(Boolean sider) {
        this.sider = sider;
    }
}
