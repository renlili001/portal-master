package com.tydic.jg.portal.menu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class SysSub {
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
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;

    private List<Menu> menus;
}
