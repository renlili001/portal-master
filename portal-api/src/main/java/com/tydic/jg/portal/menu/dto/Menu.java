package com.tydic.jg.portal.menu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Menu {

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

    private List<Menu> children;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private List<String> roles;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", subsysId=" + subsysId +
                ", pid=" + pid +
                ", subCount=" + subCount +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", menuSort=" + menuSort +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", iFrame=" + iFrame +
                ", cache=" + cache +
                ", hidden=" + hidden +
                ", subsysShow=" + subsysShow +
                ", mount='" + mount + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                '}';
    }
}
