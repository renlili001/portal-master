package com.tydic.jg.portal.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MenuInfo {
    private int id;
    private int type;
    private String name;
    private int sysId;
    private String title;
    private String icon;
    private String component;
    private String path;
    private Boolean external;
    private Boolean cache;
    private Boolean hidden;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> roles;
}
