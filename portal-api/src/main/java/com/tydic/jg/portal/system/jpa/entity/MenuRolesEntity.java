package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu_roles", schema = "portal", catalog = "")
public class MenuRolesEntity {
    private int id;
    private String role;
    private int menuId;
    private Boolean disabled;

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
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "menu_id")
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "disabled")
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRolesEntity that = (MenuRolesEntity) o;
        return id == that.id &&
                menuId == that.menuId &&
                Objects.equals(role, that.role) &&
                Objects.equals(disabled, that.disabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, menuId, disabled);
    }
}
