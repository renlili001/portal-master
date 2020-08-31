package com.tydic.jg.portal.system.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "page_info", schema = "portal", catalog = "")
public class PageInfoEntity {
    private int id;
    private Integer type;
    private String name;
    private String icon;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageInfoEntity that = (PageInfoEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, icon, description);
    }
}
