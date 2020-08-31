package com.tydic.jg.portal.menu.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_roles_menus", schema = "portal", catalog = "")
public class SysRolesMenusEntity {

    @Id
    @Column(name = "id")
    private int id;
    private String role;
    private Integer menuId;
    private Integer subsysId;
    private Boolean disabled;

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
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "subsys_id")
    public Integer getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(Integer subsysId) {
        this.subsysId = subsysId;
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
        SysRolesMenusEntity that = (SysRolesMenusEntity) o;
        return id == that.id &&
                Objects.equals(role, that.role) &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(subsysId, that.subsysId) &&
                Objects.equals(disabled, that.disabled);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, menuId, subsysId, disabled);
    }
}
