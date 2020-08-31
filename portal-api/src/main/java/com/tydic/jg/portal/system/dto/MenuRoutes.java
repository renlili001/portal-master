package com.tydic.jg.portal.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuRoutes {

    @JsonIgnore
    private int id;
    @JsonIgnore
    private int type;
    private String name;
    @JsonIgnore
    private int sysId;
    @JsonIgnore
    private String title;
    @JsonIgnore
    private String icon;
    private String component;
    private String path;
    @JsonIgnore
    private Boolean external;
    @JsonIgnore
    private Boolean cache;
    private Boolean hidden;
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private String updateBy;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private MenuRoutesMeta meta;
}
