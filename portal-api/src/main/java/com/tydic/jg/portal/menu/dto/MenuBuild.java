package com.tydic.jg.portal.menu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuBuild {

    @JsonIgnore
    private int menuId;
    @JsonIgnore
    private int subsysId;
    @JsonIgnore
    private Integer pid;
    @JsonIgnore
    private Integer subCount;
    @JsonIgnore
    private Integer type;
    private String name;
    private String component;
    @JsonIgnore
    private Integer menuSort;
    private String path;
    private Boolean hidden;
    private String redirect;
    private Boolean alwaysShow;
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private String updateBy;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private MenuMeta meta;

    private List<MenuBuild> children;
}
